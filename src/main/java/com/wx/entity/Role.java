package com.wx.entity;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private int id;
    private String rolename;
    private String roledesc;

    private List<Permission> permissions;
    private List<Users> users;
}
