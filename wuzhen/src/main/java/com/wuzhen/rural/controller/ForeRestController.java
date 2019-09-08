package com.wuzhen.rural.controller;


import com.wuzhen.rural.pojo.*;
import com.wuzhen.rural.service.*;
import com.wuzhen.rural.util.ImageUtil;
import com.wuzhen.rural.util.Page4Navigator;
import com.wuzhen.rural.util.Result;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ForeRestController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    RecordService recordService;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionRecordService questionRecordService;
    @Autowired
    OrderService orderService;
    //注册
    @PostMapping("/foreregister")
    public Object register(@RequestParam(value="name") String name,@RequestParam(value="password") String password){
        User user=new User();
        name= HtmlUtils.htmlEscape(name);
        user.setName(name);
        user.setPoints(20);
        boolean exist=userService.isExit(name);
        if (exist){
            String message="用户名已经存在，不能使用";
            return Result.fail(message);
        }
            user.setPassword(password);
            userService.add(user);
            return Result.success();
    }
    //登录
    @PostMapping("/forelogin")
    public Object login(@RequestParam(value="name") String name,@RequestParam(value="password") String password, HttpSession session){
        name = HtmlUtils.htmlEscape(name);
        User user=userService.get(name,password);
        if (user==null){
            String message="账号密码错误";
            return Result.fail(message);
        }else {
            session.setAttribute("user", user);
            return Result.success();
        }
    }
    //用户的更新操作
    @PostMapping("/foreUpdate")
    public Object update(@RequestParam(value="name") String name,@RequestParam(value="image") MultipartFile image,HttpServletRequest request) throws IOException {
        System.out.println(image);
        User user=new User();
        user.setName(name);
        userService.update(user);
        if(image!=null) {
            saveOrUpdateImageFile(user, image, request);
        }
        return user;
    }
    private void saveOrUpdateImageFile(@RequestBody User user,@RequestBody MultipartFile image, HttpServletRequest request) throws IOException {
        File imageFolder=new File(request.getServletContext().getRealPath("img/user"));
        File file = new File(imageFolder,user.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }
    //用户的评论民俗以及购买过的商品展示
    @GetMapping("/userProducts")
    public Object userProducts(HttpSession session) {
        User user =(User)  session.getAttribute("user");
        System.out.println("getSession");
        System.out.println(user);
        if(null!=user)
        {
            List<Record> records=recordService.list(user);
            List<Review> reviews=reviewService.list(user);
            List<Question> questions=questionService.list();
            System.out.println(records);
            Map<String,Object> map= new HashMap<>();
            map.put("questions", questions);
            map.put("records", records);
            map.put("reviews", reviews);
            map.put("user",user);
            return Result.success(map);
        }
        return Result.fail("未登录");
    }
    //首页展示(分类 产品 )
    @GetMapping("/foreCategories")
    public List<Category> list(){
        return  categoryService.list();
    }
    @GetMapping("/foreCategories/{cid}/foreProducts")
    public Page4Navigator<Product> list(@PathVariable("cid") int cid, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception{
        start=start<0?0:start;
        Page4Navigator<Product> page=productService.list(cid,start,size,5);
        productImageService.setFirstProdutImages(page.getContent());
        return page;
    }
    @GetMapping("/foreProducts/{pid}")
    public Object product(@PathVariable("pid") int pid,HttpSession session) {
        User user=null;
        try {
             user= (User) session.getAttribute("user");
        }
        catch (Exception e){

        }
        Product product = productService.get(pid);
        List<ProductImage> productSingleImages = productImageService.listSingleProductImages(product);
        List<ProductImage> productDetailImages = productImageService.listDetailProductImages(product);
        product.setProductSingleImages(productSingleImages);
        product.setProductDetailImages(productDetailImages);
        List<Product> products=productService.list(product.getCategory().getId());
        System.out.println("--------"+products);
        List<PropertyValue> pvs = propertyValueService.list(product);
        List<Review> reviews = reviewService.list(product);
        //productService.setSaleAndReviewNumber(product);
        productImageService.setFirstProdutImage(product);
        Map<String,Object> map= new HashMap<>();

        map.put("products",products);
        map.put("product", product);
        map.put("pvs", pvs);
        map.put("reviews", reviews);
            map.put("user", user);
        return Result.success(map);
    }
    @GetMapping("forecheckLogin")
    public Object checkLogin( HttpSession session) {
        User user =(User)  session.getAttribute("user");
        if(null!=user)
            return Result.success();
        return Result.fail("未登录");
    }
    //用户评价
    @PostMapping("foredoreview")
    public Object doreview( HttpSession session,int pid,String content) {
        Product p = productService.get(pid);
        content = HtmlUtils.htmlEscape(content);
        User user =(User)  session.getAttribute("user");
        Review review = new Review();
        review.setContent(content);
        review.setProduct(p);
        //review.setCreateDate(new Date());
        review.setUser(user);
        reviewService.add(review);
        return Result.success();
    }
    //首页交互(购买)
    @PostMapping("/foreBuyOne")
    public Object buyOne(int pid, HttpSession session) {
        Product product=productService.get(pid);
        User user =(User)  session.getAttribute("user");
        Record record=new Record(product,user);
        float userPoints=user.getPoints()-product.getPrice();
        user.setPoints(userPoints);
        session.setAttribute("user",user);
        userService.update(user);
        recordService.add(record);
        return  Result.success();
}

//购物车展示
@GetMapping("/showRecord")
public Object showRecord(HttpSession session) {
    User user =(User)  session.getAttribute("user");
    List<Record> records = recordService.listByUser(user);
    productImageService.setFirstProdutImagesOnOrderItems(records);
    System.out.println("_______"+records);
    return  records;
}

    @GetMapping("/changeNum")
    public Object changeNum( HttpSession session, @RequestParam(value="pid") Integer pid, @RequestParam(value="num") Integer num) {
        User user =(User)  session.getAttribute("user");
        if(null==user)
            return Result.fail("未登录");
        List<Record> records = recordService.listByUser(user);
        for (Record record : records) {
            if(record.getProduct().getId()==pid){
                record.setNumber(num);
                recordService.update(record);
                break;
            }
        }
        return Result.success();
    }
    //删除商品
    @DeleteMapping("delProduct")
    public Object delProduct(HttpSession session,@RequestParam(value = "rid") int rid){
        User user =(User)  session.getAttribute("user");
        if(null==user)
            return Result.fail("未登录");
        recordService.delete(rid);
        return Result.success();
    }

    @GetMapping("forebuyone")
    public Object buyone(int pid, int num, HttpSession session) {
        return addRecord(pid,num,session);
    }
//买一个，添加到购物车 //兑换商品
@PostMapping("/addRecord")
public Object addRecord(int pid,int num, HttpSession session) {
    Product product=productService.get(pid);
    int rid=0;
    User user =(User)  session.getAttribute("user");
    boolean found = false;
    List<Record> records = recordService.listByUser(user);
    for (Record record : records) {
        if(record.getProduct().getId()==product.getId()){
            record.setNumber(record.getNumber()+num);
            recordService.update(record);
            found = true;
            rid = record.getId();
            break;
        }
    }
    if(!found){
        Record record = new Record();
        record.setUser(user);
        record.setProduct(product);
        record.setNumber(num);
        recordService.add(record);
        rid = record.getId();
    }
    session.setAttribute("user",user);
    userService.update(user);
    return rid;
}
//买一个的结算页面
@GetMapping("forebuy")
public Object forebuy(@RequestParam(value = "oid") Integer[] products,HttpSession session){
    List<Record> records = new ArrayList<>();
    float total = 0;

    for (Integer id : products) {
        Record record= recordService.get(id);
        total +=record.getProduct().getPrice()*record.getNumber();
        records.add(record);
    }

    productImageService.setFirstProdutImagesOnOrderItems(records);

    session.setAttribute("records", records);

    Map<String,Object> map = new HashMap<>();
    map.put("records", records);
    map.put("total", total);
    return Result.success(map);
}
//生成订单的时候，删除购物车里面的数据
@PostMapping("forecreateOrder")
public Object createOrder(@RequestBody Order order,HttpSession session){
    User user =(User)  session.getAttribute("user");
    if(null==user)
        return Result.fail("未登录");
    String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);
    order.setOrderCode(orderCode);
    order.setCreateDate(new Date());
    order.setUser(user);
    order.setStatus(OrderService.waitPay);

    List<Record> records= (List<Record>)  session.getAttribute("records");
    //添加order
    float total =orderService.add(order,records);
    //删除已经购买的购物车里的数据
    for(Record record:records){
        if(record.getOrder().getId()==order.getId()){
            recordService.delete(record.getId());
        }
    }
    Map<String,Object> map = new HashMap<>();
    map.put("orderId",order.getId());
    map.put("total", total);
    return Result.success(map);
}
//购买成功
    @GetMapping("forepayed")
    public Object payed(int oid) {
        Order order = orderService.get(oid);
        order.setStatus(OrderService.waitDelivery);
        order.setPayDate(new Date());
        orderService.update(order);
        return order;
    }

//添加
@PostMapping("/addQuestionRecord")
public Object addQuestionRecord(HttpSession session,@RequestBody Question_Record qr) {
    User user= (User) session.getAttribute("user");
    Question_Record questionRecord=new Question_Record();
    questionRecord.setAcnumber(qr.getAcnumber());
    questionRecord.setErunmber(qr.getErunmber());
    float points= user.getPoints()+qr.getAcnumber()*10;
    user.setPoints(points);
    userService.update(user);
    questionRecord.setUser(user);
    questionRecordService.add(questionRecord);
    return  Result.success();
}
}