package com.livinglibrary.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.livinglibrary.mapper.ForumcategoryMapper;
import com.livinglibrary.mapper.ForumcommentMapper;
import com.livinglibrary.mapper.ForumpostMapper;
import com.livinglibrary.mapper.ForumuserMapper;
import com.livinglibrary.mapper.MyselfMapper;
import com.livinglibrary.mapper.UserMapper;
import com.livinglibrary.po.FcommentsList;
import com.livinglibrary.po.Forumcategory;
import com.livinglibrary.po.ForumcategoryExample;
import com.livinglibrary.po.Forumcomment;
import com.livinglibrary.po.Forumpost;
import com.livinglibrary.po.ForumpostExample;
import com.livinglibrary.po.Forumuser;
import com.livinglibrary.po.ForumuserExample;
import com.livinglibrary.po.FpostList;
import com.livinglibrary.po.PageList;
import com.livinglibrary.po.User;
import com.livinglibrary.po.UserExample;

@Service
public class ForumService {

	@Resource
	UserMapper userMapper;
	@Resource
	ForumuserMapper forumuserMapper;
	@Resource
	ForumcategoryMapper forumcategoryMapper;
	@Resource
	ForumpostMapper forumpostMapper;
	@Resource
	ForumcommentMapper forumcommentMapper;
	@Resource
	MyselfMapper myselfMapper;
	
	/**
	 * 
	 * @param order
	 * @param offset
	 * @param limit
	 * @param category_id
	 * @return 分页后的帖子列表
	 */
	public PageList getPostList(String order,Integer offset,Integer limit,Integer categoryid) {
		PageList pageList = new PageList();
		Map<String, Object> map = new HashMap<>();
		
		
		PageHelper.startPage(offset, limit);
		
		map.put("order", order);
		map.put("page", offset);
		map.put("limit", limit);
		if(categoryid==null || categoryid==0) {
			map.put("categoryid", "");
		}else {
			map.put("categoryid", categoryid);
		}
		
		
		List<FpostList> list = myselfMapper.getPostList(map);
		
		PageInfo<FpostList> info = new PageInfo<FpostList>(list);
		pageList.setPage(info.getPageNum());
		pageList.setRecords(info.getSize());
		pageList.setTotal(info.getPages());
		pageList.setRows(list);
		return pageList;
	}
	/**
	 * 
	 * @param postid
	 * @return 返回该postid的主内容
	 */
	public String getPost(Integer postid) {
		ForumpostExample example = new ForumpostExample();
		ForumpostExample.Criteria criteria = example.createCriteria();
		criteria.andPostidEqualTo(postid);
		
		List<Forumpost> list = forumpostMapper.selectByExampleWithBLOBs(example);
		
		if(list.size()>0) {
			return list.get(0).getContent();
		}
		return null;
	}
	/**
	 * 
	 * @return 返回所有种类
	 */
	public List<Forumcategory> getCategory(){
		ForumcategoryExample example = new ForumcategoryExample();
		
		return forumcategoryMapper.selectByExample(example);
	}
	
	/**
	 * 
	 * @param post_id
	 * @param order
	 * @param offset
	 * @param limit
	 * @return 返回该postid下的所有评论
	 */
	public PageList getComments(Integer post_id,String order,Integer offset,Integer limit) {
		PageList pageList = new PageList();
		Map<String, Object> map = new HashMap<>();
		
		
		PageHelper.startPage(offset, limit);
		
		map.put("order", order);
		map.put("postid", post_id);
		
		List<FcommentsList> list = myselfMapper.getCommentList(map);
		
		PageInfo<FcommentsList> info = new PageInfo<FcommentsList>(list);
		pageList.setPage(info.getPageNum());
		pageList.setRecords(info.getSize());
		pageList.setTotal(info.getPages());
		pageList.setRows(list);
		return pageList;
	}
	
	/**
	 * 
	 * @param category_id
	 * @param userid
	 * @param title
	 * @param content
	 * @param summary
	 * @return 添加一个帖子，成功返回true，失败返回false
	 */
	@Transactional
	public boolean addPost(Integer category_id,Integer userid,String title,String content,String summary) {
		Forumpost forumpost = new Forumpost();
		forumpost.setAuthor(userid);
		forumpost.setCategory(category_id);
		forumpost.setCommentnum(0);
		forumpost.setContent(content);
		forumpost.setFollownum(0);
		forumpost.setLooknum(0);
		forumpost.setStatus(1);
		forumpost.setSummary(summary);
		forumpost.setTime(new Date());
		forumpost.setTitle(title);
		
		try {
			forumpostMapper.insertSelective(forumpost);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @param postid
	 * @param userid
	 * @param parentid
	 * @param content
	 * @return 给postid的帖子发表评论，或者评论的回复
	 */
	@Transactional
	public boolean addComment(Integer postid,Integer userid,Integer parentid,String content) {
		Forumcomment forumcomment = new Forumcomment();
		forumcomment.setAddtime(new Date());
		forumcomment.setIsanonymous(false);
		forumcomment.setPostid(postid);
		forumcomment.setReplaycontent(content);
		forumcomment.setUserid(userid);
		
		if(parentid!=null) {
			forumcomment.setParentid(parentid);
		}
		
		try {
			forumcommentMapper.insertSelective(forumcomment);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	/**
	 * 
	 * @param stuid
	 * @param password
	 * @return 返回一个Fotumuser的json数据，根据status判断登录状态
	 */
	public Forumuser checkLogin(String stuid,String password) {
		
		Forumuser forumuser = new Forumuser();
		
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andStuidEqualTo(stuid);
		criteria.andPasswordEqualTo(password);
		List<User> list = userMapper.selectByExample(example);
		
		if(list.size()==1) {
			ForumuserExample example2 = new ForumuserExample();
			ForumuserExample.Criteria criteria2 = example2.createCriteria();
			criteria2.andStuidEqualTo(stuid);
			List<Forumuser> list2 = forumuserMapper.selectByExample(example2);
			
			if(list2.size()==0) {
				forumuser.setStatus((byte) -1);
				return forumuser;
			}else {
				return list2.get(0);
			}
			
		}else {
			forumuser.setStatus((byte) -2);
			return forumuser;
		}
	}
	/**
	 * 
	 * @param post_id
	 * @return 将postid的帖子置为不可用
	 */
	@Transactional
	public boolean deletePost(Integer postid) {
		
		ForumpostExample example = new ForumpostExample();
		ForumpostExample.Criteria criteria = example.createCriteria();
		criteria.andPostidEqualTo(postid);
		
		List<Forumpost> list  = forumpostMapper.selectByExample(example);
		
		if(list.size()==1) {
			list.get(0).setStatus(2);//表示该帖子删除，不显示
			try {
				forumpostMapper.updateByPrimaryKey(list.get(0));
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
		
		return false;
		
	}
	/**
	 * 
	 * @param comment_id
	 * @return 将该条评论删除
	 */
	@Transactional
	public boolean deleteComment(Integer commentid) {
		try {
			forumcommentMapper.deleteByPrimaryKey(commentid);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
