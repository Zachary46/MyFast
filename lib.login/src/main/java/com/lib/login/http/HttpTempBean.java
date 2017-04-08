package com.lib.login.http;

import java.io.Serializable;

public class HttpTempBean implements Serializable {

    public int Status;
    public String Message;

    public HttpBean toLzyResponse() {
        HttpBean httpBean = new HttpBean();
        httpBean.Status = Status;
        httpBean.Message = Message;
        return httpBean;
    }
}