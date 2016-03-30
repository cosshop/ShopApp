package com.app.shop.shopapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shop.shopapp.R;
import com.app.shop.shopapp.utils.DataCleanManager;
import com.app.shop.shopapp.utils.TitleManger;

/**
 * Created by Administrator on 2016/3/14.
 */
public class SettingActivity extends Activity implements View.OnClickListener {

    private TextView tv_check_update;
    private TextView tv_about_us;
    private TextView tv_clean_ribbish;
    private TextView tv_message_remind;
    private TextView tv_account_safety;
    private Button btn_setting_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        TitleManger.show(SettingActivity.this, "设置", false, false);
        initView();
    }

    private void initView() {
        tv_account_safety = (TextView) findViewById(R.id.tv_account_safety);
        tv_message_remind = (TextView) findViewById(R.id.tv_message_remind);
        tv_clean_ribbish = (TextView) findViewById(R.id.tv_clean_cache);
        tv_check_update = (TextView) findViewById(R.id.tv_check_update);
        tv_about_us = (TextView) findViewById(R.id.tv_about_us);
        btn_setting_exit = (Button) findViewById(R.id.btn_setting_exit);

        tv_account_safety.setOnClickListener(this);
        tv_message_remind.setOnClickListener(this);
        tv_clean_ribbish.setOnClickListener(SettingActivity.this);
        tv_check_update.setOnClickListener(this);
        tv_about_us.setOnClickListener(this);
        btn_setting_exit.setOnClickListener(SettingActivity.this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_account_safety:
                Intent intent = new Intent(SettingActivity.this, AccountSafetyActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_message_remind:
                Toast.makeText(SettingActivity.this, "此功能尚未完善", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_clean_cache:
                showCleanCacheDialog();
                break;
            case R.id.tv_check_update:
                Toast.makeText(SettingActivity.this, "此功能尚未完善", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_about_us:
                Toast.makeText(SettingActivity.this, "此功能尚未完善", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_setting_exit:
                showExitAccountDialog();
                break;
        }

    }

    private void showExitAccountDialog() {

        final AlertDialog builder = new AlertDialog.Builder(SettingActivity.this).create();

        builder.show();
        Window window = builder.getWindow();
        window.setContentView(R.layout.exit_account_activity);
        window.findViewById(R.id.ll_exit_current_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });

        window.findViewById(R.id.ll_exit_current_account_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });

    }

    private void showCleanCacheDialog() {

        final AlertDialog dialog = new AlertDialog.Builder(SettingActivity.this).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setContentView(R.layout.exit_dialog);
        window.findViewById(R.id.tv_dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {


                    String cacheSize = DataCleanManager.getTotalCacheSize(SettingActivity.this);

                    if (cacheSize == "ok") {
                        Toast.makeText(SettingActivity.this, cacheSize, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    } else {
                        DataCleanManager.clearAllCache(SettingActivity.this);
                        if (cacheSize == "ok") {
                            Toast.makeText(SettingActivity.this, cacheSize, Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }


                } catch (Exception e) {

                }

            }
        });

        window.findViewById(R.id.tv_dialog_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        tv_check_update = null;
        tv_about_us = null;
        tv_clean_ribbish = null;
        tv_message_remind = null;
        tv_account_safety = null;
        btn_setting_exit = null;
        super.onDestroy();
    }
}
