package com.robot;
import java.util.*;
import com.Tick_Tock.ANDROIDQQ.sdk.*;
import java.util.regex.*;
import org.json.*;
import java.io.*;

public class Main extends Thread implements Plugin
{
	
	
	
	private API api;
	private String configpath ="";
	private long group_number_set=0;
	private boolean thread_stop=false;

	@Override
	public String Version()
	{
		return null;
	}

	@Override
	public String author()
	{
		return null;
	}

	@Override
	public String name()
	{
		return "美剧追寻者";
	}
	
	
	
	
	@Override
	public void onLoad(API p1)
	{
		this.api = p1;
		this.configpath=this.api.GetPluginConfigPath();
		this.group_number_set = Long.parseLong(this.readconfig("group_set"));
		this.start();
	}

	@Override
	public void onMessageHandler(GroupMessage qqmessage)
	{
	    MessageFactory factory = new MessageFactory();
		factory.Group_uin=qqmessage.group_uin;
		factory.message_type=0;
		if(qqmessage.message.matches("添加美剧\\s+.*")){
			if(qqmessage.message.replaceAll("添加美剧\\s+","").split(" ").length!=2){
				factory.Message="格式错误";
				this.api.SendGroupMessage(factory);
				return;
			}
			if(!this.hasrecord(qqmessage.message.replaceAll("添加美剧\\s+","").split(" ")[1])){
				this.recordseries(qqmessage.message.replaceAll("添加美剧\\s+",""));
				factory.Message="成功记录新美剧";
			}else{
				factory.Message="美剧已存在";
			}
			this.api.SendGroupMessage(factory);
		}else if(qqmessage.message.equals("立即更新")){
			this.update(factory);
		}else if(qqmessage.message.matches("更新cookie\\s+.*")){
			this.write_property("stk",qqmessage.message.replaceAll("更新cookie\\s+",""));
		}else if(qqmessage.message.matches("锁定本群")){
			this.write_property("group_set",String.valueOf(qqmessage.group_uin));
		}else if(qqmessage.message.matches("追剧列表")){
			factory.Message=this.readallseries();
			this.api.SendGroupMessage(factory);
		}
		
		
	}

	
	

	
	
	
	@Override
	public void onMessageHandler(FriendMessage qqmessage)
	{
		
	}

	@Override
	public void onMessageHandler(TempolarMessage qqmessage)
	{
		
	}

	@Override
	public void onMessageHandler(RequestMessage qqmessage)
	{
		
		
	}
	
