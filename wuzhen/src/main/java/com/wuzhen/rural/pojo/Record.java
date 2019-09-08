package com.wuzhen.rural.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
@Table(name = "record")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Record {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @ManyToOne
  @JoinColumn(name="oid")
  private Order order;

  @ManyToOne
  @JoinColumn(name = "pid")
  private Product product;
  @ManyToOne
  @JoinColumn(name = "uid")
  private User user;

  private int number;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public Record() {
  }

  public Record(Product product, User user) {
    this.product = product;
    this.user = user;
  }
}
