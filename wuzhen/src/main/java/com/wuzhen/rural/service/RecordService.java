package com.wuzhen.rural.service;


import com.wuzhen.rural.dao.RecordDAO;
import com.wuzhen.rural.pojo.Order;
import com.wuzhen.rural.pojo.Product;
import com.wuzhen.rural.pojo.Record;
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
public class RecordService {
    @Autowired
    RecordDAO recordDAO;
    @Autowired UserService userService;
    @Autowired
    ProductImageService productImageService;
    public Page4Navigator<Record> list(int uid, int start, int size, int navigatePages){
        User user = userService.get(uid);
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable=new PageRequest(start,size,sort);

        Page<Record> pageFromJPA=recordDAO.findByUser(user,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public List<Record> list(){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        return recordDAO.findAll(sort);
    }
    public List<Record> list(User user){
        return recordDAO.findByUser(user);
    }
    //添加
    public void add(Record record){
        recordDAO.save(record);
    }
    //删除
    public void delete(int id){
        recordDAO.deleteById(id);
    }
    //编辑
    public Record get(int id){
        return recordDAO.getOne(id);
    }
    //修改
    public void update(Record bean) {
        recordDAO.save(bean);
    }
    public List<Record> listByUser(User user) {
        return recordDAO.findByUser(user);
    }
    public List<Record> listByProduct(Product product) {
        return recordDAO.findByProduct(product);
    }
    public List<Record> listByOrder(Order order) {
        return recordDAO.findByOrderOrderByIdDesc(order);
    }
    public int getSaleCount(Product product) {
        List<Record> ois =listByProduct(product);
        int result =0;
        for (Record oi : ois) {
            if(null!=oi.getOrder())
                if(null!= oi.getOrder() && null!=oi.getOrder().getPayDate())
                    result+=oi.getNumber();
        }
        return result;
    }
    public void fill(Order order) {
        List<Record> orderItems = listByOrder(order);
        float total = 0;
        int totalNumber = 0;
        for (Record oi :orderItems) {
            total+=oi.getNumber()*oi.getProduct().getPrice();
            totalNumber+=oi.getNumber();
            productImageService.setFirstProdutImage(oi.getProduct());
        }
        order.setTotal(total);
        order.setRecords(orderItems);
        order.setTotalNumber(totalNumber);
        order.setRecords(orderItems);
    }
    public void fill(List<Order> orders) {
        for (Order order : orders)
            fill(order);
    }

}

