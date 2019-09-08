package com.wuzhen.rural.controller;


import com.wuzhen.rural.pojo.Review;
import com.wuzhen.rural.service.ReviewService;
import com.wuzhen.rural.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping(value = "/users/{uid}/reviews")
    public Page4Navigator<Review> list(@PathVariable("uid")  int uid, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception{
       start=start<0?0:start;
       Page4Navigator<Review> page=reviewService.list(uid,start,size,5);
        return page;
    }
    @PostMapping(value = "/reviews")
    public Object add(@RequestBody Review bean ) throws Exception{
        reviewService.add(bean);
        return  bean;
    }

    @DeleteMapping(value = "/reviews/{id}")
    public String delete(@PathVariable("id") int id) throws Exception{
        reviewService.delete(id);
        return  null;
    }
    @GetMapping("/reviews/{id}")
    public Review get(@PathVariable("id") int id) throws Exception {
        Review review= reviewService.get(id);
        return review;
    }
    @PutMapping(value = "/reviews")
    public Object update(@RequestBody Review review) throws Exception {
        reviewService.update(review);
        return review;
    }
}
