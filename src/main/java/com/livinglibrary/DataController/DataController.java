package com.livinglibrary.DataController;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpException;
import org.apache.http.client.ClientProtocolException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.livinglibrary.PageController.ExecptionController;
import com.livinglibrary.po.Boardmessage;
import com.livinglibrary.po.Carousel;
import com.livinglibrary.po.Guest;
import com.livinglibrary.po.Guestcomment;
import com.livinglibrary.po.Guesttype;
import com.livinglibrary.po.MyGuest;
import com.livinglibrary.po.PageList;
import com.livinglibrary.po.StuYuyue;
import com.livinglibrary.po.Systemset;
import com.livinglibrary.po.User;
import com.livinglibrary.po.Vedio;
import com.livinglibrary.po.Vediotype;
import com.livinglibrary.service.livingService;
import com.livinglibrary.util.FileUtil;





@RestController
public class DataController extends ExecptionController{
	
	@Resource
	livingService service;

	//查询所有嘉宾
	@RequestMapping(value="/getGuest")
	public List<Guest> getguest(){
		return service.getAllGuest();
	}
	
	
	//查询所有嘉宾类型
	@RequestMapping("/getGuestType")
	public List<Guesttype> getAllGuestType(){
		return service.getAllGuestType();
	}
	
	/*//模糊查找嘉宾
	@RequestMapping("/searchGuest")
	public PageList searchGuest(String name,Integer page,Integer count){
		return service.searchGuest(name,page,count);
	}*/
	
	//添加一个嘉宾
	/*@RequestMapping("/addGuest")
	public boolean addGuest( HttpServletRequest request,String guestName,String begintime,String endtime,String address,Integer GuestType,@RequestParam("guestImg") MultipartFile guestImg,String summary,String isShow) throws ClientProtocolException, IOException, HttpException {
		String fileName;
		try {
			fileName = FileUtil.uploadFile(guestImg, "cfs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return service.addGuest(guestName, begintime, endtime , address, GuestType, "cfs/"+fileName, summary, isShow);
	}*/
	//详细信息的json数据
	@RequestMapping("/infoData")
	public Guest infoData(Integer GuestId) {
		return service.guestInforation(GuestId);
	}
	
	//查询单个类别的所有Guest
	@RequestMapping("/moreData")
	public PageList getTypeGuest(Integer GuestType,Integer page,Integer count){
		return service.getTypeGuest(GuestType,page,count);
	}
	//查询单个类别的所有video
	@RequestMapping("/moreVideo")
	public PageList getmoreVideo(Integer typeid,Integer page,Integer count){
		return service.getMoreVideo(typeid,page,count);
	}
	
	//删除嘉宾
	/*@RequestMapping("/delGuest")
	public boolean delGuest(HttpServletRequest request) {
		
		String guestid=request.getParameter("id");
		Integer id=Integer.parseInt(guestid);
		if(service.delGuest(id)>0) {
			return true;
		}
	
		return false;
	}*/
	
	//修改嘉宾信息
	/*@RequestMapping("/alterGuest")
	public boolean alterGuest(Integer guestid,String guestName,String begintime,String endtime,String address,Integer GuestType,@RequestParam("guestImg") MultipartFile guestImg,String summary,String isShow) {
		
		if(guestImg.equals("undefined")) {
			return service.alterGuest(guestid,guestName, begintime, endtime, address, GuestType,"unchange", summary, isShow);
		}
		
		String fileName;
		try {
			fileName = FileUtil.uploadFile(guestImg, "cfs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return service.alterGuest(guestid,guestName,  begintime, endtime, address, GuestType, "cfs/"+fileName, summary, isShow);
	}*/
	
	@RequestMapping(value = "/login" ,method=RequestMethod.POST)
	public String loginFor(HttpSession session,String stuid,String pwd,String type) {
		/*String name = service.loginFor(stuid, pwd, type);		
		if(name.equals("error")) {			
			return "error";
		}else {
			session.setAttribute("type", type);
			System.out.println(type);
			return name;
		}*/
		 //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(stuid,pwd);
        //进行验证，这里可以捕获异常，然后返回对应信息
        String name;
        try {
        	subject.login(usernamePasswordToken);
        	name = service.loginFor(stuid, pwd, type);
        	
        	
		} catch (IncorrectCredentialsException  e) {
			return "error";
		}catch (UnknownAccountException   e) {
			return "error";
		}
        return name;
	}
	
	
	
