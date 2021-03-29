package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @Auther: ljh
 * @Date: 2021/1/13 18:24
 * @Description:
 * code":0,"msg":"","count":1000,"data":
 */
public class ResponseData {
    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
