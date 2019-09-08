package com.wuzhen.rural.controller;


import com.wuzhen.rural.pojo.Record;
import com.wuzhen.rural.service.RecordService;
import com.wuzhen.rural.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class RecordController {
    @Autowired
    RecordService recordService;

    @GetMapping(value = "/users/{uid}/records")
    public Page4Navigator<Record> list(@PathVariable("uid")  int uid, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception{
       start=start<0?0:start;
       Page4Navigator<Record> page=recordService.list(uid,start,size,5);
        return page;
    }
    @PostMapping(value = "/records")
    public Object add(@RequestBody Record bean ) throws Exception{
        recordService.add(bean);
        return  bean;
    }

    @DeleteMapping(value = "/records/{id}")
    public String delete(@PathVariable("id") int id) throws Exception{
        recordService.delete(id);
        return  null;
    }
    @GetMapping("/records/{id}")
    public Record get(@PathVariable("id") int id) throws Exception {
        Record record= recordService.get(id);
        return record;
    }
    @PutMapping(value = "/records")
    public Object update(@RequestBody Record record) throws Exception {
        recordService.update(record);
        return record;
    }
}
