package com.wx.controller;

import com.github.pagehelper.PageInfo;
import com.wx.entity.Member;
import com.wx.entity.Orders;
import com.wx.entity.Product;
import com.wx.entity.Traveller;
import com.wx.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/order/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("findAllOrder")
    public ModelAndView findAllOrder(@RequestParam(name="page",defaultValue = "1")Integer page,
                                     @RequestParam(name="size",defaultValue = "5")Integer size){

        ModelAndView mv = new ModelAndView();
        PageInfo<Orders> order = orderService.findAllOrder(page,size);

        mv.addObject("pageInfo",order);

        mv.setViewName("admin/order-list");
        return mv;

    }


    @RequestMapping("findPMT")
    public ModelAndView findPMT(ModelAndView mv){

        //查询所有产品
        List<Product> products = orderService.finAllProduct();

        //查询所有会员信息
        List<Member> members= orderService.finAllMember();

        List<Traveller> travellers = orderService.findAllTraveller();

        mv.addObject("productList",products);

        mv.addObject("memberList",members);

        mv.addObject("travellerList",travellers);

        mv.setViewName("admin/order-add");

        return mv;
    }


    @RequestMapping("saveOrder")
    public String saveOrder(Orders orders, @Param("travellerId") String[] travellerId){


        orders.setId(UUID.randomUUID().toString());

        orderService.saveOrder(orders);

        orderService.saveOrderTraveller(orders.getId(),travellerId);

        return "forward:/admin/order/findPMT.do";

    }


    @RequestMapping("deleteById")
    public String deleteById(String id){



        orderService.deleteOrderById(id);

        orderService.deleteOrderTravellerById(id);

        return "redirect:findAllOrder.do?page=1&size=5";

    }

    @RequestMapping(value = "delitems", method = RequestMethod.POST)
    @ResponseBody
    public String delitems(HttpServletRequest request) {
        String idsStr = request.getParameter("ids");
        System.out.println(idsStr);

        // 执行删除操作
        orderService.delitems(idsStr);

        return "redirect:findAllOrder.do?page=1&size=5";
    }


    @RequestMapping("findById")
    public ModelAndView findById(String id,ModelAndView mv){

        System.out.println(id);

        Orders orders = orderService.findById(id);

        System.out.println(orders);

        mv.addObject("order" ,orders);
        mv.setViewName("admin/order-show");

        return mv;
    }


    @RequestMapping("editById")
    public ModelAndView editById(@RequestParam(name = "id") String id){

        ModelAndView mv = new ModelAndView();

        Orders orders1 = orderService.findoneorder(id);

        //查询产品
        List<Product> products = orderService.finProduct();

        //查询会员信息
        List<Member> members= orderService.finMember();

        mv.addObject("productList",products);

        mv.addObject("memberList",members);

        mv.addObject("order",orders1);

        mv.setViewName("admin/order-edit");

        return mv;
    }

    @RequestMapping("edit")
    public String edit(Orders orders){

        orderService.edit(orders);


        return "redirect:findAllOrder.do?page=1&size=5";
    }






}
