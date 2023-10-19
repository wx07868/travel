package com.wx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.dao.OrderDao;
import com.wx.entity.Member;
import com.wx.entity.Orders;
import com.wx.entity.Product;
import com.wx.entity.Traveller;
import com.wx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public PageInfo<Orders> findAllOrder(int page, int size) {
        PageHelper.startPage(page,size);

        List<Orders> orders = orderDao.findAllOrder();


        PageInfo<Orders> pageInfo = new PageInfo<>(orders);

        return pageInfo;
    }

    @Override
    public List<Product> finAllProduct() {
        return orderDao.finAllProduct();
    }

    @Override
    public List<Member> finAllMember() {
        return orderDao.finAllMember();
    }

    @Override
    public List<Traveller> findAllTraveller() {
        return orderDao.findAllTraveller();
    }

    @Override
    public void saveOrder(Orders orders) {


        System.out.println(orders);

        orderDao.saveOrder(orders);


    }

    @Override
    public void saveOrderTraveller(String id, String[] travellerId) {

        for (String s : travellerId) {
            System.out.println(s);
            orderDao.saveOrderTraveller(id,s);
        }

    }

    @Override
    public void deleteOrderById(String id) {
        orderDao.deleteOrderById(id);
    }

    @Override
    public void deleteOrderTravellerById(String id) {
        orderDao.deleteOrderTravellerById(id);
    }

    @Override
    public void delitems(String idsStr) {
        String[] idsArr = idsStr.replace(",", " ").split(" ");
        for (String id : idsArr) {
            System.out.println(id);
            // 执行删除操作
            orderDao.delitemsOrderById(id);
            orderDao.delitemsOrderTravellerById(id);
        }
    }

    @Override
    public Orders findById(String id) {
        return orderDao.findById(id);
    }

    @Override
    public Orders editById(String id) {

        System.out.println("id"+id);
        return orderDao.editById(id);
    }

    @Override
    public void edit(Orders orders) {
        orderDao.edit(orders);
    }

    @Override
    public List<Product> finProduct() {
        List<Product> products = orderDao.finProduct();
        for (Product product : products) {
            System.out.println(product);
        }
        return products;
    }

    @Override
    public List<Member> finMember() {

        List<Member> members = orderDao.finMember();
        for (Member member : members) {
            System.out.println(member);
        }
        return members;
    }

    @Override
    public String findpid(String id) {
        return orderDao.findpid(id);
    }

    @Override
    public String findmid(String id) {
        return orderDao.findmid(id);
    }

    @Override
    public Orders findoneorder(String id) {
        Orders orders = orderDao.findoneorder(id);

        System.out.println(orders);

        return orders;
    }

    /*@Override
    public List<Traveller> findTraveller(String id) {
        return orderDao.findTraveller(id);
    }*/
}
