package com.wx.entity;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class SysLog {
    private int id;
    private Date visitTime;
    private String visitTimeStr;
    private String username;
    private String ip;
    private String url;
    private long executionTime;
    private String method;


    public String getVisitTimeStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (null != visitTime) {
            visitTimeStr = format.format(visitTime);
        }
        return visitTimeStr;
    }

}
