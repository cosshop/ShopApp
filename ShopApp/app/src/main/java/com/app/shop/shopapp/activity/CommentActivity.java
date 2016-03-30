package com.app.shop.shopapp.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.shop.shopapp.R;
import com.app.shop.shopapp.base.activity.BaseActivity;
import com.app.shop.shopapp.model.Response;
import com.app.shop.shopapp.utils.Constant;
import com.app.shop.shopapp.utils.ToastUtil;
import com.google.gson.Gson;
import com.jiongbull.jlog.JLog;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

/**
 * <请描述这个类是干什么的>
 *
 * @author lwli
 * @data: 2016/3/11 16:31
 * @version: V1.0
 */
public class CommentActivity extends BaseActivity {
    private EditText et_input;
    private TextView tv_count;
    private String club_id="";
    public final static String CLUB_ID="club_id";
    @Override
    protected int bindLayout() {
        return R.layout.activity_comment;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("评论");
        club_id=getIntent().getStringExtra(CLUB_ID);
        et_input=findViewByIdU(R.id.et_input);
        tv_count=findViewByIdU(R.id.tv_count);
        TextView tv_right=findViewByIdU(R.id.tv_right);
        tv_right.setText("发表");
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setOnClickListener(this);
    }

    @Override
    public void setListener() {
        super.setListener();
        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s)){
                    tv_count.setText("您已输入"+s.toString().length()+"个字");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(TextUtils.isEmpty(et_input.getText().toString().trim())){
            ToastUtil.showToast("请输入内容");
            return;
        }
        FinalHttp fh=new FinalHttp();
        AjaxParams uploadParams = new AjaxParams();
        JLog.d(Constant.HOST_URL + "topic/comment");
        uploadParams.put("user_name", "13266816551");
        uploadParams.put("club_id", club_id);
        uploadParams.put("content",et_input.getText().toString().trim());
        fh.post(Constant.HOST_URL + "topic/comment", uploadParams, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String o) {
                super.onSuccess(o);
                JLog.json(o);
                Response telInfo = new Gson().fromJson(o, Response.class);
                if (telInfo.isSuccess()) {
                    ToastUtil.showToast("提交成功");
                    finish(true);
                } else {
                    ToastUtil.showToast(telInfo.msg);
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                ToastUtil.showToast("上传失败");
            }
        });
    }
}
