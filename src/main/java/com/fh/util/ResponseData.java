package com.fh.util;

public class ResponseData {

    private Integer code;

    private String info;

    private Object obj;


    //把无参构造私有化     //外界不能实例化
    private ResponseData(){

    }
    // 提供私有有参构造  //外界不能实例化
    private ResponseData(Object obj, Integer code, String info){
        this.obj=obj;
        this.info=info;
        this.code=code;
    }
    public static ResponseData success(Object obj){
        return  new ResponseData(obj,200,"success");
    }
    public static ResponseData error(Exception e){
        return  new ResponseData(null,500,e.getMessage());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
