package com.livinglibrary.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpException;
import org.springframework.web.multipart.MultipartFile;

import httpclient.HttpRequest;

public class FileUtil {

	
	public static String uploadFile(MultipartFile Img, String path) throws Exception { 
		String fileName = "";
		 File f = null;
        try {
            f=File.createTempFile(path, ".jpg");         //Guest图片使用cfs的前缀命名
            fileName=f.getName();
            Img.transferTo(f);
            f.deleteOnExit(); 
        } catch (IOException e) {
        	
            e.printStackTrace();
            return null;
        }
		
		try {
			if(HttpRequest.postFile(f)==null) {
				return null;
			}
		} catch (IOException | HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return fileName;
		
    }
}
