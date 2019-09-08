package com.wuzhen.rural.controller;

import com.wuzhen.rural.pojo.Product;
import com.wuzhen.rural.service.ProductService;
import com.wuzhen.rural.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/categories/{cid}/products")
    public Page4Navigator<Product> list(@PathVariable("cid") int cid,@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception{
       start=start<0?0:start;
       Page4Navigator<Product> page=productService.list(cid,start,size,5);
        return page;
    }
    @PostMapping(value = "/products")
    public Object add(@RequestBody Product bean) throws Exception{
        productService.add(bean);
        return bean;
    }

    @DeleteMapping(value = "/products/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) throws Exception{
        productService.delete(id);
        return  null;
    }
    @GetMapping("/products/{id}")
    public Product get(@PathVariable("id") int id) throws Exception {
        Product bean=productService.get(id);
        return bean;
    }
    @PutMapping("/products")
    public Object update(@RequestBody Product bean) throws Exception {
        productService.update(bean);
        return bean;
    }
}
