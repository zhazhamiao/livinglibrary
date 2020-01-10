package com.livinglibrary.po;

import java.sql.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class FpostList {
	private int postid;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date time;
	private String nickname;
	private String user_head;
	private String title;
	private String summary;
	private int looknum;
	private int commentnum;
	private int follownum;
	
	
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUser_head() {
		return user_head;
	}
	public void setUser_head(String user_head) {
		this.user_head = user_head;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getLooknum() {
		return looknum;
	}
	public void setLooknum(int looknum) {
		this.looknum = looknum;
	}
	public int getCommentnum() {
		return commentnum;
	}
	public void setCommentnum(int commentnum) {
		this.commentnum = commentnum;
	}
	public int getFollownum() {
		return follownum;
	}
	public void setFollownum(int follownum) {
		this.follownum = follownum;
	}
	
}
