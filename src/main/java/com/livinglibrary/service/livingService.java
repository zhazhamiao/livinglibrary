package com.livinglibrary.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.request.CollectionAdminRequest.Create;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.livinglibrary.mapper.BoardmessageMapper;
import com.livinglibrary.mapper.CarouselMapper;
import com.livinglibrary.mapper.GuestMapper;
import com.livinglibrary.mapper.GuestcommentMapper;
import com.livinglibrary.mapper.GuesttypeMapper;
import com.livinglibrary.mapper.InvitationMapper;
import com.livinglibrary.mapper.MyselfMapper;
import com.livinglibrary.mapper.RegistrationrecordMapper;
import com.livinglibrary.mapper.SystemsetMapper;
import com.livinglibrary.mapper.UserMapper;
import com.livinglibrary.mapper.VedioMapper;
import com.livinglibrary.mapper.VediotypeMapper;
import com.livinglibrary.po.Boardmessage;
import com.livinglibrary.po.BoardmessageExample;
import com.livinglibrary.po.Carousel;
import com.livinglibrary.po.CarouselExample;
import com.livinglibrary.po.Guest;
import com.livinglibrary.po.GuestExample;
import com.livinglibrary.po.Guestcomment;
import com.livinglibrary.po.GuestcommentExample;
import com.livinglibrary.po.Guesttype;
import com.livinglibrary.po.GuesttypeExample;
import com.livinglibrary.po.Invitation;
import com.livinglibrary.po.InvitationExample;
import com.livinglibrary.po.MyGuest;
import com.livinglibrary.po.PageList;
import com.livinglibrary.po.Registrationrecord;
import com.livinglibrary.po.RegistrationrecordExample;
import com.livinglibrary.po.StuYuyue;
import com.livinglibrary.po.Systemset;
import com.livinglibrary.po.SystemsetExample;
import com.livinglibrary.po.User;
import com.livinglibrary.po.UserExample;
import com.livinglibrary.po.Vedio;
import com.livinglibrary.po.VedioExample;
import com.livinglibrary.po.Vediotype;
import com.livinglibrary.po.VediotypeExample;

import httpclient.AIServicec;
import httpclient.AuthService;


@Service
public class livingService {

	@Resource 
	GuestMapper guestMapper;
	@Resource
	GuesttypeMapper guesttypeMapper;
	@Resource
	InvitationMapper invitationMapper;
	@Resource
	UserMapper userMapper;
	@Resource
	CarouselMapper carouselMapper;
	@Resource
	RegistrationrecordMapper registrationrecordMapper;
	@Resource
	GuestcommentMapper guestcommentMapper;
	@Resource
	BoardmessageMapper boardmessageMapper;
	@Resource 
	VedioMapper vedioMapper;
	@Resource
	MyselfMapper myselfMapper;
	@Resource
	SystemsetMapper systemsetMapper;
	@Resource
	VediotypeMapper vediotypeMapper;
	
	
	//查询所有嘉宾
	public List<Guest> getAllGuest(){
		GuestExample example = new GuestExample();
		GuestExample.Criteria criteria = example.createCriteria();
		criteria.andIsshowNotEqualTo("1");
		example.setOrderByClause("GuestId DESC");
		return guestMapper.selectByExampleWithBLOBs(example);
	}
	
	
	//查询一个嘉宾的详细信息
	public Guest guestInforation(Integer GuestId){
		MyGuest myGuest = new MyGuest();
		
		GuestExample example1 = new GuestExample();
		GuestExample.Criteria criteria1 = example1.createCriteria();
		criteria1.andGuestidEqualTo(GuestId);
		criteria1.andIsshowNotEqualTo("1");
		List<Guest> list1 = guestMapper.selectByExampleWithBLOBs(example1);
		if(list1.isEmpty())
			return null;
		return list1.get(0);
		/*if(list1.size()==1) {
			myGuest.setGuestId(GuestId);
			myGuest.setGuestName(list1.get(0).getGuestname());
			myGuest.setGuestImg(list1.get(0).getGuestimg());
			myGuest.setSummary(list1.get(0).getSummary());
			
			GuesttypeExample example2 = new GuesttypeExample();
			GuesttypeExample.Criteria criteria2 = example2.createCriteria();
			criteria2.andTypeidEqualTo(list1.get(0).getGuesttype());
			
			List<Guesttype> list2 = guesttypeMapper.selectByExample(example2);
			myGuest.setGuestType(list2.get(0).getTypename());
			
			InvitationExample example3 = new InvitationExample();
			example3.setOrderByClause("BeginTime DESC");
			InvitationExample.Criteria criteria3 = example3.createCriteria();
			criteria3.andGuestidEqualTo(GuestId);
			
			List<Invitation> list3 = invitationMapper.selectByExample(example3);
			
			//if(list3.size()>0) {
				myGuest.setBeginTime(new Date());
				myGuest.setEndTime(new Date());
			//}
			
		}
		return myGuest;*/
		
		
	}
	
