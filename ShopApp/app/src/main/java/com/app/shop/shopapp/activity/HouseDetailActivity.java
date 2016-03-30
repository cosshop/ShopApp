package com.app.shop.shopapp.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shop.shopapp.R;
import com.lidroid.xutils.BitmapUtils;


public class HouseDetailActivity extends Activity implements View.OnClickListener {

    ImageView img_fangwu, img_phone;
    TextView tv_about, tv_time, tv_price, tv_room, tv_area, tv_floor, tv_tese, tv_xiaoqu, tv_address, tv_person,
            tv_phone, tv_email;
    TextView title, phonenumber, queding, quxiao;
    String shop_name, logo, business_time, mobile, address, rent, room, floor, acreage, pay, tese, email;
    private BitmapUtils bitmapUtils;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);
        initview();
        getdata();
        initdata();
    }

    public void initview() {
        img_fangwu = (ImageView) findViewById(R.id.img_fangwu);
        img_phone = (ImageView) findViewById(R.id.img_phone);
        img_phone.setOnClickListener(this);
        tv_about = (TextView) findViewById(R.id.tv_about);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_room = (TextView) findViewById(R.id.tv_room);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_about = (TextView) findViewById(R.id.tv_about);
        tv_area = (TextView) findViewById(R.id.tv_area);
        tv_tese = (TextView) findViewById(R.id.tv_tese);
        tv_xiaoqu = (TextView) findViewById(R.id.tv_xiaoqu);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_person = (TextView) findViewById(R.id.tv_person);
        tv_phone = (TextView) findViewById(R.id.tv_phone);

        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_floor = (TextView) findViewById(R.id.tv_floor);

        View view = getLayoutInflater().inflate(R.layout.dialog_call, null);
        dialog = new Dialog(HouseDetailActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth());
        dialog.getWindow().setAttributes(lp);

        //WindowManager.LayoutParams params = dialog.getWindow()
        //         .getAttributes();
        // params.width = 200;
        // params.height = 450;

        // dialog.getWindow().setAttributes(params);
        title = (TextView) view.findViewById(R.id.title);
        phonenumber = (TextView) view.findViewById(R.id.phonenumber);
        queding = (TextView) view.findViewById(R.id.queding);
        queding.setOnClickListener(this);
        quxiao = (TextView) view.findViewById(R.id.quxiao);
        quxiao.setOnClickListener(this);
    }

    public void getdata() {
        shop_name = getIntent().getStringExtra("shop_name");
        logo = getIntent().getStringExtra("logo");
        business_time = getIntent().getStringExtra("business_time");
        mobile = getIntent().getStringExtra("mobile");
        address = getIntent().getStringExtra("address");
        rent = getIntent().getStringExtra("rent");
        room = getIntent().getStringExtra("room");
        floor = getIntent().getStringExtra("floor");
        acreage = getIntent().getStringExtra("acreage");
        pay = getIntent().getStringExtra("pay");
        tese = getIntent().getStringExtra("tese");
        email = getIntent().getStringExtra("email");


    }

    public void initdata() {
        showImg(img_fangwu, "http://zhonghai.apptech.space/" + logo);
        tv_person.setText(shop_name);
        tv_about.setText(room + ",约" + acreage + "平米");
        tv_room.setText(room);
        tv_price.setText(rent + "元/月");
        tv_area.setText(acreage + "㎡");
        tv_tese.setText("户型特色：" + tese);
        tv_time.setText("发布时间 " + business_time);
        tv_address.setText(address);
        tv_phone.setText(mobile);
        tv_email.setText(email);
        tv_floor.setText(floor + "层");

        if (!TextUtils.isEmpty(mobile)) {
            phonenumber.setText(mobile);
        }

        title.setText("是否拨打电话(" + shop_name + ")");


    }

    public void showImg(ImageView img, String url) {
        bitmapUtils = new BitmapUtils(HouseDetailActivity.this);
        //bitmapUtils.configDefaultLoadingImage(R.drawable.ic_launcher);
        // bitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_launcher);
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
        bitmapUtils.display(img, url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.queding:
                if (!TextUtils.isEmpty(mobile)) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);
                }
                break;
            case R.id.quxiao:
                dialog.dismiss();
                break;
            case R.id.img_phone:
                dialog.show();
                break;
        }
    }
}
