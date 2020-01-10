package com.livinglibrary.PageController;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.livinglibrary.po.Guest;
import com.livinglibrary.po.Pastreview;
import com.livinglibrary.po.Systemset;
import com.livinglibrary.service.ManagerService;
import com.livinglibrary.service.livingService;

import groovy.lang.MetaClassImpl.Index;

@Controller
public class PageController {
	
	@Resource
	livingService livingService;
	@Resource
	ManagerService mservice;
	
	@RequestMapping("/")
	public String Index1() {
		return "aindex";
	}
	
	@RequestMapping("/info")
	public String infomation(Integer GuestId,Map<String,Object> map) {
		
		map.put("GuestId", GuestId);
		
		return "detial";
	}
	


	
	@RequestMapping("/add")
	public String add() {
		return "add";
	}
	
	@RequestMapping("/test")
	public String test(Map<String, Object> map) {
		map.put("name", "Test");
		
		return "test";
		
	}
	
	@RequestMapping("/success")
	public String success(){
		return "success";
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/aindex")
	public String aindex() {
		return "aindex";
	}
	
	@RequestMapping("/amore")
	public String amore(Integer type,String typename,Map<String,Object> map) {
		map.put("type", type);
		map.put("typename", typename);
		return "amore";
	}
	
	@RequestMapping("/adetail")
	public String adetail(Integer GuestId,Map<String,Object> map) {
		
		map.put("GuestId", GuestId);
		
		return "adetail";
	}
	
	@RequestMapping("/alogin")
	public String alogin() {
		return "alogin";
	}
	
	@RequestMapping("/hot")
	public String hot(){
		return "hot";
	}
	@RequestMapping("/order")
	public String order(){
		return "order";
	}
	
	@RequestMapping("/personal")
	public String Personal() {
		
		//map.put("info", livingService.searchByStuid(stuid));
		return "personal";
	}
	@RequestMapping("pastview")
	public String pastView(Integer guestid,Map<String, Object> map) {
		
		Pastreview pastreview = mservice.getReview(guestid);
		if(pastreview==null)
			return "pastview_null";
		map.put("info", pastreview);
		
		map.put("guestinfo",livingService.guestInforation(guestid));
		
		return "pastview";
	}
	
	@RequestMapping("/avedio")
	public String aVedio(Map<String, Object> map) {
		Systemset systemset = livingService.getSysSet();
		map.put("address", systemset.getLiveaddress());
		return "avedio";
	}
	@RequestMapping("/playvedio")
	public String payVedio(Integer id,Map<String, Object> map) {
		map.put("id", id);
		return "playvedio";
	}
	@RequestMapping("my-message")
	public String myMessage() {
		return "my-message";
	}
	@RequestMapping("my-info")
	public String myInfo() {
		return "my-info";
	}
	@RequestMapping("my-join")
	public String myJoin() {
		return "my-join";
	}
	@RequestMapping("my-comment")
	public String myComment() {
		return "my-comment";
	}
	
	
}
