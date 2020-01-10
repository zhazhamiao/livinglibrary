package com.livinglibrary.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.solr.client.solrj.request.CollectionAdminRequest.Create;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.livinglibrary.mapper.BoardmessageMapper;
import com.livinglibrary.mapper.CarouselMapper;
import com.livinglibrary.mapper.GuestMapper;
import com.livinglibrary.mapper.GuestcommentMapper;
import com.livinglibrary.mapper.GuesttypeMapper;
import com.livinglibrary.mapper.InvitationMapper;
import com.livinglibrary.mapper.MyselfMapper;
import com.livinglibrary.mapper.PastreviewMapper;
import com.livinglibrary.mapper.RegistrationrecordMapper;
import com.livinglibrary.mapper.SystemsetMapper;
import com.livinglibrary.mapper.UserMapper;
import com.livinglibrary.mapper.VedioMapper;
import com.livinglibrary.mapper.VediotypeMapper;
import com.livinglibrary.po.AdminList;
import com.livinglibrary.po.Boardmessage;
import com.livinglibrary.po.BoardmessageExample;
import com.livinglibrary.po.Guest;
import com.livinglibrary.po.GuestExample;
import com.livinglibrary.po.Guestcomment;
import com.livinglibrary.po.GuestcommentExample;
import com.livinglibrary.po.PageGuest;
import com.livinglibrary.po.PageList;
import com.livinglibrary.po.Pastreview;
import com.livinglibrary.po.PastreviewExample;
import com.livinglibrary.po.Registrationrecord;
import com.livinglibrary.po.RegistrationrecordExample;
import com.livinglibrary.po.Systemset;
import com.livinglibrary.po.SystemsetExample;
import com.livinglibrary.po.User;
import com.livinglibrary.po.UserExample;
import com.livinglibrary.po.Vedio;
import com.livinglibrary.po.VedioExample;
import com.livinglibrary.po.Vediotype;
import com.livinglibrary.po.YuyueState;

@Service
public class ManagerService {
	@Resource
	UserMapper userMapper;
	@Resource
	GuestcommentMapper guestcommentMapper;
	@Resource 
	GuestMapper guestMapper;
	@Resource
	GuesttypeMapper guesttypeMapper;
	@Resource
	InvitationMapper invitationMapper;
	@Resource
	CarouselMapper carouselMapper;
	@Resource
	RegistrationrecordMapper registrationrecordMapper;
	@Resource
	MyselfMapper mapper;
	@Resource
	BoardmessageMapper boardmessageMapper;
	@Resource
	PastreviewMapper pastreviewMapper;
	@Resource
	VedioMapper vedioMapper;
	@Resource
	SystemsetMapper systemsetMapper;
	@Resource
	VediotypeMapper vediotypeMapper;
	
