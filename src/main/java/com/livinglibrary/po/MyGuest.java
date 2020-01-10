package com.livinglibrary.po;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class MyGuest {
	
	private Integer GuestId;
	private String GuestName;
	private String GuestType;
	private String GuestImg;
	private String Summary;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date beginTime;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	public Integer getGuestId() {
		return GuestId;
	}

	public void setGuestId(Integer guestId) {
		GuestId = guestId;
	}

	public String getGuestName() {
		return GuestName;
	}

	public void setGuestName(String guestName) {
		GuestName = guestName;
	}

	public String getGuestType() {
		return GuestType;
	}

	public void setGuestType(String guestType) {
		GuestType = guestType;
	}

	public String getGuestImg() {
		return GuestImg;
	}

	public void setGuestImg(String guestImg) {
		GuestImg = guestImg;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) {
		Summary = summary;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	

}
