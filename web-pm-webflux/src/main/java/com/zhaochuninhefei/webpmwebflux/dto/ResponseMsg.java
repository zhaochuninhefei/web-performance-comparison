package com.zhaochuninhefei.webpmwebflux.dto;

/**
 * @author zhaochun
 */
@SuppressWarnings("unused")
public class ResponseMsg<T> {
    private String resCd;
    private String resMsg;

    private T data;

    public String getResCd() {
        return resCd;
    }

    public void setResCd(String resCd) {
        this.resCd = resCd;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
