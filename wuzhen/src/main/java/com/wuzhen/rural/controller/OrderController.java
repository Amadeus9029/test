package com.wuzhen.rural.controller;

import com.wuzhen.rural.pojo.Order;
import com.wuzhen.rural.service.OrderService;
import com.wuzhen.rural.service.RecordService;
import com.wuzhen.rural.util.Page4Navigator;
import com.wuzhen.rural.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@RestController
public class OrderController {
	@Autowired
    OrderService orderService;
	@Autowired
    RecordService recordService;

    @GetMapping("/orders")
    public Page4Navigator<Order> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
    	start = start<0?0:start;
    	Page4Navigator<Order> page =orderService.list(start, size, 5);
        recordService.fill(page.getContent());
        orderService.removeOrderFromRecord(page.getContent());
        return page;
    }
    @PutMapping("deliveryOrder/{oid}")
    public Object deliveryOrder(@PathVariable int oid) throws IOException {
        Order o = orderService.get(oid);
        o.setDeliveryDate(new Date());
        o.setStatus(OrderService.waitConfirm);
        orderService.update(o);
        return Result.success();
    }
}

