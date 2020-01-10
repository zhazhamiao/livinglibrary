package com.livinglibrary.po;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class StuYuyue {

	private Integer guestid;
	private String guestname;
	private String address;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date begintime;
	private Integer state;
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getGuestname() {
		return guestname;
	}
	public void setGuestname(String guestname) {
		this.guestname = guestname;
	}
	
	
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	
	public Integer getGuestid() {
		return guestid;
	}
	public void setGuestid(Integer guestid) {
		this.guestid = guestid;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
