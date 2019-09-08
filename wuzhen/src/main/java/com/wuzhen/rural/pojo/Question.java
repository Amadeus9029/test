package com.wuzhen.rural.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "question")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer"})
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;


  private String content;
  private String description;
  private String answer_a;
  private String answer_b;
  private String answer_c;
  private String answer_d;
  private String answer;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAnswer_a() {
    return answer_a;
  }

  public void setAnswer_a(String answer_a) {
    this.answer_a = answer_a;
  }

  public String getAnswer_b() {
    return answer_b;
  }

  public void setAnswer_b(String answer_b) {
    this.answer_b = answer_b;
  }

  public String getAnswer_c() {
    return answer_c;
  }

  public void setAnswer_c(String answer_c) {
    this.answer_c = answer_c;
  }

  public String getAnswer_d() {
    return answer_d;
  }

  public void setAnswer_d(String answer_d) {
    this.answer_d = answer_d;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}
