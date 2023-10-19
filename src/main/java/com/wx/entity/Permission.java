package com.wx.entity;

import lombok.Data;

import java.util.List;

@Data
public class Permission {
        private int id;
        private String permissionName;
        private String url;
        private List<Role> roles;

}
