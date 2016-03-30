package com.app.shop.shopapp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.app.shop.shopapp.R;
import com.app.shop.shopapp.base.activity.BaseActivity;

/**
 * <缴费>
 *
 * @author lwli
 * @data: 2016/3/12 11:13
 * @version: V1.0
 */
public class FeeInfoActivity extends BaseActivity {
    public static final String PAY_FEE_TYPE="pay_type";//支付类型
    public static final int PAY_PARKING=0X01;
    public static final int PAY_PM=0X02;
    public static final int PAY_GUARANTEE=0X03;
    private TextView tv_paeyd;
    private TextView tv_unpaeyd;
    private TextView tv_arrearage;
    @Override
    protected int bindLayout() {
        return R.layout.activity_feeinfo;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("缴费");
        tv_paeyd=findViewByIdU(R.id.tv_paeyd);
        tv_unpaeyd=findViewByIdU(R.id.tv_unpaeyd);
        tv_arrearage=findViewByIdU(R.id.tv_arrearage);
    }

    @Override
    public void setListener() {
        super.setListener();
        findViewByIdU(R.id.iv_guarantee_money).setOnClickListener(this);
        findViewByIdU(R.id.iv_parking_fee).setOnClickListener(this);
        findViewByIdU(R.id.iv_pm_fee).setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        tv_paeyd.setText("5\n已交");
        tv_unpaeyd.setText("3\n未交");
        tv_arrearage.setText("5\n欠费");
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,PayFeeListActivity.class);
        switch (v.getId()){
            case R.id.iv_guarantee_money:
                intent.putExtra(PAY_FEE_TYPE,PAY_GUARANTEE);
                startActivity(intent);
                break;
            case R.id.iv_pm_fee:
                intent.putExtra(PAY_FEE_TYPE,PAY_PM);
                startActivity(intent);
                break;
            case R.id.iv_parking_fee:
                intent.putExtra(PAY_FEE_TYPE,PAY_PARKING);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
