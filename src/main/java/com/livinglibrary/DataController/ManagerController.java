package com.livinglibrary.DataController;

import java.io.FileNotFoundException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.livinglibrary.po.AdminList;
import com.livinglibrary.po.Guest;
import com.livinglibrary.po.PageList;
import com.livinglibrary.po.Pastreview;
import com.livinglibrary.po.ReviewState;
import com.livinglibrary.po.State;
import com.livinglibrary.po.Systemset;
import com.livinglibrary.po.User;
import com.livinglibrary.po.Vedio;
import com.livinglibrary.po.YuyueState;
import com.livinglibrary.service.ManagerService;
import com.livinglibrary.util.FileUtil;

@RestController
@RequiresRoles("admin")
public class ManagerController {
	
	@Resource
	ManagerService service;
	
	@RequestMapping("/listmember")
	public PageList listmember(Integer page,Integer count){
		return service.listmember(page,count);
	}
	/**
	 * 添加用户、管理员
	 * @param stuid
	 * @param username
	 * @param sex
	 * @param college
	 * @param phone
	 * @param pass
	 * @param type
	 * @return
	 */
	@RequestMapping("/adduser")
	public Integer addUser(String stuid,String username,Integer sex,String college,String phone,String pass,String type) {
		return service.addUser(stuid, username, sex, college, phone, pass, type);
	}
	
	/**
	 * 修改用户信息
	 * @param stuid
	 * @param username
	 * @param sex
	 * @param college
	 * @param phone
	 * @param type
	 * @return
	 */
	@RequestMapping("/updateuser")
	public Integer updateUser(String stuid,String username,Boolean sex,String college,String phone,String pass,String type) {
		return service.updateUser(stuid, username, sex, college, phone,pass, type);
	}
	/**
	 * 删除用户，将用户的状态改为3
	 * @param stuid
	 * @return
	 */
	@RequestMapping("/deluser")
	public Integer delUser(String stuid) {
		return service.delUser(stuid);
	}
	/**
	 * 禁用账户
	 * @param stuid
	 * @param state
	 * @return
	 */
	@RequestMapping("/updatestate")
	public Integer updateState(String stuid,Integer state) {
		return service.updateState(stuid, state);
	}
	
	/**
	 * 根据isshow来展示评论信息
	 * @param isshow
	 * @return
	 */
	@RequestMapping("/loadcomment")
	public PageList loadComment(Integer isshow,Integer page,Integer count){
		return service.loadComment(isshow,page,count);
	}
	/**
	 * 更新评论的状态，0--未审核，1--通过，2--隐藏，3--删除；
	 * @param id
	 * @param isshow
	 * @return
	 */
	@RequestMapping("/updatecomment")
	public Integer updateComment(Integer id,Integer isshow) {
		return service.updateComment(id, isshow);
	}
	
	/**
	 * 模糊查找用户
	 * @param name
	 * @return
	 */
	@RequestMapping("/searchuser")
	public List<User> searchUser(String name){
		return service.searchUser(name);
	}
	
	/**
	 * 获取后台显示的guest信息
	 * @param page 页
	 * @param count 条数
	 * @return
	 */
	@RequestMapping("/getList")
	public PageList getList(Integer page,Integer count){
		return service.getList(page, count);
		
	}
	
