package com.wuzhen.rural.service;

import com.wuzhen.rural.dao.OrderDAO;
import com.wuzhen.rural.pojo.Order;
import com.wuzhen.rural.pojo.Record;
import com.wuzhen.rural.pojo.User;
import com.wuzhen.rural.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
	public static final String waitPay = "waitPay";
	public static final String waitDelivery = "waitDelivery";
	public static final String waitConfirm = "waitConfirm";
	public static final String waitReview = "waitReview";
	public static final String finish = "finish";
	public static final String delete = "delete";	
	
	@Autowired
    OrderDAO orderDAO;
	@Autowired
    RecordService recordService;


	public Page4Navigator<Order> list(int start, int size, int navigatePages) {
    	Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size,sort);
		Page pageFromJPA =orderDAO.findAll(pageable);
		return new Page4Navigator<>(pageFromJPA,navigatePages);
	}

	public void removeOrderFromRecord(List<Order> orders) {
		for (Order order : orders) {
			removeOrderFromRecord(order);
		}
	}


	public void removeOrderFromRecord(Order order) {
		List<Record> records= order.getRecords();
		for (Record record : records) {
			record.setOrder(null);
		}
	}

	public Order get(int oid) {
		return orderDAO.getOne(oid);
	}

	public void update(Order bean) {
		orderDAO.save(bean);
	}

	//生成订单
	@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
	public float add(Order order, List<Record> records) {
		float total = 0;
		add(order);

		if(false)
			throw new RuntimeException();

		for (Record record: records) {
			record.setOrder(order);
			recordService.update(record);
			total+=record.getProduct().getPrice()*record.getNumber();
		}
		return total;
	}
	public void add(Order order) {
		orderDAO.save(order);
	}

	public List<Order> listByUserWithoutDelete(User user) {
		List<Order> orders = listByUserAndNotDeleted(user);
		recordService.fill(orders);
		return orders;
	}


	public List<Order> listByUserAndNotDeleted(User user) {
		return orderDAO.findByUserAndStatusNotOrderByIdDesc(user, OrderService.delete);
	}

	public void cacl(Order o) {
		List<Record> records = o.getRecords();
		float total = 0;
		for (Record orderItem : records) {
			total+=orderItem.getProduct().getPrice()*orderItem.getNumber();
		}
		o.setTotal(total);
	}

}
