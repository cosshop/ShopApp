package com.app.shop.shopapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.shop.shopapp.R;
import com.app.shop.shopapp.utils.TitleManger;
import com.app.shop.shopapp.utils.ToastUtil;

/**
 * Created by Administrator on 2016/3/14.
 */
public class MyComplaintActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_complaint_activity);
        initView();
    }

    private void initView() {
        TextView addView = (TextView) findViewById(R.id.top_layout_right_tv);
        addView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ToastUtil.showToast("功能尚未完善");
    }
}
