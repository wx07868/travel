package com.wx.service;

import com.github.pagehelper.PageInfo;
import com.wx.entity.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    boolean checkProductNum(String productNum);

    PageInfo<Product> findAllProducts(int page, int size);

    boolean deleteById(String id);


    Product editById(String id);

    void edit(Product product);

    void selectDelete(String id);

    Product findProduct(String id);
}
