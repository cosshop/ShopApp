package com.app.shop.shopapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.shop.shopapp.R;
import com.app.shop.shopapp.adapter.HouseAdapter;
import com.app.shop.shopapp.model.HouseInfo;
import com.app.shop.shopapp.utils.GsonTools;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ChushouActivity extends Activity {
    ListView listview;
    AsyncHttpClient client;
    String url = "http://zhonghai.apptech.space/api/room/lists";
    String id;
    TextView text;
    List<HouseInfo> houseinfolist = new ArrayList<HouseInfo>();
    HouseAdapter houseAdapter;
    ImageView img,img2,img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chushou);
        initview();
        getdata();
    }

    public void initview(){
        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img = (ImageView) findViewById(R.id.img);
        img2 = (ImageView) findViewById(R.id.img2);
        listview = (ListView) findViewById(R.id.listview);
        text = (TextView) findViewById(R.id.text);
        id = getIntent().getStringExtra("id");
        if(TextUtils.equals(id,"1")){
            text.setText("出售");
        } else if(TextUtils.equals(id,"2")){
            text.setText("出租");
            img2.setVisibility(View.VISIBLE);
        } else{
            text.setText("二手置换");
        }

        if(TextUtils.equals(id,"1")){
            img.setBackgroundResource(R.mipmap.photo4);
        }else if(TextUtils.equals(id,"2")){
            img.setBackgroundResource(R.mipmap.photo5);
        }else if(TextUtils.equals(id,"3")){
            img.setBackgroundResource(R.mipmap.photo6);
        }
    }

    public void getdata(){
        client = new AsyncHttpClient();

            RequestParams params = new RequestParams();
            params.put("category_id",id);
            client.get(url,params,new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String s) {
                    super.onSuccess(s);

                    //initdata(s);
                    // ToastUtil.show(ChushouActivity.this, s);
                    try{
                        JSONObject jsonObj = new JSONObject(s);
                        if(TextUtils.equals(jsonObj.getString("msg"),"成功")){
                            JSONArray houseList = jsonObj.getJSONArray("data");
                            int length = houseList.length();

                            for (int i = 0; i < length; i++) {
                                JSONObject oj = houseList.getJSONObject(i);
                                String proku = oj.toString();
                                houseinfolist.add(GsonTools.changeGsonToBean(proku,
                                        HouseInfo.class));
                            }
                            houseAdapter = new HouseAdapter(ChushouActivity.this,houseinfolist,id);
                            listview.setAdapter(houseAdapter);
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable throwable, String s) {
                    super.onFailure(throwable, s);
                   // ToastUtil.show(ChushouActivity.this, s);

                }
                @Override
                public void onStart() {
                    super.onStart();

                }
            });

    }

}
