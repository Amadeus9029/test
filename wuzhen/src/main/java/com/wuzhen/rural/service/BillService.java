package com.wuzhen.rural.service;


import com.wuzhen.rural.dao.BillDAO;
import com.wuzhen.rural.pojo.Bill;
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
public class BillService {
    @Autowired
    BillDAO billDAO;
    @Autowired UserService userService;
    public Page4Navigator<Bill> list(int uid, int start, int size, int navigatePages){
        User user = userService.get(uid);
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable=new PageRequest(start,size,sort);

        Page<Bill> pageFromJPA=billDAO.findByUser(user,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public List<Bill> list(){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        return billDAO.findAll(sort);
    }
    //添加
    public void add(Bill property){
        billDAO.save(property);
    }
    //删除
    public void delete(int id){
        billDAO.deleteById(id);
    }
    //编辑
    public Bill get(int id){
        return billDAO.getOne(id);
    }
    //修改
    public void update(Bill bean) {
        billDAO.save(bean);
    }
}

