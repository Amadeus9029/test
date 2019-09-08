package com.wuzhen.rural.dao;

import com.wuzhen.rural.pojo.Order;
import com.wuzhen.rural.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order,Integer>{
    //订单是重要数据，删除的时候给个delete状态
    public List<Order> findByUserAndStatusNotOrderByIdDesc(User user, String status);
}
