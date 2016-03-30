package com.app.shop.shopapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.shop.shopapp.R;
import com.app.shop.shopapp.adapter.NetImageAdapter;
import com.app.shop.shopapp.base.activity.BaseActivity;
import com.app.shop.shopapp.model.PeopleContactInfo;
import com.app.shop.shopapp.model.PeopleContactModel;
import com.app.shop.shopapp.model.Response;
import com.app.shop.shopapp.utils.Constant;
import com.app.shop.shopapp.utils.TimeUtil;
import com.app.shop.shopapp.utils.ToastUtil;
import com.app.shop.shopapp.view.ExGridView;
import com.google.gson.Gson;
import com.jiongbull.jlog.JLog;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

/**
 * <请描述这个类是干什么的>
 *
 * @author lwli
 * @data: 2016/3/11 12:29
 * @version: V1.0
 */
public class PeopleContactActivity extends BaseActivity {
    private ListView lv_data;
    private List<PeopleContactModel>datas;
    public static final int PEOPLE_CONTACT_REQUEST=0x11;
    @Override
    protected int bindLayout() {
        return R.layout.activity_people_contact;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("邻里互动");
        lv_data=findViewByIdU(R.id.lv_data);
        TextView tv_right=findViewByIdU(R.id.tv_right);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("发贴");
        tv_right.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PEOPLE_CONTACT_REQUEST&&resultCode== Activity.RESULT_OK){
            loadData();
        }
    }

    @Override
    public void initData() {
        super.initData();
        loadData();
        datas=new ArrayList<PeopleContactModel>();
        lv_data.setAdapter(new Adapter(this));
    }

    @Override
    public void setListener() {
        super.setListener();
        lv_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                EaseUser easeUser=new EaseUser("123");
//                startActivity(new Intent(PeopleContactActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, easeUser.getUsername()));
            }
        });
    }
    final void loadData(){
        FinalHttp fh=new FinalHttp();
        JLog.d(Constant.HOST_URL + "topic/lists");
        fh.get(Constant.HOST_URL + "topic/lists", new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String o) {
                super.onSuccess(o);
                PeopleContactInfo peopleContactInfo=PeopleContactInfo.parse(o);
                if(peopleContactInfo.getData()!=null){
                    datas.addAll(peopleContactInfo.getData());
                    ((Adapter)lv_data.getAdapter()).notifyDataSetChanged();

                }
                JLog.json(o);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                ToastUtil.showToast("加载失败");
            }
        });
    }
    @Override
    public void onClick(View v) {
        startActivityForResult(new Intent(this,SendPostActivity.class),PEOPLE_CONTACT_REQUEST,false);

    }
    class Adapter extends BaseAdapter{
        private LayoutInflater inflater;
        public Adapter(Context context){
            inflater=LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public PeopleContactModel getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            PeopleContactModel item=getItem(position);
            Holder holder=null;
            if(convertView==null){
                convertView=inflater.inflate(R.layout.item_people_contact,null);
                holder=new Holder(convertView);
                convertView.setTag(holder);
            }else{
                holder= (Holder) convertView.getTag();
            }
            List<String>images=new ArrayList<>();
            if(!TextUtils.isEmpty(item.getPic0())){
                images.add(item.getPic0());
            }
            if(!TextUtils.isEmpty(item.getPic1())){
                images.add(item.getPic1());
            }
            if(!TextUtils.isEmpty(item.getPic2())){
                images.add(item.getPic2());
            }
            if(!TextUtils.isEmpty(item.getPic3())){
                images.add(item.getPic3());

            }
            if(!TextUtils.isEmpty(item.getPic4())){
                images.add(item.getPic4());
            }
            if(!TextUtils.isEmpty(item.getPic5())){
                images.add(item.getPic5());
            }
            if(images.size()>0){
                holder.exg_favor.setAdapter(new NetImageAdapter(holder.exg_favor.getContext(), images));
                holder.exg_favor.setColumnCount(images.size());
            }
//            UiUtil.setAvatar(holder.iv_favor,item.geti);
            holder.iv_comment.setTag(item);
            holder.iv_favor.setTag(item);
            holder.iv_comment.setOnClickListener(mCKListener);
            holder.iv_favor.setOnClickListener(mCKListener);
            holder.tv_info.setText(item.getTitle());
            holder.tv_comment_count.setText("("+item.getComment_num()+")");
            holder.tv_favor_count.setText("("+item.getLike_num()+")");
            holder.tv_name.setText(item.getClub_name());
            if(!TextUtils.isEmpty(item.getCreate_time()))holder.tv_time.setText(TimeUtil.getDistanceTime(item.getCreate_time()));
            return convertView;
        }
        class Holder{
            ImageView iv_comment;
            ImageView iv_favor;
            ExGridView exg_favor;
            TextView tv_info;
            TextView tv_comment_count;
            TextView tv_favor_count;
            TextView tv_time;
            TextView tv_name;
            public Holder(View v){
                tv_name= (TextView) v.findViewById(R.id.tv_name);
                tv_time= (TextView) v.findViewById(R.id.tv_time);
                tv_favor_count= (TextView) v.findViewById(R.id.tv_favor_count);
                tv_comment_count= (TextView) v.findViewById(R.id.tv_comment_count);
                tv_info= (TextView) v.findViewById(R.id.tv_info);
                iv_comment= (ImageView) v.findViewById(R.id.iv_comment);
                iv_favor= (ImageView) v.findViewById(R.id.iv_favor);
                exg_favor= (ExGridView) v.findViewById(R.id.exg_favor);
            }

        }
        private View.OnClickListener mCKListener=new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PeopleContactModel item= (PeopleContactModel) v.getTag();
                switch (v.getId()){
                    case R.id.iv_favor:
                        FinalHttp fh=new FinalHttp();
                        AjaxParams uploadParams = new AjaxParams();
                        JLog.d(Constant.HOST_URL + "topic/support");
                        uploadParams.put("user_name", "13266816551");
                        uploadParams.put("club_id",item.getClub_id());
                        fh.post(Constant.HOST_URL + "topic/support", uploadParams, new AjaxCallBack<String>() {
                            @Override
                            public void onSuccess(String o) {
                                super.onSuccess(o);
                                JLog.json(o);
                                Response telInfo = new Gson().fromJson(o, Response.class);
                                if (telInfo.isSuccess()) {
                                    ToastUtil.showToast("操作成功");
                                    loadData();
                                } else {
                                    ToastUtil.showToast(telInfo.msg);
                                }
                            }

                            @Override
                            public void onFailure(Throwable t, int errorNo, String strMsg) {
                                super.onFailure(t, errorNo, strMsg);
                                ToastUtil.showToast("上传失败");
                            }
                        });
                        break;
                    case R.id.iv_comment:
                        Intent intent=new Intent(PeopleContactActivity.this,CommentActivity.class);
                        intent.putExtra(CommentActivity.CLUB_ID,item.getClub_id());
                        startActivityForResult(intent,PEOPLE_CONTACT_REQUEST,false);
                        break;
                    default:
                        break;
                }

            }
        };
    }
}
