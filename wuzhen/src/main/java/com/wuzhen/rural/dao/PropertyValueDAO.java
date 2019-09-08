package com.wuzhen.rural.dao;


import com.wuzhen.rural.pojo.Product;
import com.wuzhen.rural.pojo.Property;
import com.wuzhen.rural.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyValueDAO extends JpaRepository<PropertyValue,Integer>{

	List<PropertyValue> findByProductOrderByIdDesc(Product product);
	PropertyValue getByPropertyAndProduct(Property property, Product product);

	
	
	
}
