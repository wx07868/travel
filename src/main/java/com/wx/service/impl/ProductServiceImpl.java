package com.wx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.dao.ProductDao;
import com.wx.entity.Product;
import com.wx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 产品业务层
 *
 */

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public void addProduct(Product product) {

        productDao.addProduct(product);
    }

    @Override
    public boolean checkProductNum(String productNum) {
        String pn = productDao.checkProductNum(productNum);
        if (pn==null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public PageInfo<Product> findAllProducts(int page, int size) {
        PageHelper.startPage(page,size);

        List<Product> products = productDao.findAllProducts();

        PageInfo<Product> pageInfo = new PageInfo<>(products);

        return pageInfo;

    }

    @Override
    public boolean deleteById(String id) {
        int i = productDao.deleteById(id);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Product editById(String id) {
        Product product = productDao.editById(id);
        return product;
    }

    @Override
    public void edit(Product product) {
        productDao.edit(product);
    }

    @Override
    public void selectDelete(String id) {

        String[] idsArr = id.replace(",", " ").split(" ");
        for (String id1 : idsArr) {
            // 执行删除操作
            productDao.selectDelete(id1);
        }

    }

    @Override
    public Product findProduct(String id) {
        return productDao.findProduct(id);
    }


}
