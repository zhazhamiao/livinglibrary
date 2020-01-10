package com.livinglibrary.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor  {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
        System.out.println("------preHandle------");
        //获取session
        HttpSession session = request.getSession(true);
        
        System.out.println(session.getAttribute("type"));
        //判断用户ID是否存在，不存在就跳转到登录界面
        if(session.getAttribute("type") == null){
            System.out.println("------:跳转到login页面！");
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }else if(!session.getAttribute("type").equals("4")){
        	System.out.println("------:跳转到index页面！");
            response.sendRedirect(request.getContextPath()+"/index");
            return false;
        }else {
            session.setAttribute("type", session.getAttribute("type"));
            return true;
        }
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub21
		
	}

}
