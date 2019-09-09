package com.wuzhen.rural.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer"})
@Document(indexName = "tmall_springboot",type = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @ManyToOne
  @JoinColumn(name = "cid")
  private Category category;

  private String name;

  private Date createdate;
  private String content;
  private String subTitle;
  private float price;
  private float originalPrice;
  private float promotePrice;

  @Transient
  private List<Review> reviews_user;

  @Transient
  private  List<Record> records;
  @Transient
  private ProductImage firstProductImage;
  @Transient
  private List<ProductImage> productSingleImages;
  @Transient
  private List<ProductImage> productDetailImages;
  @Transient
  private int reviewCount;
  @Transient
  private int saleCount;

  public float getOriginalPrice() {
    return originalPrice;
  }

  public void setOriginalPrice(float originalPrice) {
    this.originalPrice = originalPrice;
  }

  public float getPromotePrice() {
    return promotePrice;
  }

  public void setPromotePrice(float promotePrice) {
    this.promotePrice = promotePrice;
  }

  public int getReviewCount() {
    return reviewCount;
  }

  public void setReviewCount(int reviewCount) {
    this.reviewCount = reviewCount;
  }

  public int getSaleCount() {
    return saleCount;
  }

  public void setSaleCount(int saleCount) {
    this.saleCount = saleCount;
  }

  public List<ProductImage> getProductSingleImages() {
    return productSingleImages;
  }

  public void setProductSingleImages(List<ProductImage> productSingleImages) {
    this.productSingleImages = productSingleImages;
  }

  public List<ProductImage> getProductDetailImages() {
    return productDetailImages;
  }

  public void setProductDetailImages(List<ProductImage> productDetailImages) {
    this.productDetailImages = productDetailImages;
  }

  public List<Review> getReviews_user() {
    return reviews_user;
  }

  public void setReviews_user(List<Review> reviews_user) {
    this.reviews_user = reviews_user;
  }

  public List<Record> getRecords() {
    return records;
  }

  public void setRecords(List<Record> records) {
    this.records = records;
  }

  public ProductImage getFirstProductImage() {
    return firstProductImage;
  }

  public String getSubTitle() {
    return subTitle;
  }

  public void setSubTitle(String subTitle) {
    this.subTitle = subTitle;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public void setFirstProductImage(ProductImage firstProductImage) {
    this.firstProductImage = firstProductImage;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Date getCreatedate() {
    return createdate;
  }

  public void setCreatedate(Date createdate) {
    this.createdate = createdate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
