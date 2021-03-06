package com.app.shop.shopapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.shop.shopapp.R;
import com.app.shop.shopapp.fragment.FragmentTabAdapter;
import com.app.shop.shopapp.fragment.TabShopFm;
import com.app.shop.shopapp.fragment.TabProFm;
import com.app.shop.shopapp.fragment.TabOAFm;
import com.app.shop.shopapp.fragment.TabHouseFm;
import com.app.shop.shopapp.fragment.TabPersonFm;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */
    private RadioGroup rgs;
    private TextView mTitleDesc;
    private Button mLocal;
    private ImageView mIvLocal;
    private Button mLogin;
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


        rgs = (RadioGroup) findViewById(R.id.tabs_rg);
        mTitleDesc = (TextView) findViewById(R.id.tv_loal_des);
        mLocal = (Button) findViewById(R.id.btn_local_addr);
        mIvLocal = (ImageView) findViewById(R.id.iv_local_ico);
        mLogin = (Button) findViewById(R.id.btn_login);
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
                        mTitleDesc.setText(getString(R.string.str_bottom_shop));
                        break;
                    case 1:
                        mTitleDesc.setVisibility(View.VISIBLE);
                        mLogin.setVisibility(View.GONE);
                        mIvLocal.setVisibility(View.GONE);
                        mLocal.setVisibility(View.GONE);
                        mTitleDesc.setText(getString(R.string.str_bottom_pro));
                        break;
                    case 2:
                        mTitleDesc.setVisibility(View.VISIBLE);
                        mLogin.setVisibility(View.GONE);
                        mIvLocal.setVisibility(View.GONE);
                        mLocal.setVisibility(View.GONE);
                        mTitleDesc.setText(getString(R.string.str_bottom_oa)+"系统");
                        break;
                    case 3:
                        mTitleDesc.setVisibility(View.VISIBLE);
                        mLogin.setVisibility(View.GONE);
                        mIvLocal.setVisibility(View.GONE);
                        mLocal.setVisibility(View.GONE);
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
                        break;
                }
            }
        });

    }








}