	public PageList listmember(Integer page, Integer count){
		PageList pageGuest = new PageList();
		
		PageHelper.startPage(page, count);
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(3);
		List<User> list = userMapper.selectByExample(example);
		PageInfo<User> pageinfo = new PageInfo<User>(list);
		pageGuest.setPage(pageinfo.getPageNum());
		pageGuest.setRecords(pageinfo.getTotal());
		pageGuest.setTotal(pageinfo.getPages());
		pageGuest.setRows(list);
		
		return pageGuest;
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
	@Transactional
	public Integer addUser(String stuid,String username,Integer sex,String college,String phone,String pass,String type) {
		User user = new User();
		user.setCollege(college);
		user.setName(username);
		user.setPassword(pass);
		user.setPhone(phone);
		user.setSex(sex==1?true:false);
		user.setState(1);
		user.setStuid(stuid);
		user.setType(type);
		try {
			userMapper.insertSelective(user);
			return 1;//插入成功 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		
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
	@Transactional
	public Integer updateUser(String stuid,String username,Boolean sex,String college,String phone,String pass, String type) {
		User user = new User();
		user.setCollege(college);
		user.setName(username);
		user.setPhone(phone);
		user.setSex(sex);
		user.setStuid(stuid);
		user.setType(type);
		user.setPassword(pass);
		try {
			userMapper.updateByPrimaryKeySelective(user);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		
	}
	/**
	 * 删除用户，将用户的状态改为3
	 * @param stuid
	 * @return
	 */
	@Transactional
	public Integer delUser(String stuid) {
		User user = new User();
		user.setStuid(stuid);
		user.setState(3);
		
		try {
			userMapper.updateByPrimaryKeySelective(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	/**
	 * 禁用账户
	 * @param stuid
	 * @param state
	 * @return
	 */
	@Transactional
	public Integer updateState(String stuid,Integer state) {
		User user = new User();
		user.setStuid(stuid);
		user.setState(state);
		
		try {
			userMapper.updateByPrimaryKeySelective(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 根据isshow来展示评论信息
	 * @param isshow
	 * @return
	 */
	public PageList loadComment(Integer isshow,Integer page,Integer count){
		
		PageList pageGuest = new PageList();
		
		PageHelper.startPage(page, count);
		GuestcommentExample example = new GuestcommentExample();
		GuestcommentExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("addtime desc");
		if(isshow!=-1)
			criteria.andIsshowEqualTo(isshow);
		List<Guestcomment> list = guestcommentMapper.selectByExampleWithBLOBs(example);
		PageInfo<Guestcomment> pageinfo = new PageInfo<Guestcomment>(list);
		pageGuest.setPage(pageinfo.getPageNum());
		pageGuest.setRecords(pageinfo.getTotal());
		pageGuest.setTotal(pageinfo.getPages());
		pageGuest.setRows(list);
		
		return pageGuest;
		
	}
	/**
	 * 更新评论的状态，0--未审核，1--通过，2--隐藏，3--删除；
	 * @param id
	 * @param isshow
	 * @return
	 */
	@Transactional
	public Integer updateComment(Integer id,Integer isshow) {
		Guestcomment guestcomment = new Guestcomment();
		guestcomment.setId(id);
		guestcomment.setIsshow(isshow);
		
		try {
			guestcommentMapper.updateByPrimaryKeySelective(guestcomment);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
			// TODO: handle exception
		}
	}
	
	/**
	 * 模糊查找用户
	 * @param name
	 * @return
	 */
	public List<User> searchUser(String name){
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andNameLike("%"+name+"%");
		criteria.andStateNotEqualTo(3);
		
		return userMapper.selectByExample(example);
	}
	
	/**
	 * 获取后台显示的guest信息
	 * @param page 页
	 * @param count 条数
	 * @return
	 */
	public PageList getList(Integer page,Integer count) {
		
		PageList pageGuest = new PageList();
		
		PageHelper.startPage(page, count);
		GuestExample example = new GuestExample();
		GuestExample.Criteria criteria = example.createCriteria();
		//criteria.andIsshowNotEqualTo("1");
		example.setOrderByClause("guestid desc");
		List<Guest> list = guestMapper.selectByExampleWithBLOBs(example);
		PageInfo<Guest> pageinfo = new PageInfo<Guest>(list);
		pageGuest.setPage(pageinfo.getPageNum());
		pageGuest.setRecords(pageinfo.getSize());
		pageGuest.setTotal(pageinfo.getPages());
		pageGuest.setRows(list);
		
		return pageGuest;
		
	}
	/**
	 * 添加一个Guest
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
	@Transactional
	public Integer addGuest(String guestname,String address,Integer allnum,String begintime,String endtime,Integer isshow,String imgpath,String summary) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		
		Guest guest = new Guest();
		guest.setAddress(address);
		try {
			guest.setBegintime(sdf.parse(begintime));
			guest.setEndtime(sdf.parse(endtime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		guest.setAddress(address);
		guest.setAllnum(allnum);
		guest.setGuestname(guestname);
		guest.setGuestimg(imgpath);
		guest.setOrdernum(0);
		//guest.setGuesttype(GuestType);
		guest.setIsshow(isshow.toString());
		guest.setSummary(summary);
		try {
			guestMapper.insertSelective(guest);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return 0;
		}	
	}
	/**
	 * 删除一个Guest(讲座/真人图书)
	 * @param guestid
	 * @return
	 */
	@RequestMapping("/delGuest")
	public Integer delGuest(Integer guestid) {
		
		Guest guest = new Guest();
		guest.setGuestid(guestid);
		guest.setIsshow("1");//设置为已取消
		
		try {
			guestMapper.updateByPrimaryKeySelective(guest);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
			// TODO: handle exception
		}
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
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		
		Guest guest = new Guest();
		guest.setGuestid(guestid);
		guest.setAddress(address);
		try {
			guest.setBegintime(sdf.parse(begintime));
			guest.setEndtime(sdf.parse(endtime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		guest.setAddress(address);
		guest.setAllnum(allnum);
		guest.setGuestname(guestname);
		guest.setGuestimg(imgpath);
		//guest.setOrdernum(0);
		//guest.setGuesttype(GuestType);
		guest.setIsshow(isshow);
		guest.setSummary(summary);
		try {
			guestMapper.updateByPrimaryKeySelective(guest);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return 0;
		}	
	}
	
	/**
	 * 查找讲座
	 * @param start
	 * @param end
	 * @param name
	 * @return
	 * @throws ParseException 
	 */
	public List<Guest> searchGuest(String start,String end,String name){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		GuestExample example = new GuestExample();
		GuestExample.Criteria criteria = example.createCriteria();
		criteria.andAddressLike("%"+name+"%");
	
		try {
			criteria.andBegintimeBetween(sdf.parse(start),sdf.parse(end));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return guestMapper.selectByExampleWithBLOBs(example);
	}
	
	/**
	 * 导出预约信息
	 * @param guestid
	 * @return
	 */
	public List<YuyueState> orderYuyue(Integer guestid){
		return mapper.getYuyue(guestid);
	}
	
	/**
	 * 发布通知消息
	 * @param type
	 * @param notice
	 * @return 
	 */
	public int publishNotice(Integer type,Integer guestid, String notice) {
		
		if(type==0) {
			UserExample example = new UserExample();
			List<User> list = userMapper.selectByExample(example);
			Boardmessage boardmessage = new Boardmessage();
			
			for (User user : list) {
				boardmessage.setAnonymous(false);
				boardmessage.setContent(notice);
				boardmessage.setGuestid(guestid);
				boardmessage.setStuid(user.getStuid());
				boardmessage.setPubtime(new Date());
				try {
					boardmessageMapper.insertSelective(boardmessage);
				} catch (Exception e) {
					return 0;
				}
				
			}
		}
		
		return 1;
	}
	
	/**
	 * 修改预约转态
	 * @param stuid
	 * @param guestid
	 * @param status
	 * @return
	 */
	public int alterYuyueStatus(String stuid,Integer guestid,boolean status) {
		Registrationrecord registrationrecord = new Registrationrecord();
		registrationrecord.setStatus(status);
		RegistrationrecordExample example = new RegistrationrecordExample();
		RegistrationrecordExample.Criteria criteria = example.createCriteria();
		criteria.andStuidEqualTo(stuid);
		criteria.andGuestidEqualTo(guestid);
		try {
			registrationrecordMapper.updateByExampleSelective(registrationrecord, example);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}
	/**
	 * 上传真人图书活动回顾
	 * @param txt
	 * @param guestid
	 * @return
	 */
	public String uploadReview(String txt,Integer guestid) {
		
		PastreviewExample example = new PastreviewExample();
		PastreviewExample.Criteria criteria = example.createCriteria();
		criteria.andGuestidEqualTo(guestid);
		
		List<Pastreview> list = pastreviewMapper.selectByExample(example);
		if(list.size()==0) {
			Pastreview pastreview = new Pastreview();
			pastreview.setContent(txt);
			pastreview.setGuestid(guestid);
			pastreview.setTime(new Date());
			
			try {
				pastreviewMapper.insertSelective(pastreview);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		}else {
			Pastreview pastreview = new Pastreview();
			pastreview.setContent(txt);
			pastreview.setTime(new Date());
			
			try {
				pastreviewMapper.updateByExampleSelective(pastreview, example);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		}
		
		
		
	}
	
	/**
	 * 获取回顾
	 * @param guestid
	 * @return
	 */
	public Pastreview getReview(Integer guestid) {
		PastreviewExample example = new PastreviewExample();
		PastreviewExample.Criteria criteria = example.createCriteria();
		criteria.andGuestidEqualTo(guestid);
		
		try {
			
			List<Pastreview> p = pastreviewMapper.selectByExampleWithBLOBs(example);
			if(p.size()==0)
				return null;
			else
				return p.get(0);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取管理员页面的视频管理
	 * @param page
	 * @param count
	 * @return
	 */
	public PageList getManagerVideo(Integer page,Integer count) {
		PageList pageGuest = new PageList();
		
		PageHelper.startPage(page, count);
		VedioExample example = new VedioExample();
		
		
		List<Vedio> list = vedioMapper.selectByExample(example);
		PageInfo<Vedio> pageinfo = new PageInfo<Vedio>(list);
		pageGuest.setPage(pageinfo.getPageNum());
		pageGuest.setRecords(pageinfo.getTotal());
		pageGuest.setTotal(pageinfo.getPages());
		pageGuest.setRows(list);
		
		return pageGuest;
	}
	/**
	 * 添加视频
	 * @param title
	 * @param url
	 * @param imgpath
	 * @param description
	 * @return
	 */
	@Transactional
	public Integer addVideo(String title,String url,String imgpath,Integer type,String description) {
		Vedio vedio = new Vedio();
		vedio.setImg(imgpath);
		vedio.setCreatetime(new Date());
		vedio.setDescription(description);
		vedio.setPlayvolume(0);
		vedio.setUrl(url);
		vedio.setTypeid(type);
		vedio.setTitle(title);
		
		try {
			vedioMapper.insertSelective(vedio);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	/**
	 * 删除一个视频
	 * @param id
	 * @return
	 */
	@Transactional
	public Integer delVideo(Integer id) {
		try {
			vedioMapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			return 0;
			// TODO: handle exception
		}
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
	@Transactional
	public Integer alterVedio(Integer id,String title,String url,String imgpath,String description,Integer type,Integer playvolume) {
		Vedio vedio = new Vedio();
		vedio.setId(id);
		vedio.setPlayvolume(playvolume);
		vedio.setImg(imgpath);
		vedio.setCreatetime(new Date());
		vedio.setDescription(description);
		vedio.setUrl(url);
		vedio.setTypeid(type);
		vedio.setTitle(title);
		
		try {
			vedioMapper.updateByPrimaryKeySelective(vedio);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public PageList loadAllNotice(Integer page, Integer count) {
		PageList pageGuest = new PageList();
		
		PageHelper.startPage(page, count);
		
		
	
		List<Boardmessage> list = mapper.loadBoardMessage();
		PageInfo<Boardmessage> pageinfo = new PageInfo<Boardmessage>(list);
		pageGuest.setPage(pageinfo.getPageNum());
		pageGuest.setRecords(pageinfo.getTotal());
		pageGuest.setTotal(pageinfo.getPages());
		pageGuest.setRows(list);
		
		return pageGuest;
		
	}

	public Integer delNotice(String content) {
		BoardmessageExample example= new BoardmessageExample();
		BoardmessageExample.Criteria criteria = example.createCriteria();
		criteria.andContentEqualTo(content);
		try {
			boardmessageMapper.deleteByExample(example);
			return 1;
		} catch (Exception e) {
			return 0;
			// TODO: handle exception
		}
	}

	public Integer alertRtmp(String address) {
		Systemset systemset = new Systemset();
		systemset.setLiveaddress(address);
		SystemsetExample example = new SystemsetExample();
		try {
			systemsetMapper.updateByExampleSelective(systemset, example);
			return 1;
		} catch (Exception e) {
			return 0;
			// TODO: handle exception
		}
		
	}
	/**
	 * 获取管理员列表
	 * @param type
	 * @return
	 */
	public PageList getAdminlist(Integer page,Integer count,Integer type) {
		
		
		PageList pageGuest = new PageList();
		
		PageHelper.startPage(page, count);
		
		
	
		List<AdminList> list = mapper.getAdminList(type);
		PageInfo<AdminList> pageinfo = new PageInfo<AdminList>(list);
		pageGuest.setPage(pageinfo.getPageNum());
		pageGuest.setRecords(pageinfo.getTotal());
		pageGuest.setTotal(pageinfo.getPages());
		pageGuest.setRows(list);
		
		return pageGuest;
	}


	public List<AdminList> searchAdminList(String start, String end, String username) {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			map.put("start", sdf.parse(start));
			if(end==null || end =="") 
				map.put("end", new Date());
			else 
				map.put("end", sdf.parse(end));

			map.put("username", "%"+username+"%");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		List<AdminList> list = mapper.searchAdminList(map);
		
		
		
		return list;
	}

	public Integer addVedioType(Integer type, String typename, String description) {
		
		Vediotype vediotype = new Vediotype();
		vediotype.setCreatetime(new Date());
		vediotype.setDescription(description);
		
		vediotype.setTypename(typename);
		try {
			if(type==-1) {
				vediotypeMapper.insertSelective(vediotype);
				return 1;
			}else {
				vediotype.setTypeid(type);
				vediotypeMapper.updateByPrimaryKeySelective(vediotype);
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	public List<Vedio> scanFile(String filepath,Integer type) throws FileNotFoundException {
		File file = new File("D:\\public\\node\\"+filepath);
		List<Vedio> list = new ArrayList<>();
		if (!file.isDirectory()) {
		       return null;
		} else if (file.isDirectory()) {
		        String[] filelist = file.list();
		        for (int i = 0; i < filelist.length; i++) { //循环文件列表，将每一个文件的信息都遍历到
		                File readfile = new File(filepath + "\\" + filelist[i]);  //获取文件列表中的文件
		                if (!readfile.isDirectory()) {
		                      
		                        Vedio vedio = new Vedio();
		                        vedio.setCreatetime(new Date());
		                        vedio.setDescription(null);
		                        vedio.setImg("/video/videoimg/"+readfile.getName()+".jpg");
		                        vedio.setPlayvolume(0);
		                        vedio.setTitle(readfile.getName().substring(0, readfile.getName().lastIndexOf(".")));
		                        vedio.setUrl("video/"+filepath+"/"+readfile.getName());
		                        vedio.setTypeid(type);
		                        list.add(vedio);
		                        vedioMapper.insertSelective(vedio);
		                        

		                } else if (readfile.isDirectory()) {  //如果再扫描到文件夹，则递归再次进入文件夹中扫描文件夹中的文件
		                        scanFile(filepath + "\\" + filelist[i],type);
		                }
		        }

		}
		return list;
	}
}
