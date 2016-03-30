package com.app.shop.shopapp.activity;

import android.view.View;

import com.app.shop.shopapp.R;
import com.app.shop.shopapp.base.activity.BaseActivity;
import com.app.shop.shopapp.utils.Constant;
import com.app.shop.shopapp.utils.ToastUtil;
import com.jiongbull.jlog.JLog;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

/**
 * <请描述这个类是干什么的>
 *
 * @author lwli
 * @data: 2016/3/26 14:11
 * @version: V1.0
 */
public class PayFeeListActivity extends BaseActivity {
    private String feeType="CARD";
    @Override
    protected int bindLayout() {
        return R.layout.activity_payfee_list;
    }

    @Override
    public void initData() {
        super.initData();
        FinalHttp fh=new FinalHttp();
        AjaxParams uploadParams = new AjaxParams();
        JLog.d(Constant.HOST_URL + "fee/lists");
        uploadParams.put("member", "13266816551");
        uploadParams.put("fee_type",feeType);
        fh.post(Constant.HOST_URL + "topic/support", uploadParams, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String o) {
                super.onSuccess(o);
                JLog.json(o);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                ToastUtil.showToast("数据加载失败");
            }
        });
    }

    @Override
    public void setListener() {
        super.setListener();
    }

    @Override
    public void initView() {
        super.initView();
        int type=getIntent().getIntExtra(FeeInfoActivity.PAY_FEE_TYPE,FeeInfoActivity.PAY_PARKING);
        switch (type){
            case FeeInfoActivity.PAY_PARKING:
                feeType="CARD";
                setTitle("停车费");
                break;
            case FeeInfoActivity.PAY_PM:
                feeType="WUYE";
                setTitle("物业费");
                break;
            case FeeInfoActivity.PAY_GUARANTEE:
                feeType="ZHUANGXIU";
                setTitle("装修押金列表");
                break;
            default:
                break;
        }


    }

    @Override
    public void onClick(View v) {

    }
}
