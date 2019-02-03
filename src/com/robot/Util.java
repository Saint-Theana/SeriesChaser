package com.robot;
import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import java.security.cert.*;
import java.util.regex.*;
import java.text.*;
import java.util.*;

public class Util
{
	
	public static void log(String string){
		SimpleDateFormat format0 = new SimpleDateFormat("[HH:mm:ss]");
        String log = format0.format(new Date().getTime());
		System.out.println(log+" "+string);
	}
	
	public static String curl_with_referer(String url, String referer,String cookie,Main main)
	{  
		try
		{  
			URL lll = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) lll.openConnection();// 打开连接  
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Referer", referer);
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Linux; U; Android 7.1.2; zh-Hans-CN; ONEPLUS A5010 Build/NJH47F) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.108 Quark/3.0.0.942 Mobile Safari/537.36");
			connection.setRequestProperty("Cookie",cookie);
			connection.setRequestProperty("X-UCBrowser-UA","dv(HTC 802t);pr(UCBrowser/10.2.0.535);ov(Android 5.0.2);ss(360*640);pi(1080*1920);bt(UC);pm(1);bv(1);nm(0);im(0);sr(0);nt(2);");
			connection.connect();// 连接会话  
			// 获取输入流  
			BufferedReader br= new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));  
			String line;  
			StringBuilder sb = new StringBuilder();  
			while ((line = br.readLine()) != null)
			{// 循环读取流  
				sb.append(line);  
			}
			String cookieVal = connection.getHeaderField("Set-Cookie");
			if(cookieVal!=null){
				String pattern = "skt=(.*?);";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(cookieVal);
				if(m.find()){
					main.write_property("skt",m.group(0));
				}
			}
			br.close();// 关闭流
			connection.disconnect();// 断开连接  
			return sb.toString();
		}
		catch (Exception e)
		{  
			e.printStackTrace();
		}
		return null;
	}

	public static String get_redirected_url(String url){
		String location =null;
		try {  
            URL serverUrl = new URL(url);  
            HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();  
            connection.setRequestMethod("GET");  
            // 必须设置false，否则会自动redirect到Location的地址  
            connection.setInstanceFollowRedirects(false);
            connection.addRequestProperty("Accept-Charset", "UTF-8;");  
            connection.addRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
            connection.connect();  
            location = connection.getHeaderField("Location");
			connection.disconnect();

        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		return location;
	}

	public static String post_with_data(String url, String data)
	{  
		try
		{
			URL lll = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) lll.openConnection();// 打开连接  
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept", "*/*");
			connection.setDoOutput(true);
			connection.setRequestProperty("Referer", "https://music.163.com/m/song?id=16431842");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
			connection.setRequestProperty("Origin","http://music.163.com");
			connection.setRequestProperty("Connection","keep-alive");
			connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7");
			connection.connect();// 连接会话  
			// 获取输入流  
			PrintWriter writer = new PrintWriter(connection.getOutputStream());
			writer.print(data);                                    
			writer.flush();
			BufferedReader br= new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));  
			String line;  
			StringBuilder sb = new StringBuilder();  
			while ((line = br.readLine()) != null)
			{// 循环读取流  
				sb.append(line);  
			}  
			br.close();// 关闭流
			connection.disconnect();// 断开连接  
			return sb.toString();
		}
		catch (Exception e)
		{  
			System.out.println(e.toString());
		}
		return null;
	}

	public static String Curl(String url)
	{
		try
		{

			InputStream is=new URL(url).openStream();
			ByteArrayOutputStream buffer=new ByteArrayOutputStream();
			int b=-1;
			while ((b = is.read()) != -1)
				buffer.write(b);
			return new String(buffer.toByteArray());
		}
		catch (MalformedURLException e)
		{
			System.out.println(e.toString());
		}
		catch (IOException e)
		{
			System.out.println(e.toString());
		}
		return null;
	}
	public static String ridiculous_uin(String qquin)
	{
		String uin = String.valueOf(qquin);
		return uin.replace("0","⓪")
	    	.replace("1","①")
	     	.replace("2","②")
			.replace("3","③")
			.replace("4","④")
			.replace("5","⑤")
			.replace("6","⑥")
			.replace("7","⑦")
			.replace("8","⑧")
			.replace("9","⑨");
	}
}
