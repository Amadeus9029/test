package com.wuzhen.rural.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "bill")
@JsonIgnoreProperties({"handeler","hibernateLazyInitializer"})
public class Bill {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  private String billtype;
  private Date billdate;
  private String points;
  private String comment;
  @ManyToOne
  @JoinColumn(name = "uid")
  private User user;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getBilltype() {
    return billtype;
  }

  public void setBilltype(String billtype) {
    this.billtype = billtype;
  }

  public Date getBilldate() {
    return billdate;
  }

  public void setBilldate(Date billdate) {
    this.billdate = billdate;
  }

  public String getPoints() {
    return points;
  }

  public void setPoints(String points) {
    this.points = points;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}