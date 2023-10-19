package com.wx.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("Users")
@Data
public class Users {
    private int id;
    private String username;
    private String email;
    private String phoneNum;
    private String password;
    private int status;
    private String statusStr;
    private List<Role> roles;


    public String getStatusStr() {
        if (status == 0){
            statusStr = "关闭";
        }else  if (status == 1){
            statusStr = "开启";
        }
        return statusStr;
    }
}