	//查询所有嘉宾类型
	public List<Guesttype> getAllGuestType(){
		GuesttypeExample example = new GuesttypeExample();
		
		return guesttypeMapper.selectByExample(example);
	}
	
	//模糊查找嘉宾
	public PageList searchGuest(String name,Integer page,Integer count){
		
		
		PageList pageGuest = new PageList();
		
		PageHelper.startPage(page, count);
		GuestExample example = new GuestExample();
		GuestExample.Criteria criteria = example.createCriteria();
		criteria.andGuestnameLike("%"+name+"%");
		criteria.andIsshowNotEqualTo("1");
		List<Guest> list = guestMapper.selectByExampleWithBLOBs(example);
		
		PageInfo<Guest> pageinfo = new PageInfo<Guest>(list);
		pageGuest.setPage(pageinfo.getPageNum());
		pageGuest.setRecords(pageinfo.getSize());
		pageGuest.setTotal(pageinfo.getPages());
		pageGuest.setRows(list);
		
		
		
		return pageGuest;
	}
	
	//添加一个嘉宾，要用事物
	@Transactional
	public boolean addGuest(String guestName,String begintime,String endtime,String address,Integer GuestType,String img,String summary,String isShow) {
		
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
		guest.setGuestimg(img);
		guest.setGuestname(guestName);
		guest.setGuesttype(GuestType);
		guest.setIsshow(isShow);
		guest.setSummary(summary);
		
		guestMapper.insertSelective(guest);
		
		return true;
	}
	
	//查询单个类别的所有Guest
	public PageList getTypeGuest(Integer typeid,Integer page,Integer count){
		
		PageList pageGuest = new PageList();
		
		PageHelper.startPage(page, count);
		
		GuestExample example = new GuestExample();
		GuestExample.Criteria criteria = example.createCriteria();
		criteria.andIsshowNotEqualTo("1");
		//criteria.andGuesttypeEqualTo(typeid);
		List<Guest> list = guestMapper.selectByExampleWithBLOBs(example);
		
		PageInfo<Guest> info = new PageInfo<Guest>(list);
		
		pageGuest.setPage(info.getPageNum());
		pageGuest.setRecords(info.getPageSize());
		pageGuest.setTotal(info.getPages());
		pageGuest.setRows(list);
		
		return pageGuest;
	}
	
	//删除一个嘉宾
	@Transactional
	public int delGuest(Integer guestid) {
		int effNums=guestMapper.deleteByPrimaryKey(guestid);
		return effNums;
	}
	
	//判断是否存在此用户（用于登录验证）
	public String loginFor(String stuid,String pwd,String type) {
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andStuidEqualTo(stuid);
		criteria.andPasswordEqualTo(pwd);
		criteria.andTypeEqualTo(type);
		criteria.andStateEqualTo(1);
		
		List<User> list = userMapper.selectByExample(userExample);
		
		if(list.size()==1) {
			return list.get(0).getName();
		}else
			return "error";
		
	}
	/**
	 *  根据stuid获取用户信息
	 * @param stuid
	 * @return
	 */
	public User searchByStuid(String stuid) {
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andStuidEqualTo(stuid);
		List<User> list = userMapper.selectByExample(userExample);
		
		return list.get(0);
	}
	
	//修改一个嘉宾的信息
	/*@Transactional
	public boolean alterGuest(Integer guestid,String guestName,String begintime,String endtime,String address,Integer GuestType,String img,String summary,String isShow) {
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
		guest.setGuestid(guestid);
		
		if (!img.equals("unchange")) {
			guest.setGuestimg(img);
		}
		guest.setGuestname(guestName);
		guest.setGuesttype(GuestType);
		guest.setIsshow(isShow);
		guest.setSummary(summary);
		
		try {
			guestMapper.updateByPrimaryKeyWithBLOBs(guest);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}*/
	
	//获得轮播图ID和URL
	public List<Carousel> getCarousel(){
		CarouselExample example = new CarouselExample();
		return carouselMapper.selectByExample(example);
	}
	
