package com.wuzhen.rural.dao;


import com.wuzhen.rural.pojo.Bill;
import com.wuzhen.rural.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BillDAO extends JpaRepository<Bill,Integer>{
    Page<Bill> findByUser(User user, Pageable pageable);
}