	/**
	 * 单张图片上传
	 * @param data
	 * @return
	 */
	@RequestMapping("/uploadimg")
	public State uploadImg(@RequestParam("file") MultipartFile data) {
		String fileName="";
		State state = new State();
		try {
			fileName =  FileUtil.uploadFile(data, "pic");
			state.setmString("pic/"+fileName);
			state.setState(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			state.setmString("出错了！");
			state.setState(0);
		}
		
		return state;
	}
	
	/**
	 * 富文本编辑器图片上传
	 * @param data
	 * @return
	 */
	@RequestMapping("/uploadReviewImg")
	public ReviewState uploadReviewImg(@RequestParam("file") MultipartFile data) {
		String fileName="";
		ReviewState rState = new ReviewState();
		try {
			fileName =  FileUtil.uploadFile(data, "rev");
			rState.setData("rev/"+fileName);
			rState.setErrno(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rState.setData("review/error.jpg");
			rState.setErrno(1);
		}
		
		return rState;
	}
	
	/**
	 * 添加一场讲座
	 * @param guestname
	 * @param address
	 * @param allnum
	 * @param begintime
	 * @param endtime
	 * @param isshow
	 * @param imgpath
	 * @param summary
	 * @return
	 */
	@RequestMapping("/addGuest")
	public Integer addGuest(String guestname,String address,Integer allnum,String begintime,String endtime,Integer isshow,String imgpath,String summary) {
		return service.addGuest(guestname, address, allnum, begintime, endtime, isshow, imgpath, summary);
	}
	/**
	 * 删除一个Guest(讲座/真人图书)
	 * @param guestid
	 * @return
	 */
	@RequestMapping("/delGuest")
	public Integer delGuest(Integer guestid) {
		return service.delGuest(guestid);
	}
	/**
	 * 修改讲座信息
	 * @param guestname
	 * @param address
	 * @param allnum
	 * @param begintime
	 * @param endtime
	 * @param isshow
	 * @param imgpath
	 * @param summary
	 * @return
	 */
	@RequestMapping("/alterGuest")
	public Integer alterGuest(Integer guestid,String guestname,String address,Integer allnum,String begintime,String endtime,String isshow,String imgpath,String summary) {
		return service.alterGuest(guestid, guestname, address, allnum, begintime, endtime, isshow, imgpath, summary);
	}
	/**
	 * 查找讲座
	 * @param start
	 * @param end
	 * @param name
	 * @return
	 */
	@RequestMapping("/searchGuest")
	public List<Guest> searchGuest(String start,String end,String name){
		return service.searchGuest(start, end, name);
	}
	/**
	 * 导出预约信息
	 * @param guestid
	 * @return
	 */
	@RequestMapping("/orderyuyue")
	public List<YuyueState> orderYuyue(Integer guestid){
		return service.orderYuyue(guestid);
	}
	/**
	 * 发布通知消息
	 * @param type
	 * @param notice
	 * @return 
	 */
	@RequestMapping("publishNotice")
	public int publishNotice(Integer type,Integer guestid,String notice) {
		return service.publishNotice(type,guestid, notice);
	}
	/**
	 * 修改预约转态
	 * @param stuid
	 * @param guestid
	 * @param status
	 * @return
	 */
	@RequestMapping("alertYuYueStatus")
	public int alterYuyueStatus(String stuid,Integer guestid,boolean status) {
		return service.alterYuyueStatus(stuid, guestid, status);
	}
	/**
	 * 上传真人图书活动回顾
	 * @param txt
	 * @param guestid
	 * @return
	 */
	@RequestMapping("uploadReview")
	public String uploadReview(String txt,Integer guestid) {
		return service.uploadReview(txt, guestid);
	}
	/**
	 * 获取回顾
	 * @param guestid
	 * @return
	 */
	@RequestMapping("getReview")
	public Pastreview getReview(Integer guestid) {
		return service.getReview(guestid);
	}
	/**
	 * 获取管理员页面的视频管理
	 * @param page
	 * @param count
	 * @return
	 */
	@RequestMapping("/getManagerVideo")
	public PageList getManagerVideo(Integer page,Integer count) {
		return service.getManagerVideo(page, count);
	}
	/**
	 * 添加视频
	 * @param title
	 * @param url
	 * @param imgpath
	 * @param description
	 * @return
	 */
	@RequestMapping("addVideo")
	public Integer addVideo(String title,String url,String imgpath,Integer type,String description) {
		return service.addVideo(title, url, "http://10.1.79.22:8056/"+imgpath,type, description);
	}
	/**
	 * 删除一个视频
	 * @param id
	 * @return
	 */
	@RequestMapping("delVideo")
	public Integer delVideo(Integer id) {
		return service.delVideo(id);
	}
	/**
	 * 修改视频信息
	 * @param id
	 * @param title
	 * @param url
	 * @param imgpath
	 * @param description
	 * @param playvolume
	 * @return
	 */
	@RequestMapping("alterVedio")
	public Integer alterVedio(Integer id,String title,String url,String imgpath,String description,Integer type,Integer playvolume) {
		return service.alterVedio(id, title, url, imgpath, description,type, playvolume);
	}
	@RequestMapping("/loadAllNotice")
	public PageList loadAllNotice(Integer page,Integer count){
		return service.loadAllNotice(page,count);
	}
	@RequestMapping("/delNotice")
	public Integer delNotice(String content) {
		return service.delNotice(content);
	}
	
	
	@RequestMapping("/alertSysSet")
	public Systemset alertSysSet() {
		return null;
	}
	/**
	 * 修改直播地址
	 * @param address
	 * @return
	 */
	@RequestMapping("/alertRtmp")
	public Integer alertRtmp(String address) {
		return service.alertRtmp(address);
	}
	/**
	 * 获取管理员列表
	 * @param type
	 * @return
	 */
	@RequestMapping("/getAdminList")
	public PageList getAdminLst(Integer page,Integer count,Integer type){
		return service.getAdminlist(page,count,type);
	}
	@RequestMapping("/searchAdmin")
	public List<AdminList> searchAdminList(String start,String end,String username) {
		return service.searchAdminList(start,end,username);
	}
	@RequestMapping("/addVideoType")
	public Integer addVideoType(Integer type,String typename,String description) {
		return service.addVedioType(type,typename,description);
	}
	@RequestMapping("/scanFiles")
	public List<Vedio> scanFiles(String filepath,Integer type) throws FileNotFoundException {
		return service.scanFile(filepath,type);
	}
}
