package com.wuzhen.rural.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "question_record")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Question_Record {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  private int acnumber;
  private int erunmber;

  @ManyToOne
  @JoinColumn(name = "uid")
  private User user;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAcnumber() {
    return acnumber;
  }

  public void setAcnumber(int acnumber) {
    this.acnumber = acnumber;
  }

  public int getErunmber() {
    return erunmber;
  }

  public void setErunmber(int erunmber) {
    this.erunmber = erunmber;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
