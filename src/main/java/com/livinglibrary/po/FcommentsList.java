package com.livinglibrary.po;

import java.sql.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class FcommentsList {
	Integer commentid;
	Integer parentid;
	String nickname;
	String user_head;
	String replaycontent;
	@JSONField(format="yyyy-MM-dd hh:mm:ss")
	Date addtime;
	public Integer getCommentid() {
		return commentid;
	}
	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
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
	public String getReplaycontent() {
		return replaycontent;
	}
	public void setReplaycontent(String replaycontent) {
		this.replaycontent = replaycontent;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
}
