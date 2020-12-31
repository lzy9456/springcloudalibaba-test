package com.example.entity.result;

public enum CodeEnum {
    NON("0001",""),
    SUCCESS("1001","请求成功"),
    FAIL("1002","请求失败"),
    ;


    String code;
    String msg;

    CodeEnum(String code, String msg){
        this.code=code;
        this.msg=msg;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
