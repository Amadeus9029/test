package com.wuzhen.rural.controller;


import com.wuzhen.rural.pojo.Bill;
import com.wuzhen.rural.service.BillService;
import com.wuzhen.rural.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class BillController {
    @Autowired
    BillService billService;

    @GetMapping(value = "/users/{uid}/bills")
    public Page4Navigator<Bill> list(@PathVariable("uid")  int uid, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception{
       start=start<0?0:start;
       Page4Navigator<Bill> page=billService.list(uid,start,size,5);
        return page;
    }
    @PostMapping(value = "/bills")
    public Object add(@RequestBody Bill bean ) throws Exception{
        billService.add(bean);
        return  bean;
    }

    @DeleteMapping(value = "/bills/{id}")
    public String delete(@PathVariable("id") int id) throws Exception{
        billService.delete(id);
        return  null;
    }
    @GetMapping("/bills/{id}")
    public Bill get(@PathVariable("id") int id) throws Exception {
        Bill bill= billService.get(id);
        return bill;
    }
    @PutMapping(value = "/bills")
    public Object update(@RequestBody Bill bill) throws Exception {
        billService.update(bill);
        return bill;
    }
}
