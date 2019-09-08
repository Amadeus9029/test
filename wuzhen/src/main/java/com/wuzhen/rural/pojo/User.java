package com.wuzhen.rural.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id")		
	private int id;

	private String password;
	private String name;	
	private String salt;
	private String level;
	private float points;
	@Transient
	private String anonymousName;
	@Transient
	private List<Review> reviews_user;

	@Transient
	private  List<Record> records;

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	public List<Review> getReviews_user() {
		return reviews_user;
	}

	public void setReviews_user(List<Review> reviews_user) {
		this.reviews_user = reviews_user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalt() {
		return salt;
		
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getAnonymousName(){
		if(null!=anonymousName)
			return anonymousName;
		if(null==name)
			anonymousName= null;
		else if(name.length()<=1)
			anonymousName = "*";
		else if(name.length()==2)
			anonymousName = name.substring(0,1) +"*";
		else {
			char[] cs =name.toCharArray();
			for (int i = 1; i < cs.length-1; i++) {
				cs[i]='*';
			}
			anonymousName = new String(cs);			
		}
		return anonymousName;
	}

	public void setAnonymousName(String anonymousName) {
		this.anonymousName = anonymousName;
	}

	public float getPoints() {
		return points;
	}

	public void setPoints(float points) {
		this.points = points;
	}
}
