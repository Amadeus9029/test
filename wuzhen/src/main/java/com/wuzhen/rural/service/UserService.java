package com.wuzhen.rural.service;


import com.wuzhen.rural.dao.UserDAO;
import com.wuzhen.rural.pojo.User;
import com.wuzhen.rural.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
    UserDAO userDAO;

	public Page4Navigator<User> list(int start, int size, int navigatePages) {
    	Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size,sort);
		Page pageFromJPA =userDAO.findAll(pageable);
		return new Page4Navigator<>(pageFromJPA,navigatePages);
	}

	public User get(int id){
		return userDAO.getOne(id);
	}

	public User getByName(String name){
		return userDAO.findByName(name);
	}
	public boolean isExit(String name){
		User user=getByName(name);
		return null!=user;
	}

	public void add(User user){
		userDAO.save(user);
	}

	public User get(String name,String password){
		return  userDAO.getByNameAndPassword(name,password);
	}
	public User update(User user){
		return userDAO.save(user);
	}
}