	//获取轮播图的id以及图片地址URL
	@RequestMapping("/getCarousel")
	public List<Carousel> getCarousel(){
		return service.getCarousel();
	}
	//删除一个轮播图ID和URL(根据图片地址删除)
	@RequestMapping("/delCarousel")
	public List<Carousel> delCarousel(String url){
		return service.delCarousel(url);
	}
	//添加一张轮播图
	@RequestMapping("/addCarousel")
	public List<Carousel> addCarousel(@RequestParam("Img") MultipartFile Img) {
		String fileName="";
		try {
			fileName =  FileUtil.uploadFile(Img, "img");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return service.addCarousel("img/"+fileName);
	}
	
	//修改轮播图(根据图片地址修改)
	@RequestMapping("/alterCarouselByUrl")
	public List<Carousel> alterCarousel(String url,@RequestParam("Img") MultipartFile Img){
		String fileName="";
		try {
			fileName =  FileUtil.uploadFile(Img, "img");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return service.alterCarouselByUrl(url, "img/"+fileName);
		
	}
	//修改轮播图(根据图片ID修改)
	@RequestMapping("/alterCarouselById")
	public List<Carousel> alterCarousel(Integer id,@RequestParam("Img") MultipartFile Img){
		String fileName="";
		try {
			fileName =  FileUtil.uploadFile(Img, "img");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return service.alterCarouselById(id, "img/"+fileName);
		
	}
	
	@RequestMapping("/logout")  
	public String logout(){  
		 Subject subject = SecurityUtils.getSubject();
		// System.out.println(subject.getPrincipal().toString());
	     subject.logout();
	     
	    return "redirect:/login";  
	} 
	
	/**
	 * 判断该用户是否已经预约过这场讲座
	 * @param stuid
	 * @param guestid
	 * @return
	 */
	@RequestMapping("/isYuyue")
	public Integer isYuyue(String stuid,Integer guestid) {
		return service.isYuyue(stuid, guestid);
	}
	/**
	 * 预约
	 * @param stuid
	 * @param guestid
	 * @return
	 */
	@RequestMapping("/yuYue")
	public Integer Yuyue(String stuid,Integer guestid) {
		return service.Yuyue(stuid, guestid);
	}
	/**
	 * 获取一场讲座下的所有审核通过的留言
	 * @param guestid 讲座id
	 * @return
	 */
	@RequestMapping("/getcomment")
	public List<Guestcomment> guestcomments(Integer guestid){
		return service.guestcomments(guestid);
	}
	/**
	 * 用户发表评论
	 * @param stuid 学号
	 * @param guestid	讲座id
	 * @param content	评论内容
	 * @param isrely	是否是回复评论
	 * @return
	 */
	@RequestMapping("/addcomment")
	public Integer addComment(String stuid,Integer guestid,String content,Integer isrely) {
		return service.addComment(stuid, guestid, content, isrely);
	}
	
	/**
	 * 取消预约（取消后不可再预约）
	 * @param stuid
	 * @param guestid
	 * @return
	 */
	@RequestMapping("/cancelYuyue")
	public Integer cancelYuyue(String stuid,Integer guestid) {
		return service.cancelYuyue(stuid, guestid);
	}
	/**
	 * 根据用户获取通知消息
	 * @param stuid
	 * @return
	 */
	@RequestMapping("getNotice")
	public List<Boardmessage> getNotice(String stuid,Integer type){
		return service.getNotice(stuid,type);
	}
	/**
	 * 获取精彩视频列表
	 * @return
	 */
	@RequestMapping("getWonderfulVedio")
	public List<Vedio> getWonderfulVedio(){
		return service.getWonderfulVedio();
	}
	
	@RequestMapping("getVedio")
	public Vedio getVedio(Integer id) {
		return service.getVedio(id);
	}
	@RequestMapping("getUserInfo")
	public User getUserInfo(String stuid) {
		return service.getUserInfo(stuid);
	}
	@RequestMapping("/getStuYuYue")
	public List<StuYuyue> getStuYuYue(String stuid){
		return service.getStuYuYue(stuid);
	}
	@RequestMapping("getMycomment")
	public List<Guestcomment> getMycomment(String stuid){
		return service.getMycomment(stuid);
	}
	@RequestMapping("/getSysSet")
	public Systemset getSysSet() {
		return service.getSysSet();
	}
	@RequestMapping("/updateuserbyself")
	public Integer updateuserbyself(String stuid,String username,Boolean sex,String college,String phone,String email, String pass,String type) {
		return service.updateUserbyself(stuid, username, sex, college, phone,email,pass, type);
	}
	@RequestMapping("/getVedioType")
	public List<Vediotype> getVedioType(){
		return service.getVedioType();
	}
	
}