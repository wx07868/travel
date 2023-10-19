package com.wx.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * (OrderTraveller)实体类
 *
 * @author makejava
 * @since 2023-10-11 11:34:06
 */
@Alias("OrderTraveller")
@Data
public class OrderTraveller implements Serializable {
    private static final long serialVersionUID = -31079618955314194L;

    private String orderid;

    private String travellerid;




}

