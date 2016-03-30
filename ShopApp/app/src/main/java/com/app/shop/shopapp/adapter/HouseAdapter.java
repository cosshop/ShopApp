package com.app.shop.shopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shop.shopapp.R;
import com.app.shop.shopapp.activity.HouseDetailActivity;
import com.app.shop.shopapp.model.HouseInfo;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;


/**
 * Created by Administrator on 2015/11/8.
 */
public class HouseAdapter extends BaseAdapter {
    private Context mContext;
    List<HouseInfo> list ;
    private BitmapUtils bitmapUtils;
    String type;
    String url;

    public HouseAdapter(Context context, List<HouseInfo> list,String type){
        this.mContext = context;
        this.list = list;
        this.type = type;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holderview holderview = null;

        if(convertView == null){
            holderview = new Holderview();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_house,parent,false);
            holderview.room = (TextView) convertView.findViewById(R.id.room);
            holderview.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holderview.acreage = (TextView) convertView.findViewById(R.id.acreage);
            holderview.tv_info = (TextView) convertView.findViewById(R.id.tv_info);
            holderview.address = (TextView) convertView.findViewById(R.id.address);
            holderview.img_logo = (ImageView) convertView.findViewById(R.id.img_logo);
            holderview.more = (ImageView) convertView.findViewById(R.id.more);
            convertView.setTag(holderview);
        }else {
            holderview = (Holderview)convertView.getTag();
        }


           url = "http://zhonghai.apptech.space/"+list.get(position).getLogo();

        holderview.acreage.setText("约"+list.get(position).getAcreage()+"平米");
        holderview.room.setText(list.get(position).getRoom());
        if(TextUtils.equals(type,"1")){
           // holderview.tv_price.setText(list.get(position).get);
        }else {
            holderview.tv_price.setText("￥"+list.get(position).getRent());
        }
        holderview.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HouseInfo houseInfo = list.get(position);
                Intent intent = new Intent();
                intent.setClass(mContext,HouseDetailActivity.class);
                intent.putExtra("shop_name",houseInfo.getShop_name());
                intent.putExtra("logo",houseInfo.getLogo());
                intent.putExtra("business_time",houseInfo.getBusiness_time());
                intent.putExtra("mobile",houseInfo.getMobile());
                intent.putExtra("address",houseInfo.getAddress());
                intent.putExtra("rent",houseInfo.getRent());
                intent.putExtra("room",houseInfo.getRoom());
                intent.putExtra("floor",houseInfo.getFloor());
                intent.putExtra("acreage",houseInfo.getAcreage());
                intent.putExtra("pay",houseInfo.getPay());
                intent.putExtra("tese",houseInfo.getTese());
                intent.putExtra("email",houseInfo.getEmail());
                mContext.startActivity(intent);
            }
        });
        holderview.address.setText(list.get(position).getAddress());
        holderview.tv_info.setText(list.get(position).getRoom()+"平方米|"+list.get(position).getRoom()+"|"+list.get(position).getFloor()+"层");
        showImg(holderview.img_logo,url);
        return convertView;
    }

    class Holderview{
        TextView room,acreage,tv_info,address,tv_price;
        ImageView img_logo,more;
    }
    public void showImg(ImageView img,String url){
        bitmapUtils = new BitmapUtils(mContext);
        //bitmapUtils.configDefaultLoadingImage(R.drawable.ic_launcher);//Ĭ�ϱ���ͼƬ
       // bitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_launcher);//����ʧ��ͼƬ
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);//����ͼƬѹ������
        bitmapUtils.display( img, url);
    }
}
