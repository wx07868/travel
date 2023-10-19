package com.wx.controller;

import com.github.pagehelper.PageInfo;
import com.wx.entity.Product;
import com.wx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

/**
 *产品控制层
 *
 * */
@Controller
@RequestMapping("/admin/product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    /*添加产品信息*/
    @RequestMapping("addProduct")
    public String addProduct(Product product){

        //设置随机的产品id
        product.setId(UUID.randomUUID().toString());

        //调用业务层添加方法
        productService.addProduct(product);

        //响应信息


        return "admin/product-add";

    }

    @RequestMapping("checkProductNum")
    @ResponseBody
    public int checkProductNum(String productNum){

        boolean bl = productService.checkProductNum(productNum);

        if (bl){
            return 1;
        }else {
            return 0;
        }

    }

    @RequestMapping("findAllProducts")
    public ModelAndView findAllProducts(@RequestParam(name="page",defaultValue = "1")Integer page,
                                        @RequestParam(name="size",defaultValue = "5")Integer size){

        ModelAndView mv = new ModelAndView();
        PageInfo<Product> product = productService.findAllProducts(page,size);

        mv.addObject("pageInfo",product);

        mv.setViewName("admin/product-list");

        return mv;
    }


    @RequestMapping("deleteById")
    public String deleteById(@RequestParam(name = "id") String id){


        boolean bl = productService.deleteById(id);




        return "redirect:findAllProducts.do?page=1&size=5";


    }

    @RequestMapping("editById")
    public ModelAndView editById(@RequestParam(name = "id") String id){

        ModelAndView modelAndView = new ModelAndView();

        Product p = productService.editById(id);



        modelAndView.addObject("product" ,p);


        modelAndView.setViewName("admin/product-edit");

        return modelAndView;
    }

    @RequestMapping("edit")
    public String edit(Product product){


        productService.edit(product);
        return "redirect:findAllProducts.do?page=1&size=5";
    }


    /*批量删除*/
    @RequestMapping(value = "selectDelete.do", method = RequestMethod.POST)
    @ResponseBody
    public String selectDelete(@RequestParam(name = "ids") String ids) {
        // 执行删除操作
        productService.selectDelete(ids);

        return "redirect:findAllProducts.do?page=1&size=5";
    }

    @RequestMapping("findProduct")
    public ModelAndView findProduct(Product product,ModelAndView mv){

        Product product1 = productService.findProduct(product.getId());

        mv.addObject("product" ,product1);
        mv.setViewName("admin/product-show");

        return mv;
    }



}
