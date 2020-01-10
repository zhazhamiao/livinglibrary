package com.livinglibrary.PageController;

import java.util.Map;

import javax.annotation.Resource;
import javax.xml.stream.events.Comment;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.livinglibrary.po.Systemset;
import com.livinglibrary.po.Vedio;
import com.livinglibrary.service.ManagerService;
import com.livinglibrary.service.livingService;

@Controller
@RequiresRoles("admin")
@RequestMapping("/manager")
public class ManageController extends ExecptionController{
	
	@Resource
	livingService service;
	@Resource
	ManagerService mservice;

	@RequestMapping("/")
	//@RequiresRoles("user")
	public String manager() {
		return "index";
	}
	
	@RequestMapping("/member-add.html")
	public String memberadd() {
		return "member-add";
	}
	@RequestMapping("/member-list.html")
	public String memberlist() {
		return "member-list";
	}
	@RequestMapping("/member-del.html")
	public String memberdel() {
		return "member-del";
	}
	@RequestMapping("/member-edit.html")
	public String memberedit(String stuid,String name,Boolean sex,String college,String pass,String phone,Integer type,Integer state,Map<String,Object> map) {
		map.put("stuid", stuid);
		map.put("name", name);
		map.put("isgirl", !sex);
		map.put("isboy", sex);
		map.put("college", college);
		map.put("phone", phone);
		map.put("pass", pass);
		map.put("isuser", type==1?true:false);
		map.put("isadmin", type==1?false:true);
		
		
		return "member-edit";
	}
	@RequestMapping("/member-password.html")
	public String memberpass() {
		return "member-password";
	}
	@RequestMapping("/order-add.html")
	public String orderadd() {
		return "order-add";
	}
	@RequestMapping("/order-list.html")
	public String orderlist() {
		return "order-list";
	}
	@RequestMapping("/order.html")
	public String order2() {
		return "order";
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/admin-add.html")
	public String adminadd() {
		return "admin-add";
	}
	@RequestMapping("/admin-cate.html")
	public String admincate() {
		return "admin-cate";
	}
	@RequestMapping("/admin-edit.html")
	public String adminedit() {
		return "admin-edit";
	}
	@RequestMapping("/admin-list.html")
	public String adminlist() {
		return "admin-list";
	}
	@RequestMapping("/admin-role.html")
	public String adminrole() {
		return "admin-role";
	}
	@RequestMapping("/admin-rule.html")
	public String adminrule() {
		return "admin-rule";
	}
	@RequestMapping("/cate.html")
	public String cate() {
		return "cate";
	}
	@RequestMapping("/echarts1.html")
	public String echarts1() {
		return "echarts1";
	}
	@RequestMapping("/echarts2.html")
	public String echarts2() {
		return "echarts2";
	}
	@RequestMapping("/echarts3.html")
	public String echarts3() {
		return "echarts3";
	}
	@RequestMapping("/echarts4.html")
	public String echarts4() {
		return "echarts4";
	}
	@RequestMapping("/echarts5.html")
	public String echarts5() {
		return "echarts5";
	}
	@RequestMapping("/echarts6.html")
	public String echarts6() {
		return "echarts6";
	}
	@RequestMapping("/echarts7.html")
	public String echarts7() {
		return "echarts7";
	}
	@RequestMapping("/echarts8.html")
	public String echarts8() {
		return "echarts8";
	}
	
	@RequestMapping("/welcome.html")
	public String welcome() {
		return "welcome";
	}
	@RequestMapping("/unicode.html")
	public String unicode() {
		return "unicode";
	}
	@RequestMapping("/comment.html")
	public String Comment() {
		return "comment";
	}
	@RequestMapping("/order-yuyue.html")
	public String orderYuyue() {
		return "order-yuyue";
	}
	@RequestMapping("/order-view.html")
	public String orderView(String guestid,String guestname,String address,String guesttype,String guestimg,String summary,Integer isshow,String begintime,String endtime,String allnum,Map<String,Object> map) {
		//guestimg="http://10.1.79.22:8056/"+guestimg;
		map.put("guestid", guestid);
		map.put("guestname", guestname);
		map.put("address", address);
		map.put("guesttype", guesttype);
		map.put("guestimg",guestimg);
		map.put("summary", summary);
		map.put("isshow", isshow==0?true:false);
		map.put("ishide", isshow==0?false:true);
		map.put("begintime", begintime);
		map.put("endtime", endtime);
		map.put("allnum", allnum);
		return "order-view";
	}
	@RequestMapping("/sendnotice.html")
	public String sendNotice() {
		return "sendnotice";
	}
	@RequestMapping("/allnotice.html")
	public String allNotice() {
		return "allnotice";
	}
	@RequestMapping("/order-review.html")
	public String orderReview() {
		return "order-review";
	}
	
	@RequestMapping("/videoManager")
	public String videoManager() {
		return "videoManager";
	}
	@RequestMapping("/vedio-add.html")
	public String videoAdd() {
		return "vedio-add";
	}
	@RequestMapping("/vedio-view.html")
	public String videoView(Integer id,Map<String,Object> map) {
		
		Vedio vedio = service.getVedio(id);
		
		map.put("id", vedio.getId());
		map.put("title", vedio.getTitle());
		map.put("img", vedio.getImg());
		map.put("url", vedio.getUrl());
		map.put("typeid", vedio.getTypeid());
		map.put("description", vedio.getDescription());
		map.put("playvolume", vedio.getPlayvolume());
		
		return "vedio-view";
	}
	@RequestMapping("/vediotype-add.html")
	public String videoTypeAdd() {
		return "vediotype-add";
	}
	@RequestMapping("/vediofold-scan.html")
	public String videofoldscan() {
		return "vediofold-scan";
	}
	
	
	@RequestMapping("/imgManage")
	public String imgManage() {
		
		return "imgManage";
	}
	@RequestMapping("/liveManager")
	public String liveManage(Map<String, Object> map) {
		Systemset systemset = service.getSysSet();
		map.put("liveaddress", systemset.getLiveaddress());
		return "liveManager";
	}
	@RequestMapping("/add")
	public String add() {
		return "add";
	}
	@RequestMapping("/order")
	public String order() {
		return "order";
	}
	@RequestMapping("/role")
	public String role() {
		return "role";
	}
	@RequestMapping("/addManager")
	public String addManager() {
		return "addManager";
	}
	@RequestMapping("/hot")
	public String hot() {
		return "hot";
	}
	
	
}
