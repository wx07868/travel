package com.wx.dao;

import com.wx.entity.Member;
import com.wx.entity.Orders;
import com.wx.entity.Product;
import com.wx.entity.Traveller;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderDao {
    /*@Select("select * from orders,product where orders.productId = product.id")
    @Results(value = {
            @Result(column = "p")
    })*/
    List<Orders> findAllOrder();

    @Select("select * from product ")
    List<Product> finAllProduct();

    @Select("select * from member ")
    List<Member> finAllMember();

    @Select("select * from traveller ")
    List<Traveller> findAllTraveller();




    @Insert("insert into orders values(#{id},#{orderNum},#{orderTime},#{peopleCount},#{orderDesc},#{payType},#{orderStatus},#{productId},#{memberId})")
    void saveOrder(Orders orders);

    @Insert("insert into order_traveller values(#{id},#{s})")
    void saveOrderTraveller(@Param("id") String id, @Param("s") String s);

    @Delete("delete from orders where id = #{id}")
    void deleteOrderById(String id);

    @Delete("delete from order_traveller where orderId = #{id}")
    void deleteOrderTravellerById(String id);



    @Delete("delete from orders where id = #{id}")
    void delitemsOrderById(String id);

    @Delete("delete from order_traveller where orderId = #{id}")
    void delitemsOrderTravellerById(String id);

    /*@Select("select * from orders where id = #{id}")*/
    Orders findById(String id);

    /*@Select("select * from orders where id = #{id}")*/
    Orders editById(String id);

    @Update("update orders set orderNum = #{orderNum},orderTime = #{orderTime},peopleCount = #{peopleCount},orderDesc = #{orderDesc},payType = #{payType},orderStatus = #{orderStatus},productId=#{productId},memberId=#{memberId} where id = #{id}")
    void edit(Orders orders);

    @Select("select * from product")
    List<Product> finProduct();

    @Select("select * from member")
    List<Member> finMember();

    @Select("select productId from orders where id = #{id}")
    String findpid(String id);

    @Select("select memberId from orders where id = #{id}")
    String findmid(String id);

    @Select("select * from orders where id = #{id}")
    Orders findoneorder(String id);

    /*List<Traveller> findTraveller(String id);*/
}
