package com.wuzhen.rural.dao;


import com.wuzhen.rural.pojo.Product;
import com.wuzhen.rural.pojo.Review;
import com.wuzhen.rural.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewDAO extends JpaRepository<Review,Integer>{
    List<Review> findByUser(User user);
    List<Review> findByProduct(Product product);
    Page<Review> findByUser(User user, Pageable pageable);
}
