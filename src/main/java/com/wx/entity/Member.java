package com.wx.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * (Member)实体类
 *
 * @author makejava
 * @since 2023-10-11 11:34:06
 */
@Alias("Member")
@Data
public class Member implements Serializable {
    private static final long serialVersionUID = 890629605189463067L;

    private String id;

    private String name;

    private String nickname;

    private String phoneNum;

    private String email;


}

