package com.livinglibrary.DataController;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.livinglibrary.po.Forumcategory;
import com.livinglibrary.po.Forumuser;
import com.livinglibrary.po.PageList;
import com.livinglibrary.service.ForumService;
import com.livinglibrary.util.FileUtil;

@RestController
@RequestMapping("/v1")
public class ForumDataController {

	@Resource
	ForumService forumService;
	/**
	 * 
	 * @param order
	 * @param offset
	 * @param limit
	 * @param category_id
	 * @return 帖子列表
	 */
	@RequestMapping(value="/postList")
	public PageList postList(String order,Integer offset,Integer limit,Integer category_id){
		return forumService.getPostList(order, offset, limit, category_id);
	}
	
	/**
	 * 
	 * @param postid
	 * @return 返回该postid的主内容
	 */
	@RequestMapping("/post")
	public String getPost(Integer post_id) {
		return forumService.getPost(post_id);
	}
	/**
	 * 
	 * @return 所有的分类
	 */
	@RequestMapping("/category")
	public List<Forumcategory> getCategory(){
		return forumService.getCategory();
	}
	
	/**
	 * 
	 * @param post_id
	 * @param order
	 * @param offset
	 * @param limit
	 * @return 获得该postid下的所有评论
	 */
	@RequestMapping("/comment")
	public PageList getComment(Integer post_id,String order,Integer offset,Integer limit) {
		return forumService.getComments(post_id, order, offset, limit);
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
	@RequestMapping("/addPost")
	public boolean addPost(Integer category_id,Integer userid,String title,String content,String summary) {
		return forumService.addPost(category_id, userid, title, content, summary);
	}
	
	/**
	 * 
	 * @param postid
	 * @param userid
	 * @param parentid
	 * @param content
	 * @return 给postid的帖子发表评论，或者评论的回复
	 */
	@RequestMapping("/addComment")
	public boolean addComment(Integer postid,Integer userid,Integer parentid,String content) {
		return forumService.addComment(postid, userid, parentid, content);
	}
	/**
	 * 
	 * @param stuid
	 * @param password
	 * @return 返回一个Fotumuser的json数据，根据status判断登录状态
	 */
	@RequestMapping("/checkLogin")
	public Forumuser checkLogin(String stuid,String password) {
		return forumService.checkLogin(stuid, password);
	}
	/**
	 * 
	 * @param data
	 * @return 上传一张图片到文件服务器，返回文件名
	 */
	@RequestMapping("/Img")
	public String postImg(@RequestParam("data") MultipartFile data) {
		String fileName="";
		try {
			fileName =  FileUtil.uploadFile(data, "forum");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return fileName;
	}
	
	/**
	 * 
	 * @param post_id
	 * @return 将postid的帖子置为不可用
	 */
	@RequestMapping("/deletePost")
	public boolean deletePost(Integer post_id) {
		return forumService.deletePost(post_id);
	}
	/**
	 * 
	 * @param comment_id
	 * @return 将该条评论删除
	 */
	@RequestMapping("/deleteComment")
	public boolean deleteComment(Integer comment_id) {
		return forumService.deleteComment(comment_id);
	}
}
