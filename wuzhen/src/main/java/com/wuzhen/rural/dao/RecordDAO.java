package com.wuzhen.rural.dao;


import com.wuzhen.rural.pojo.Order;
import com.wuzhen.rural.pojo.Product;
import com.wuzhen.rural.pojo.Record;
import com.wuzhen.rural.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordDAO extends JpaRepository<Record,Integer>{
    Page<Record> findByUser(User user, Pageable pageable);
    List<Record> findByUser(User user);
    List<Record> findByProduct(Product product);

    List<Record> findByOrderOrderByIdDesc(Order order);
    List<Record> findByUserAndOrderIsNull(User user);
}