	//删除一个轮播图ID和URL(根据图片地址删除)
	@Transactional
	public List<Carousel> delCarousel(String url){
		CarouselExample example = new CarouselExample();
		CarouselExample.Criteria criteria = example.createCriteria();
		criteria.andUrlEqualTo(url);
		carouselMapper.deleteByExample(example);
		//删除完之后执行查询语句，返回删除后的结果
		
		return getCarousel();
	}
	//添加一张轮播图
	@Transactional
	public List<Carousel> addCarousel(String url) {
		Carousel carousel = new Carousel();
		carousel.setUrl(url);
		
		carouselMapper.insertSelective(carousel);
		
		return getCarousel();
	}
	//修改一张轮播图（根据url）
	@Transactional
	public List<Carousel> alterCarouselByUrl(String oldurl,String newurl){
		CarouselExample example = new CarouselExample();
		CarouselExample.Criteria criteria = example.createCriteria();
		criteria.andUrlEqualTo(oldurl);
		
		List<Carousel> list = carouselMapper.selectByExample(example);
		Integer id=0;
		if(list.size()==1) {
			id = list.get(0).getId();
		}
		Carousel carousel = new Carousel();
		carousel.setId(id);
		carousel.setUrl(newurl);
		if(carouselMapper.updateByPrimaryKey(carousel)==1) {
			return getCarousel();
		}else {
			return null;
		}
	}
	
