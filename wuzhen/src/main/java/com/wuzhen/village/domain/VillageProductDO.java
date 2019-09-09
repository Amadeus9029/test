package com.wuzhen.village.domain;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xxx
 * @email xxx@163.com
 * @date 2019-09-08 16:53:57
 */
public class VillageProductDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String name;
	//
	private Integer cid;
	//

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdate;
	//
	private String content;
	//
	private Float price;
	//
	private String subtitle;
	//
	private Float originalprice;
	//
	private Float promoteprice;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/**
	 * 获取：
	 */
	public Date getCreatedate() {
		return createdate;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setPrice(Float price) {
		this.price = price;
	}
	/**
	 * 获取：
	 */
	public Float getPrice() {
		return price;
	}
	/**
	 * 设置：
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	/**
	 * 获取：
	 */
	public String getSubtitle() {
		return subtitle;
	}
	/**
	 * 设置：
	 */
	public void setOriginalprice(Float originalprice) {
		this.originalprice = originalprice;
	}
	/**
	 * 获取：
	 */
	public Float getOriginalprice() {
		return originalprice;
	}
	/**
	 * 设置：
	 */
	public void setPromoteprice(Float promoteprice) {
		this.promoteprice = promoteprice;
	}
	/**
	 * 获取：
	 */
	public Float getPromoteprice() {
		return promoteprice;
	}
}
