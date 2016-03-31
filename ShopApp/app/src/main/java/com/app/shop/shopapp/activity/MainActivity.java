package com.app.shop.shopapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.shop.shopapp.R;
import com.app.shop.shopapp.fragment.FragmentTabAdapter;
import com.app.shop.shopapp.fragment.TabHouseFm;
import com.app.shop.shopapp.fragment.TabOAFm;
import com.app.shop.shopapp.fragment.TabPersonFm;
import com.app.shop.shopapp.fragment.TabProFm;
import com.app.shop.shopapp.fragment.TabShopFm;
import com.app.shop.shopapp.utils.Constant;
import com.jiongbull.jlog.JLog;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener{
    /**
     * Called when the activity is first created.
     */
    private RadioGroup rgs;
    private TextView mTitleDesc;
    private Button mLocal;
    private ImageView mIvLocal;
    private Button mLogin;
    private ImageButton mSetting;
    public List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        fragments.add(new TabShopFm());
        fragments.add(new TabProFm());
        fragments.add(new TabOAFm());
        fragments.add(new TabHouseFm());
        fragments.add(new TabPersonFm());

        FinalHttp fh=new FinalHttp();
        JLog.d(Constant.HOST_URL + "user/login");
        AjaxParams params=new AjaxParams();
        params.put("user_name","13266816551");
        params.put("password", "123456");
        fh.post(Constant.HOST_URL + "user/login", params, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String o) {
                super.onSuccess(o);
                JLog.json(o);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
        rgs = (RadioGroup) findViewById(R.id.tabs_rg);
        mTitleDesc = (TextView) findViewById(R.id.tv_loal_des);
        mLocal = (Button) findViewById(R.id.btn_local_addr);
        mIvLocal = (ImageView) findViewById(R.id.iv_local_ico);
        mLogin = (Button) findViewById(R.id.btn_login);
        mLogin.setOnClickListener(this);
        mSetting = (ImageButton) findViewById(R.id.btn_setting);
        mSetting.setOnClickListener(this);
        FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this, fragments, R.id.tab_content, rgs);
        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener(){
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                System.out.print("choose index is "+ index);
                switch (index){
                    case 0:
                        mTitleDesc.setVisibility(View.VISIBLE);
                        mLogin.setVisibility(View.VISIBLE);
                        mIvLocal.setVisibility(View.VISIBLE);
                        mLocal.setVisibility(View.VISIBLE);
                        mSetting.setVisibility(View.GONE);
                        mTitleDesc.setText(getString(R.string.str_bottom_shop));
                        break;
                    case 1:
                        mTitleDesc.setVisibility(View.VISIBLE);
                        mLogin.setVisibility(View.GONE);
                        mIvLocal.setVisibility(View.GONE);
                        mLocal.setVisibility(View.GONE);
                        mSetting.setVisibility(View.GONE);
                        mTitleDesc.setText(getString(R.string.str_bottom_pro));
                        break;
                    case 2:
                        mTitleDesc.setVisibility(View.VISIBLE);
                        mLogin.setVisibility(View.GONE);
                        mIvLocal.setVisibility(View.GONE);
                        mLocal.setVisibility(View.GONE);
                        mSetting.setVisibility(View.GONE);
                        mTitleDesc.setText(getString(R.string.str_bottom_oa)+"系统");
                        break;
                    case 3:
                        mTitleDesc.setVisibility(View.VISIBLE);
                        mLogin.setVisibility(View.GONE);
                        mIvLocal.setVisibility(View.GONE);
                        mLocal.setVisibility(View.GONE);
                        mSetting.setVisibility(View.GONE);
                        SpannableString styledText = new SpannableString("亲爱的小宝，你好");
                        styledText.setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.txt_style), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        styledText.setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.txt_style_font), 3, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                        mTitleDesc.setText(styledText, TextView.BufferType.SPANNABLE);

//                        mTitleDesc.setText(getString(R.string.str_bottom_house));
                        break;
                    case 4:
                        mTitleDesc.setVisibility(View.VISIBLE);
                        mLogin.setVisibility(View.GONE);
                        mIvLocal.setVisibility(View.GONE);
                        mLocal.setVisibility(View.GONE);
                        mTitleDesc.setText(getString(R.string.str_bottom_person));
                        mSetting.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_setting:{
                Intent intent =new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_login:{
                Intent intent =new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);}
            break;
        }
    }
}

