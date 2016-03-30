package com.app.shop.shopapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.shop.shopapp.R;
import com.app.shop.shopapp.activity.ChushouActivity;

/**
 * Created with IntelliJ IDEA.
 * Author: wangjie  email:wangjie@cyyun.com
 * Date: 13-6-14
 * Time: 下午2:39
 */
public class TabHouseFm extends Fragment implements View.OnClickListener {
    private LinearLayout ll_chuzu,ll_chushou,ll_ershou;
    private View view;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        System.out.println("DDDDDDDDD____onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("DDDDDDDDD____onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("DDDDDDDDD____onCreateView");
        view = inflater.inflate(R.layout.tab_house, container, false);
        return view;
    }




    public void initView(){
        ll_chuzu = (LinearLayout) view.findViewById(R.id.ll_chuzu);
        ll_chushou = (LinearLayout) view.findViewById(R.id.ll_chushou);
        ll_ershou = (LinearLayout) view.findViewById(R.id.ll_ershou);

        ll_chuzu.setOnClickListener(this);
        ll_chushou.setOnClickListener(this);
        ll_ershou.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_chushou:
                Intent intent = new Intent();
                intent.putExtra("id","1");
                intent.setClass(getActivity(),ChushouActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_chuzu:
                Intent intent2 = new Intent();
                intent2.putExtra("id","2");
                intent2.setClass(getActivity(),ChushouActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_ershou:
                Intent intent3 = new Intent();
                intent3.putExtra("id","3");
                intent3.setClass(getActivity(),ChushouActivity.class);
                startActivity(intent3);
                break;
        }
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        System.out.println("DDDDDDDDD____onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("DDDDDDDDD____onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("DDDDDDDDD____onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("DDDDDDDDD____onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("DDDDDDDDD____onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("DDDDDDDDD____onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("DDDDDDDDD____onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("DDDDDDDDD____onDetach");
    }




}
