package com.wuzhen.rural.service;

import com.wuzhen.rural.dao.PropertyDAO;
import com.wuzhen.rural.pojo.Category;
import com.wuzhen.rural.pojo.Property;
import com.wuzhen.rural.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    @Autowired
    PropertyDAO propertyDAO;
    @Autowired CategoryService categoryService;
    public Page4Navigator<Property> list(int cid, int start, int size, int navigatePages){
        Category category = categoryService.get(cid);
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable=new PageRequest(start,size,sort);

        Page<Property> pageFromJPA=propertyDAO.findByCategory(category,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public List<Property> list(){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        return propertyDAO.findAll(sort);
    }
    //添加
    public void add(Property property){
        propertyDAO.save(property);
    }
    //删除
    public void delete(int id){
        propertyDAO.deleteById(id);
    }
    //编辑
    public Property get(int id){
        Property c=propertyDAO.getOne(id);
        return c;
    }
    //修改
    public void update(Property bean) {
        propertyDAO.save(bean);
    }

    public List<Property> listByCategory(Category category){
        return propertyDAO.findByCategory(category);
    }
}

