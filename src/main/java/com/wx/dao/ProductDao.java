package com.wx.dao;


import com.wx.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 产品持久层接口
 * */
public interface ProductDao {
    @Insert("insert into product values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    int addProduct(Product product);

    @Select("select productName from product where productNum = #{productNum}")
    String checkProductNum(String productNum);

    @Select("select * from product")
    List<Product> findAllProducts();

    @Delete("delete from product where id = #{id}")
    int deleteById(String id);

    @Select("select * from product where id = #{id}")
    Product editById(String id);

    @Update("update product set productNum = #{productNum},productName = #{productName},cityName = #{cityName},departureTime = #{departureTime},productPrice = #{productPrice},productDesc = #{productDesc},productStatus=#{productStatus} where id = #{id}")
    void edit(Product product);

    @Delete("delete from product where id = #{id}")
    void selectDelete(String id);

    @Select("select * from product where id = #{id}")
    Product findProduct(String id);
}
