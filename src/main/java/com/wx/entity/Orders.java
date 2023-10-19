package com.wx.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (Orders)实体类
 *
 * @author makejava
 * @since 2023-10-11 11:34:06
 */
@Alias("Orders")
@Data
public class Orders implements Serializable {
    private static final long serialVersionUID = -31541168749417318L;

    private String id;
    private String orderNum;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date orderTime;
    private String orderTimeStr;
    private int orderStatus;
    private String orderStatusStr;
    private int peopleCount;
    private Product product;
    private List<Traveller> travellers;
    private Member member;
    private Integer payType;
    private String payTypeStr;
    private String orderDesc;
    //用于添加订单时，添加产品，会员
    private String productId;
    private String memberId;

    public String getOrderTimeStr() {
        // 对日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        orderTimeStr =  dateFormat.format(orderTime);
        return orderTimeStr;
    }

    public String getPayTypeStr() {
        if (payType == 0){
            payTypeStr = "支付宝";
        } else if(payType == 1){
            payTypeStr = "微信";
        }else if(payType == 2){
            payTypeStr = "其他";
        }
        return payTypeStr;
    }


    public String getOrderStatusStr() {
        if (orderStatus == 0){
            orderStatusStr = "未支付";
        } else if(orderStatus == 1){
            orderStatusStr = "已支付";
        }
        return orderStatusStr;
    }


}

