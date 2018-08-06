package com.lijie.pojo;

/**
 * Created by lijie on 2018/5/28.
 */
public class ResultPojo {
    private String status;//返回状态值 success正常 其他失败
    private Object data;//返回参数
    private String info;//返回说明
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
}
