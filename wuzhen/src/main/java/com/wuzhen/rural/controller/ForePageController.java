package com.wuzhen.rural.controller;

import com.wuzhen.rural.pojo.Category;
import com.wuzhen.rural.pojo.Product;
import com.wuzhen.rural.service.CategoryService;
import com.wuzhen.rural.service.ProductService;
import com.wuzhen.rural.service.QuestionRecordService;
import com.wuzhen.rural.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class ForePageController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionRecordService questionRecordService;
    @GetMapping(value="/frontIndex")
    public String frontIndex(){
        return "rural/front/index";
    }
    @GetMapping(value="/fooddetail")
    public String detail(){
        return "rural/front/fooddetail";
    }
    //退出登录
    @GetMapping("/forelogout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:frontIndex";
    }
    @GetMapping(value="/front_land_list")
    public String front_land_list(){
        return "rural/front/land";
    }
    @GetMapping(value="/front_landDetail_list")
    public String front_landDetail_list(){
        return "rural/front/landdetail";
    }
    @GetMapping(value="/front_sign_list")
    public String front_sign_list(){
        return "rural/front/sign";
    }
    @GetMapping(value="/front_product_list")
    public String front_product_list(){
        return "rural/front/food";
    }
    @GetMapping(value="/front_folk_list")
    public String front_folk_list(){
        return "rural/front/service";
    }
    @GetMapping(value="/front_productDetail_list")
    public String front_productDetail_list(){
        return "rural/front/fooddetail";
    }
    @GetMapping(value="/front_user")
    public String front_user(){
        return "rural/front/user";
    }
//搜索
    @GetMapping(value="/search")
    public String searchResult(){
    return "rural/include/front/search";
}
    //注册页面跳转
    @GetMapping(value="/register")
    public String register(){
        return "rural/fore/register";
    }
    //登录页面跳转
    @GetMapping(value="/rural_login")
    public String login(){
        return "rural/fore/login";
    }
    @GetMapping(value="/fore_product_list")
    public String fore_product_list(){
        return "rural/fore/product";
    }
    @GetMapping(value="/fore_productDetail_list")
    public String fore_productDetail_list(){
        return "rural/fore/productDetail";
    }

    @GetMapping(value="/category")
    public String category(){
        return "rural/fore/category";
    }
    @GetMapping(value="/registerSuccess")
    public String registerSuccess(){
        return "rural/fore/registerSuccess";
    }
    @GetMapping(value="/review")
    public String review(){
        return "rural/fore/review";
    }
    //展示风景
    @GetMapping(value="/showSign")
    public String showSign(Model m){
        List<Category> categories=categoryService.list();
        List<Product> products=productService.list();
        productService.fill(categories);
        m.addAttribute("cs",categories);
        m.addAttribute("ps",products);
        return "rural/front/sign";
    }
    @GetMapping(value="/test")
    public String test(){
        return "rural/front/test";
    }
    @GetMapping(value="/cart")
    public String cartShow(){
        return "rural/front/cart";
    }
    @GetMapping(value="/check")
    public String check(){
        return "rural/front/check";
    }


}