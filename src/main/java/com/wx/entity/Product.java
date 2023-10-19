package com.wx.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2023-10-11 11:33:17
 */
@Alias("Product")
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = -19175501199500546L;

    private String id;

    private String productNum;

    private String productName;

    private String cityName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date departureTime;

    private Integer productPrice;

    private String productDesc;

    private Integer productStatus;

    private String productStatusStr;

    private String departureTimeStr;

    public String getDepartureTimeStr() {
        // 对日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (null != departureTime) {
            departureTimeStr = dateFormat.format(departureTime);
        }
        return departureTimeStr;
    }

    public String getProductStatusStr() {

        if (productStatus == null) {
            return "";
        }

        if (productStatus == 0){
            productStatusStr = "关闭";
        } else if(productStatus == 1){
            productStatusStr = "开启";
        }

        return productStatusStr;
    }

}

