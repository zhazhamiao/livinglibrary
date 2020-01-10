package httpclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class AIServicec {
	public static String sendPost( String param) {
		String url_param="https://aip.baidubce.com/rpc/2.0/nlp/v1/sentiment_classify?charset=UTF-8&access_token="+AuthService.token;
        String result = "";// 返回的结果  
        BufferedReader in = null;// 读取响应输入流  
//        PrintWriter out = null;  
        System.out.println(param);
        try {  
            // 创建URL对象  
            URL url = new URL(url_param);
            // 打开URL连接  
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) url  
                    .openConnection();  
            // 设置属性  
            httpConn.setRequestProperty("Content-Type",
                    "application/json"); 
            // 设置POST方式  
            httpConn.setDoInput(true);  
            httpConn.setDoOutput(true);  
            httpConn.setRequestMethod("POST");
            httpConn.setUseCaches(false);
            httpConn.setInstanceFollowRedirects(true);
            // 获取HttpURLConnection对象对应的输出流  
            DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());
            out.write(param.getBytes("utf-8"));
            // flush输出流的缓冲  
            out.flush();  
            out.close(); 
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式  
            in = new BufferedReader(new InputStreamReader(httpConn  
                    .getInputStream(), "UTF-8"));  
            String line;  
            // 读取返回的内容  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
              /*  if (out != null) {  
                    out.close();  
                }  */
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }
}
