package com.app.shop.shopapp.activity;

import android.app.Activity;
import android.os.Bundle;

import com.app.shop.shopapp.R;
import com.app.shop.shopapp.utils.TitleManger;

/**
 * Created by Administrator on 2016/3/14.
 */
public class MyPropertyFeeActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_property_fee_activity);

        TitleManger.show(this,"我的物业费",false,false);
    }
}
