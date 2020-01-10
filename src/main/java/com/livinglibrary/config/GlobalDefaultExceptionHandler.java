package com.livinglibrary.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler{
	//private static Logger logger = LogManager.getLogger(GlobalDefaultExceptionHandler.class.getName());
	 @Override
	    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
	        System.out.println("错误");
	        ex.printStackTrace();
	        return new ResponseEntity<Object>("出错了", status);

	    }
}