	@Override
	public void run(){
		while(!this.thread_stop){
			Util.log("[追剧] 开始休眠");
			try
			{
				this.sleep(3600000);

			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			Util.log("[追剧] 开始查询");
			this.update();

		}
		
	}
	
	
	
	
	private void update(MessageFactory... fact){
		MessageFactory factory =new MessageFactory();
		factory.Group_uin=this.group_number_set;
		factory.message_type=0;
		if(fact.length!=0){
			factory=fact[0];
		}
		String data = this.readconfig("SeriesData");
		try{
			if(!(data==null)&&!(data.isEmpty())){
				JSONArray json_root = new JSONArray(data);
				for(int i=0;i<json_root.length();i++){
					JSONObject series_json = json_root.getJSONObject(i);
					String seriesid = series_json.getString("seriesid");
					String seriesname = series_json.getString("seriesname");
					int whatchedepis = series_json.getInt("whatchedepis");
					String remote_query = this.query(seriesid);
					if (remote_query.equals("error")){
						
					}else if (remote_query.equals("cookie expired")){
						factory.Message="Cookie已过期";
						this.api.SendGroupMessage(factory);
						this.thread_stop=true;
						return;
					}
					JSONObject remote_series_json = new JSONObject(remote_query);
					if (Integer.parseInt(remote_series_json.getString("episnum").split(" ")[1])>whatchedepis){
						this.update_status(seriesid,Integer.parseInt(remote_series_json.getString("episnum").split(" ")[1]));
						factory.Message="您追的剧: "+seriesname+" 更新到了: "+remote_series_json.getString("episnum")+" 标题: "+remote_series_json.getString("episname")+" 更新日期: "+remote_series_json.getString("episdate");
						this.api.SendGroupMessage(factory);
						this.send_magnet(factory,String.valueOf(remote_series_json.getLong("episid")));
					}
					try
					{
						this.sleep(4000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				if(fact.length!=0){
					factory.Message="更新完毕";
					this.api.SendGroupMessage(factory);
				}
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}
	
	private void send_magnet(MessageFactory factory,String episid){
		String result = Util.curl_with_referer("https://rarbgprx.org/tv.php?ajax=1&tvepisode="+episid,"rarbg.is","skt=ksrcv0q6xw",this);
		String pattern = "(return nd\\(\\);\" href=\"/torrent/[a-z0-9]*\" title=\".*?\">.*?</a>     <a href=\"/torrents\\.php\\?imdb=[a-z0-9A-Z]*\"><img src=\"https://dyncdn\\.me/static/20/images/imdb_thumb\\.gif\" border=\"0\" alt=\"\"></a> <a href=\"/tv/[a-z0-9A-Z]*/\"><img src=\"https://dyncdn\\.me/static/20/img/tv2\\.png\" border=\"0\" alt=\"\"></a>    <br><span style=\"color:DarkSlateGray\">.*?</span>  </td>\\t<td align=\"center\"  width=\"150px\" class=\"lista\">[0-9\\. -:]*</td>\\t<td align=\"center\"  width=\"100px\" class=\"lista\">[0-9\\.]* [MG]B</td>)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(result);
		
		while(m.find()){
			String Message = "";
			String info = m.group(0);
			r = Pattern.compile("return nd\\(\\);\" href=\"/torrent/([a-z0-9]*)\" title=\"(.*?)\">.*?</a>     <a href=\"/torrents\\.php\\?imdb=[a-z0-9A-Z]*\"><img src=\"https://dyncdn\\.me/static/20/images/imdb_thumb\\.gif\" border=\"0\" alt=\"\"></a> <a href=\"/tv/[a-z0-9A-Z]*/\"><img src=\"https://dyncdn\\.me/static/20/img/tv2\\.png\" border=\"0\" alt=\"\"></a>    <br><span style=\"color:DarkSlateGray\">.*?</span>  </td>\\t<td align=\"center\"  width=\"150px\" class=\"lista\">([0-9\\. -:]*)</td>\\t<td align=\"center\"  width=\"100px\" class=\"lista\">([0-9\\.]* [MG]B)</td>");
			Matcher n = r.matcher(info);
		    n.find();
			String pageid = n.group(1);
			String title = n.group(2);
			String date = n.group(3);
			String size = n.group(4);
			String magnet = this.get_magnet(pageid);
			Message+="文件: "+title+ " "+ date+" "+ size +" " + magnet+"       ";
			factory.Message=Message;
			this.api.SendGroupMessage(factory);
			try
			{
				this.sleep(1500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	private void update_status(String seriesid, int newestepis)
	{
	    String data = this.readconfig("SeriesData");
		try
		{
			JSONArray json_root = new JSONArray();
			if(!(data==null)&&!(data.isEmpty())){
				json_root = new JSONArray(data);
			}
		    for(int i=0;i<json_root.length();i++){
				JSONObject series_json = json_root.getJSONObject(i);
				if (series_json.getString("seriesid").equals(seriesid)){
				    json_root.remove(i);
					series_json.put("whatchedepis",newestepis);
					json_root.put(series_json);
					this.write_property("SeriesData",json_root.toString());
					return;
				}
				
			}
			
			
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}
	
	private String get_magnet(String torrentpageid){
		
		String result = Util.curl_with_referer("https://rarbgprx.org/torrent/"+torrentpageid,"rarbg.is","skt=ksrcv0q6xw",this);
		String pattern = "(magnet:\\?.*?)&dn=";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(result);
		if(m.find()){
			return m.group(1);
		}
		return "error";
	}
	
	private String readallseries()
	{
		String data = this.readconfig("SeriesData");
		if(!(data==null)&&!(data.isEmpty())){
			try
			{
				String Message ="追剧列表:\n";
				JSONArray json_root = new JSONArray(data);
				for(int i =0;i<json_root.length();i++){
					Message+=json_root.getJSONObject(i).getString("seriesname")+"\n";
				}
				return Message;
			}
			catch (JSONException e)
			{
				return "错误";
			}



		}
		return "错误";
	}
	
	
	private String query(String id){
		
		String result = Util.curl_with_referer("https://rarbgprx.org/tv/"+id+"/","rarbg.is","skt="+this.readconfig("stk"),this);/*tt0898266*/
		String pattern = "(<div id=\"episode_[0-9]*\"><div class=\"tvdivhidden\"><a onclick=\"populate_tv\\([0-9]*\\);\" class=\"tvshowClick\"><div class=\"tvshowEpNum\">Episode [0-9]*</div> .*? <span class=\"tvshowRelDate\">[0-9-]*</span></a></div>)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(result);
		if (m.find()) {
		    String info = m.group(0);
			r = Pattern.compile("<div id=\"episode_([0-9]*)\"><div class=\"tvdivhidden\"><a onclick=\"populate_tv\\([0-9]*\\);\" class=\"tvshowClick\"><div class=\"tvshowEpNum\">(Episode [0-9]*)</div> (.*?) <span class=\"tvshowRelDate\">([0-9-]*)</span></a></div>");
			m = r.matcher(info);
		    m.find();
			long episid = Long.parseLong(m.group(1));
			String episnum = m.group(2);
			String episname = m.group(3);
			String episdate = m.group(4);
			JSONObject new_json = new JSONObject();
			try
			{
				new_json.put("episid",episid);
				new_json.put("episnum",episnum);
				new_json.put("episname",episname);
				new_json.put("episdate",episdate);
				return new_json.toString();
			}
			catch (JSONException e)
			{
				return "erro";
			}
  		}else if (Pattern.compile("Please don't change tabs / minimize your browser or the process will fail").matcher(result).find()){
		    return "cookie expired";
		}
		return "error";
	}
	
	private void recordseries(String text_in)
	{
		String series_name = text_in.split(" ")[0];
		String series_id = text_in.split(" ")[1];
		String data = this.readconfig("SeriesData");
		try
		{
			JSONArray json_root = new JSONArray();
			if(!(data==null)&&!(data.isEmpty())){
				json_root = new JSONArray(data);
			}
			JSONObject record_json = new JSONObject();
			record_json.put("seriesname",series_name);
			record_json.put("seriesid",series_id);
			record_json.put("whatchedepis",1);
			json_root.put(record_json);
			this.write_property("SeriesData",json_root.toString());
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}
	
	private boolean hasrecord(String id)
	{
		String data = this.readconfig("SeriesData");
		if(!(data==null)&&!(data.isEmpty())){
			try
			{
				JSONArray json_root = new JSONArray(data);
				for(int i =0;i<json_root.length();i++){
					if(id.equals(json_root.getJSONObject(i).getString("seriesid"))) return true;
				}
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}



		}
		return false;
	}
	
	
	private String readconfig(String key){
		File property_file = new File(this.configpath+"Series.conf");
		Properties properties = new Properties();
		// 使用InPutStream流读取properties文件
		try
		{
			if (!property_file.exists()){
				property_file.createNewFile();
				return "error";
			}

			BufferedReader bufferedReader = new BufferedReader(new FileReader(property_file));

			properties.load(bufferedReader);
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		// 获取key对应的value值
		return properties.getProperty(key);

	}
	
	
	public void write_property(String key,String value){
		File property_file = new File(this.configpath+"Series.conf");
		Properties properties = new Properties();
		// 使用InPutStream流读取properties文件
		try
		{
			if (!property_file.exists()){
				property_file.createNewFile();
			}
			BufferedReader bufferedReader = new BufferedReader(new FileReader(property_file));

			properties.load(bufferedReader);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(property_file)));

			properties.setProperty(key,value);
			properties.store(bw,value);

		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}

	}
}
