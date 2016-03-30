package com.app.shop.shopapp.model;

import com.google.gson.Gson;

import java.util.List;

/**
 * <请描述这个类是干什么的>
 *
 * @author lwli
 * @data: 2016/3/23 12:26
 * @version: V1.0
 */
public class PeopleContactInfo {
    /**
     * data : [{"id":"1","phone":"110","title":"火警"}]
     * msg : 成功
     * code : 200
     */

    private String msg;
    private String code;
    /**
     * id : 1
     * phone : 110
     * title : 火警
     */
    public static PeopleContactInfo parse(String obj){
        PeopleContactInfo telInfo =new Gson().fromJson(obj,PeopleContactInfo.class);
        return telInfo;
    }

    private List<PeopleContactModel> data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(List<PeopleContactModel> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public List<PeopleContactModel> getData() {
        return data;
    }
}
