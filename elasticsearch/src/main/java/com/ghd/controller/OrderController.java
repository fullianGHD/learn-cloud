package com.ghd.controller;

import com.ghd.model.Order;
import com.ghd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/testes")
    public String testes(){
        return "asdasd";
    }

    /**
     * 保存一条索引
     * @param
     * @return
     */
    @PostMapping("/save")
    public Object save(){
        Order o = new Order();
        o.setId("3");
        o.setOrderNum("202030103456643");
        o.setPayTime(new Date().getTime());
        o.setPrice(new BigDecimal(104421.2));
        o.setProductName("孚链要凉了，一首凉凉送给你们");
        o.setUsername("大全为");
        orderService.save(o);
        return "success";
    }


    /**
     * 查所有
     * @return
     */
    @GetMapping("/getAll")
    public Object get(){
        Iterable<Order> all = orderService.findAll();
        all.forEach(o -> System.out.println(o));
        return all;
    }

    /**
     * 根据id查
     * @param id
     * @return
     */
    @GetMapping("/getByid/{id}")
    public Object getOne(@PathVariable("id")String id){
        try{
            Optional<Order> byId = orderService.findById(id);
            return byId.get();
        }catch (Exception e){
            return "无值";
        }
    }

    /**
     * 删除
     * @return
     */
    @GetMapping("/delete")
    public Object delete(){
        //删除所有文档
        orderService.deleteAll();
        //根据对象删除，但是得有id
        //orderService.delete(order);
        //根据id删除
        //orderService.deleteById(id);
        return "";
    }

    /**
     * 根据用户名 and 订单号查询
     * @param username
     * @param orderNum
     * @return
     */
    @GetMapping("/usernameAndorderNum/{username}/{ordeNum}")
    public List<Order> usernameAndorderNum(@PathVariable("username") String username, @PathVariable("orderNum") String orderNum){
        List<Order> byUsernameAndOrderNum = orderService.findByUsernameAndOrderNum(username, orderNum);
        return byUsernameAndOrderNum;
    }

    @GetMapping("/getParams")
    public List<Order> getParams(){
        String username = "11";
        String orderNum = "123";
        List<Order> byUsernameAndOrderNum = orderService.findByUsernameAndOrderNum(username, orderNum);
        return byUsernameAndOrderNum;
    }
}
