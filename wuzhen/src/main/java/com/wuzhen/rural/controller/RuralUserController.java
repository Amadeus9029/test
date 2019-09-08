package com.wuzhen.rural.controller;

import com.wuzhen.rural.pojo.User;
import com.wuzhen.rural.service.UserService;
import com.wuzhen.rural.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuralUserController {
	@Autowired
    UserService userService;

    @GetMapping("/users")
    public Page4Navigator<User> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
    	start = start<0?0:start;
    	Page4Navigator<User> page = userService.list(start,size,5);
        return page;
    }
    @GetMapping(value = "users/{id}")
    public User  get(@PathVariable("id") int id) throws Exception{
        return userService.get(id);
    }
}

