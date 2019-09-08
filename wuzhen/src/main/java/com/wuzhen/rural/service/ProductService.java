package com.wuzhen.rural.service;

import com.wuzhen.rural.dao.ProductDAO;
import com.wuzhen.rural.pojo.Category;
import com.wuzhen.rural.pojo.Product;
import com.wuzhen.rural.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;
    @Autowired ProductImageService productImageService;
    @Autowired CategoryService categoryService;
    public Page4Navigator<Product> list(int cid, int start, int size, int navigatePages){
        Category category = categoryService.get(cid);
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable=new PageRequest(start,size,sort);

        Page<Product> pageFromJPA=productDAO.findByCategory(category,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
    public List<Product> list(int cid){
        Category category = categoryService.get(cid);
        return productDAO.findByCategoryOrderById(category);
    }
    public List<Product> list(){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        return productDAO.findAll(sort);
    }
    //添加
    public void add(Product product){
        productDAO.save(product);
    }
    //删除
    public void delete(int id){
        productDAO.deleteById(id);
    }

    //编辑
    public Product get(int id){
        Product c=productDAO.getOne(id);
        return c;
    }
    //修改
    public void update(Product bean) {
        productDAO.save(bean);
    }

    public void fill(List<Category> categorys) {
        for (Category category : categorys) {
            fill(category);
        }
    }
    public void fill(Category category) {
        List<Product> products = listByCategory(category);
        productImageService.setFirstProdutImages(products);
        category.setProducts(products);
    }
    public List<Product> listByCategory(Category category){
        return productDAO.findByCategoryOrderById(category);
    }

    public void fillByRow(List<Category> categorys) {
        int productNumberEachRow = 8;
        for (Category category : categorys) {
            List<Product> products =  category.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }



}

