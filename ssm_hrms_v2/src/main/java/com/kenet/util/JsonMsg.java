package com.kenet.util;

import java.util.HashMap;
import java.util.Map;

public class JsonMsg {
    private int code;
    private String msg;
    private Map<String,Object> extendInfo = new HashMap<>();

    public int getCode(){
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Map<String, Object> getExtendInfo() {
        return extendInfo;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setExtendInfo(Map<String, Object> extendInfo) {
        this.extendInfo = extendInfo;
    }

    public static JsonMsg success() {
        JsonMsg res = new JsonMsg();
        res.setCode(100);
        res.setMsg("successful");
        return res;
    }

    public static JsonMsg fail() {
        JsonMsg res = new JsonMsg();
        res.setMsg("fail");
        res.setCode(200);
        return res;
    }

    public JsonMsg addInfo(String key, Object obj) {
        this.extendInfo.put(key,obj);
        return this;
    }

}