	//修改一张轮播图（根据id）
	@Transactional
	public List<Carousel> alterCarouselById(Integer id,String newurl){
		
		Carousel carousel = new Carousel();
		carousel.setId(id);
		carousel.setUrl(newurl);
		if(carouselMapper.updateByPrimaryKey(carousel)==1) {
			return getCarousel();
		}else {
			return null;
		}
	}
	/**
	 * 判断该用户是否已经预约过这场讲座
	 * @param stuid
	 * @param guestid
	 * @return
	 */
	public Integer isYuyue(String stuid,Integer guestid) {
		RegistrationrecordExample example = new RegistrationrecordExample();
		RegistrationrecordExample.Criteria criteria = example.createCriteria();
		criteria.andStuidEqualTo(stuid);
		criteria.andGuestidEqualTo(guestid);
		List<Registrationrecord> list = registrationrecordMapper.selectByExample(example);
		if(list.size()>=1) {
			criteria.andStateEqualTo(1);
			List<Registrationrecord> list1 = registrationrecordMapper.selectByExample(example);
			if(list1.size()>=1)
				return 1;
			else
				return 2;
		}else {
			return 0;
		}
		
	}
	/**
	 * 预约
	 * @param stuid
	 * @param guestid
	 * @return
	 */
	@Transactional
	public synchronized Integer Yuyue(String stuid,Integer guestid) {
		
		
		GuestExample example = new GuestExample();
		GuestExample.Criteria criteria = example.createCriteria();
		criteria.andGuestidEqualTo(guestid);
		
		List<Guest> list = guestMapper.selectByExample(example);
		if(list.size()>=1) {
			int ordernum = list.get(0).getOrdernum();
			int allnum = list.get(0).getAllnum();
			if(ordernum>=allnum) {
				return 2;
			}
		}
		
		
		
		Registrationrecord registrationrecord = new Registrationrecord();
		registrationrecord.setGuestid(guestid);
		registrationrecord.setState(1);//1表示正常状态，2表示已取消
		registrationrecord.setStuid(stuid);
		try {
			registrationrecordMapper.insertSelective(registrationrecord);
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	/**
	 * 获取一场讲座下的所有审核通过的留言
	 * @param guestid 讲座id
	 * @return
	 */
	public List<Guestcomment> guestcomments(Integer guestid){
		GuestcommentExample example = new GuestcommentExample();
		example.setOrderByClause("addtime desc");
		GuestcommentExample.Criteria criteria = example.createCriteria();
		criteria.andGuestidEqualTo(guestid);
		criteria.andIsshowEqualTo(1);
		
		return guestcommentMapper.selectByExampleWithBLOBs(example);
		
	}
	
	/**
	 * 用户发表评论
	 * @param stuid 学号
	 * @param guestid	讲座id
	 * @param content	评论内容
	 * @param isrely	是否是回复评论
	 * @return
	 */
	@Transactional
	public Integer addComment(String stuid,Integer guestid,String content,Integer isrely) {
		Guestcomment guestcomment = new Guestcomment();
		JSONObject jsonObject = JSONObject.parseObject(AIServicec.sendPost("{\"text\":\""+content+"\"}"));
		System.out.println(jsonObject);
		if(jsonObject.getString("error_code")=="110") {
			AuthService.token = AuthService.getAuth();
			jsonObject = JSONObject.parseObject(AIServicec.sendPost("{\"text\":\""+content+"\"}"));
		}else if(jsonObject.getString("error_code")!=null) {
			guestcomment.setAirecommend(3);
			guestcomment.setKeyword("0");
		}else {
			String items = jsonObject.getString("items");
			System.out.println(jsonObject);
			//System.out.println();
			JSONArray jsonArray = JSONArray.parseArray(items); 
			if(jsonArray.size()==0) {
				guestcomment.setAirecommend(3);
				guestcomment.setKeyword("0");
			}else {
				JSONObject j1 = jsonArray.getJSONObject(0);
				guestcomment.setAirecommend(j1.getInteger("sentiment"));
				guestcomment.setKeyword(j1.getString("positive_prob"));
			}
		}
		
		
		guestcomment.setAddtime(new Date());
		guestcomment.setContent(content);
		guestcomment.setGuestid(guestid);
		guestcomment.setIsreply(isrely);
		guestcomment.setIsshow(0);
		guestcomment.setStuid(stuid);
		try {
			guestcommentMapper.insertSelective(guestcomment);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	/**
	 * 取消预约（取消后不可再预约）
	 * @param stuid
	 * @param guestid
	 * @return
	 */
	public Integer cancelYuyue(String stuid,Integer guestid) {
		
		RegistrationrecordExample example = new RegistrationrecordExample();
		RegistrationrecordExample.Criteria criteria = example.createCriteria();
		criteria.andStuidEqualTo(stuid);
		criteria.andGuestidEqualTo(guestid);
		
		Registrationrecord registrationrecord = new Registrationrecord();
		registrationrecord.setGuestid(guestid);
		registrationrecord.setStuid(stuid);
		registrationrecord.setState(2);//设为已取消
		try {
			registrationrecordMapper.updateByExampleSelective(registrationrecord, example);
			return 1;//取消成功
		} catch (Exception e) {
			return 0;//取消失败
		}
		
	}
	 
	/**
	 * 根据用户获取通知消息
	 * @param stuid
	 * @return
	 */
	public List<Boardmessage> getNotice(String stuid,Integer type){
		BoardmessageExample example = new BoardmessageExample();
		example.setOrderByClause("pubtime desc");
		BoardmessageExample.Criteria criteria = example.createCriteria();
		criteria.andStuidEqualTo(stuid);
		if(type==0)
			criteria.andAnonymousEqualTo(false);
		List<Boardmessage> list = boardmessageMapper.selectByExample(example);
		if(type==1) {
			Boardmessage boardmessage = new Boardmessage();
			boardmessage.setAnonymous(true);
			
			boardmessageMapper.updateByExampleSelective(boardmessage, example);
		}
		
		
		return list;
	}
	
	/**
	 * 获取精彩视频列表
	 * @return
	 */
	public List<Vedio> getWonderfulVedio(){
		
		List<Vediotype> types = getVedioType();
		List<Vedio> list = new ArrayList<>();
		if(types.size()!=0) {
			for(int i=0;i<types.size();++i) {
				
				list.addAll(myselfMapper.getVedioByTypeidlimit(types.get(i).getTypeid()));
			}
		}
		
		
		
		
		return list;
	}
	public Vedio getVedio(Integer id) {
		Vedio v = vedioMapper.selectByPrimaryKey(id);
		Vedio vedio = new Vedio();
		vedio.setId(id);
		vedio.setPlayvolume(v.getPlayvolume()+1);
		vedioMapper.updateByPrimaryKeySelective(vedio);
		return v;
	}
	public User getUserInfo(String stuid) {
		return userMapper.selectByPrimaryKey(stuid);
	}
	/**
	 * 我的预约
	 * @param stuid
	 * @return
	 */
	public List<StuYuyue> getStuYuYue(String stuid){
		return myselfMapper.getStuYuyue(stuid);
	}
	/**
	 * 获取我的评论
	 * @param stuid
	 * @return
	 */
	public List<Guestcomment> getMycomment(String stuid){
		GuestcommentExample example = new GuestcommentExample();
		GuestcommentExample.Criteria criteria = example.createCriteria();
		criteria.andStuidEqualTo(stuid);
		return guestcommentMapper.selectByExampleWithBLOBs(example);
	}
	
	public Systemset getSysSet() {
		SystemsetExample example = new SystemsetExample();
		
		return systemsetMapper.selectByExample(example).get(0);
	}


	public Integer updateUserbyself(String stuid, String username, Boolean sex, String college, String phone,String email, String pass, String type) {
		User user = new User();
		user.setCollege(college);
		user.setName(username);
		user.setPhone(phone);
		user.setEmail(email);
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


	public List<Vediotype> getVedioType() {
		VediotypeExample example = new VediotypeExample();
		return vediotypeMapper.selectByExample(example);
	}


	public PageList getMoreVideo(Integer typeid, Integer page, Integer count) {
		PageList pageGuest = new PageList();
		
		PageHelper.startPage(page, count);
		
		VedioExample example = new VedioExample();
		VedioExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("createtime desc");
		criteria.andTypeidEqualTo(typeid);
		List<Vedio> list = vedioMapper.selectByExample(example);
		
		PageInfo<Vedio> info = new PageInfo<Vedio>(list);
		
		pageGuest.setPage(info.getPageNum());
		pageGuest.setRecords(info.getTotal());
		pageGuest.setTotal(info.getPages());
		pageGuest.setRows(list);
		
		
		
		return pageGuest;
	}

}
