package com.wx.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * (Traveller)实体类
 *
 * @author makejava
 * @since 2023-10-11 11:34:06
 */
@Alias("Traveller")
@Data
public class Traveller implements Serializable {
    private static final long serialVersionUID = -43334241970244124L;

    private String id;
    private String name;
    private String sex;
    private String phoneNum;
    private Integer credentialsType;
    private String credentialsTypeStr;
    private String credentialsNum;
    private Integer travellerType;
    private String travellerTypeStr;


    public String getCredentialsTypeStr() {
        // 证件类型 0：身份证 1：护照 2：军官证
        if (credentialsType == 0) {
            credentialsTypeStr = "身份证";
        } else if (credentialsType == 1) {
            credentialsTypeStr = "护照";
        } else if (credentialsType == 2) {
            credentialsTypeStr = "军官证";
        }
        return credentialsTypeStr;
    }

    public String getTravellerTypeStr() {
        // 证件类型 0：成人 1：儿童
        if (travellerType == 0) {
            travellerTypeStr = "成人";
        } else if (travellerType == 1) {
            travellerTypeStr = "儿童";
        }

        return travellerTypeStr;
    }


}

