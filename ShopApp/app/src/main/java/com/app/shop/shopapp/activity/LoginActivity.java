package com.app.shop.shopapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.shop.shopapp.R;
import com.app.shop.shopapp.utils.TitleManger;
import com.app.shop.shopapp.utils.ToastUtil;

public class LoginActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TitleManger.show(this, "登录", true, false);
        initView();
    }

    private void initView() {
        TextView addView = (TextView) findViewById(R.id.top_layout_right_tv);
        Button mLoginBtn = (Button) findViewById(R.id.btn_login_inner);
        TextView registerNow = (TextView) findViewById(R.id.tv_register_now);
        TextView  forgetPassword= (TextView) findViewById(R.id.tv_forget_password);
        addView.setText("快速登录");
        addView.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
        registerNow.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_layout_right_tv:
                ToastUtil.showToast("功能尚未完善");

                break;
            case R.id.btn_login_inner:
                ToastUtil.showToast("功能尚未完善");
                break;
            case R.id.tv_register_now:
                ToastUtil.showToast("功能尚未完善");
                break;
            case R.id.tv_forget_password:
                ToastUtil.showToast("功能尚未完善");
                break;
        }
    }
}
