package com.wuzhen.rural.service;


import com.wuzhen.rural.dao.ReviewDAO;
import com.wuzhen.rural.pojo.Product;
import com.wuzhen.rural.pojo.Review;
import com.wuzhen.rural.pojo.User;
import com.wuzhen.rural.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewDAO reviewDAO;
    @Autowired
    UserService userService;
    public Page4Navigator<Review> list(int uid, int start, int size, int navigatePages){
        User user = userService.get(uid);
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable=new PageRequest(start,size,sort);
        Page<Review> pageFromJPA= reviewDAO.findByUser(user,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public List<Review> list(){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        return reviewDAO.findAll(sort);
    }

    public List<Review> list(Product product){
        List<Review> result =  reviewDAO.findByProduct(product);
        return result;
    }

    //添加
    public void add(Review property){
        reviewDAO.save(property);
    }
    //删除
    public void delete(int id){
        reviewDAO.deleteById(id);
    }
    //编辑
    public Review get(int id){
        return reviewDAO.getOne(id);
    }
    //修改
    public void update(Review bean) {
        reviewDAO.save(bean);
    }
    public List<Review> list(User user){
        return reviewDAO.findByUser(user);
    }
}

