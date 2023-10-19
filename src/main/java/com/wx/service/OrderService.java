package com.wx.service;

import com.github.pagehelper.PageInfo;
import com.wx.entity.Member;
import com.wx.entity.Orders;
import com.wx.entity.Product;
import com.wx.entity.Traveller;

import java.util.List;

public interface OrderService {
    PageInfo<Orders> findAllOrder(int page, int size);

    List<Product> finAllProduct();

    List<Member> finAllMember();

    List<Traveller> findAllTraveller();

    void saveOrder(Orders orders);

    void saveOrderTraveller(String id, String[] travellerId);

    void deleteOrderById(String id);

    void deleteOrderTravellerById(String id);

    void delitems(String idsStr);

    Orders findById(String id);

    Orders editById(String id);

    void edit(Orders orders);

    List<Product> finProduct();

    List<Member> finMember();

    String findpid(String id);

    String findmid(String id);

    Orders findoneorder(String id);

    /*List<Traveller> findTraveller(String id);*/
}
