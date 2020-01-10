package com.livinglibrary.PageController;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class ExecptionController {
	 @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
	    public String authenticationException(HttpServletRequest request, HttpServletResponse response) {
		 System.err.println("未登录");
	        if (WebUtilsPro.isAjaxRequest(request)) {
	            // 输出JSON
	            Map<String,Object> map = new HashMap<>();
	            map.put("code", "-999");
	            map.put("message", "未登录");
	            return map.toString();
	        } else {
	            return "redirect:/login";
	        }
	    }

	    /**
	     * 权限异常
	     */
	    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
	    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
	    	System.err.println("权限不足");
	        if (WebUtilsPro.isAjaxRequest(request)) {
	            // 输出JSON
	            Map<String,Object> map = new HashMap<>();
	            map.put("code", "-998");
	            map.put("message", "无权限");
	            return map.toString();
	        } else {
	            return "redirect:/403";
	        }
	    }
}

class WebUtilsPro {

    /**
     * 是否是Ajax请求
     *
     * @param request
     * @return
     * @author SHANHY
     * @create 2017年4月4日
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }
}
