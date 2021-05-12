//
// Decompiled by Jadx - 1028ms
//
package com.robot;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.ticktock.androidqq.sdk.Api;
import com.ticktock.androidqq.sdk.Plugin;
import com.ticktock.androidqq.sdk.contactInfo.QQFriendGroup;
import com.ticktock.androidqq.sdk.contactInfo.QQGroup;
import com.ticktock.androidqq.sdk.contactInfo.QQGroupMember;
import com.ticktock.androidqq.sdk.event.Event;
import com.ticktock.androidqq.sdk.message.GroupMessage;
import com.ticktock.androidqq.sdk.message.QQMessage;
import com.ticktock.androidqq.sdk.messagebuilder.MessageBuilder;
import com.ticktock.androidqq.sdk.target.GroupTarget;
import com.ticktock.androidqq.sdk.target.MessageTarget;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main extends Plugin
{

	@Override
	public void onLoad(HashMap<Long, Api> p1)
	{
		this.api=p1.get(robotset);
		if(this.api==null){
			this.api = new Api(){
				@Override
				public void printStackTrace(Exception p1)
				{
				}

				@Override
				public void sendMessage(MessageBuilder p1, MessageTarget p2)
				{
				}

				@Override
				public long getUserUin()
				{
					return 0;
				}

				@Override
				public void sendTextMessage(String p1, MessageTarget p2)
				{
				}

				@Override
				public void sendLongTextMessage(String p1, MessageTarget p2)
				{
				}

				@Override
				public void dealWithGroupRequest(long p1, int p2, boolean p3, long p4, long p5)
				{
				}

				@Override
				public void dealWithFriendRequest(long p1, int p2, boolean p3, long p4)
				{
				}

				@Override
				public void groupMemberShutUp(long p1, long p2, long p3)
				{
				}

				@Override
				public void groupMemberDelete(long p1, long p2)
				{
				}

				@Override
				public void groupShutUp(boolean p1, GroupTarget p2)
				{
				}

				@Override
				public void setGroupMemberCard(String p1, long p2, long p3)
				{
				}

				@Override
				public void groupMemberMessageRecall(long p1, long p2, GroupTarget p3)
				{
				}

				@Override
				public void addFriend(long p1, String p2, String p3)
				{
				}

				@Override
				public void inviteFriendJoinGroup(long p1, long p2)
				{
				}

				@Override
				public void digMemberFromOtherGroup(long p1, long p2, long p3)
				{
				}

				@Override
				public void joinGroup(long p1, String p2)
				{
				}

				@Override
				public void pressFavorite(long p1)
				{
				}

				@Override
				public void uploadGroupFile(long p1, String p2) 
				{
				}

				@Override
				public void uploadFriendFile(long p1, String p2)
				{
				}

				@Override
				public void downloadFriendFile(String p1)
				{
				}

				@Override
				public void downloadGroupFile(long p1, String p2)
				{
				}

				@Override
				public long getInitiateTime()
				{
					return 0;
				}

				@Override
				public long getLastLoginTime()
				{
					return 0;
				}

				@Override
				public void updateGroupList()
				{
				}

				@Override
				public String getPluginConfigPath()
				{
					return null;
				}

				@Override
				public List<Long> getGroupAdminList(long p1)
				{
					return null;
				}

				@Override
				public void updateGroupAdminList(long p1)
				{
				}

				@Override
				public void updateFriendList()
				{
				}

				@Override
				public void updateGroupMemberList(long p1)
				{
				}

				@Override
				public HashMap<Long, QQGroup> getGroupList()
				{
					return null;
				}

				@Override
				public List<QQFriendGroup> getFriendGroupList()
				{
					return null;
				}

				@Override
				public long getGroupUinByGroupCode(long p1)
				{
					return 0;
				}

				@Override
				public long getGroupCodeByGroupUin(long p1)
				{
					return 0;
				}

				@Override
				public List<QQGroupMember> getGroupMemberList(long p1)
				{
					return null;
				}

				@Override
				public void setAllPluginEnabled()
				{
				}

				@Override
				public void setAllPluginDisabled()
				{
				}

				@Override
				public void setAllGroupEnabled()
				{
				}

				@Override
				public void setGroupEnabled(long p1)
				{
				}

				@Override
				public void setAllGroupDisabled()
				{
				}

				@Override
				public void setGroupDisabled(long p1)
				{
				}

				@Override
				public void log(int p1, String p2)
				{
				}
			
			};
		}
		this.http = new HTTP();
        while (this.token == null)
		{
            try
			{
                refreshtoken();
            }
			catch (Exception e)
			{
                api.printStackTrace(e);
            }
        }
		new Thread(){
			@Override public void run()
			{
				while (true)
				{
					top10movie();
					try
					{
						Thread.currentThread();
						Thread.sleep(1000 * 60 * 60 * 24);
					}
					catch (InterruptedException e2)
					{
						api.printStackTrace(e2);
					}
				}
			}
		}.start();
		new Thread(){
			@Override public void run()
			{
				while (true)
				{
					startProcess();
					try
					{
						Thread.currentThread();
						Thread.sleep(3600000);
					}
					catch (InterruptedException e2)
					{
						api.printStackTrace(e2);
					}
				}

			}
		}.start();
	}

    private long groupset = 1107272833;
	private long robotset = 2569577241l;
	
    private HTTP http;
    private String token;

	private static Api api;

    public Main()
	{
		
    }

    public static void main(String[] strArr) throws Exception
	{
        Main main = new Main();
        main.http = new HTTP();
        try
		{
            main.refreshtoken();
        }
		catch (Exception e)
		{
            api.printStackTrace(e);
        }
        HashMap<String, String> search = main.search("the.flash");
        for (String next : search.keySet())
		{
            System.out.println(next + " " + search.get(next));
        }
    }

    public String version()
	{
        return null;
    }

    public String author()
	{
        return null;
    }

    @Override public String name()
	{
        return "rarbg";
    }




	private void top10movie()
	{
		try
		{
			JSONObject a=new JSONObject(this.http.httpGet("https://torrentapi.org/pubapi_v2.php?mode=list&format=json_extended&category=44&app_id=rarbg&limit=10&token=" + token, true, new String[]{}));
			JSONArray b=a.getJSONArray("torrent_results");
			String msg="今日电影Top10(part1)\n----------\n";
			for(int y=0;y<5;y+=1){
				JSONObject c=b.getJSONObject(y);
				msg+="标题: "+c.getString("title")+"\n";
				msg+="文件大小: "+(c.getLong("size") / 1024) / 1024+"M\n";
				msg+="发布日期: "+c.getString("pubdate")+"\n";
				msg+="做种: "+c.getInt("seeders")+"\n";
				msg+="下载: "+c.getInt("leechers")+"\n";
				msg+="磁力链: "+c.getString("download")+"\n";
				msg+="----------\n";
			}
			api.sendLongTextMessage(msg, new GroupTarget().setGroupUin(this.groupset));
			msg="今日电影Top10(part2)\n----------\n";
			for(int y=5;y<10;y+=1){
				JSONObject c=b.getJSONObject(y);
				msg+="标题: "+c.getString("title")+"\n";
				msg+="文件大小: "+(c.getLong("size") / 1024) / 1024+"M\n";
				msg+="发布日期: "+c.getString("pubdate")+"\n";
				msg+="做种: "+c.getInt("seeders")+"\n";
				msg+="下载: "+c.getInt("leechers")+"\n";
				msg+="磁力链: "+c.getString("download")+"\n";
				msg+="----------\n";
			}
			api.sendLongTextMessage(msg, new GroupTarget().setGroupUin(this.groupset));
			
		}
		catch (JSONException e2)
		{
			try
			{
				Thread.currentThread().sleep(800);
			}
			catch (InterruptedException e3)
			{
				api.printStackTrace(e3);
			}
			api.printStackTrace(e2);
			top10movie();
		}
		catch (TokenExpiredException e4)
		{
			refreshtoken();
			api.printStackTrace(e4);
			top10movie();
		}
	}

    private void refreshtoken()
	{
        try
		{
            this.token = new JSONObject(this.http.httpGet("https://torrentapi.org/pubapi_v2.php?get_token=get_token&app_id=github.com/banteg/rarbg", true, new String[0])).getString("token");
        }
		catch (TokenExpiredException e)
		{
            api.printStackTrace(e);
        }
		catch (JSONException e2)
		{
            api.printStackTrace(e2);
        }
    }

    public void onMessageHandler(Event event,Api api)
	{
    }

    public void onMessageHandler(QQMessage qQMessage,Api api)
	{
        if (qQMessage instanceof GroupMessage)
		{
            onMessageHandler((GroupMessage) qQMessage,api);
        }
    }

    public void onMessageHandler(GroupMessage groupMessage,Api api)
	{
        if (groupMessage.toString().matches("搜索美剧\\s+.*"))
		{
            try
			{
                HashMap<String, String> search = search(groupMessage.toString().replaceAll("搜索美剧\\s+", ""));
                MessageBuilder messageBuilder = new MessageBuilder();
                messageBuilder.addText("名称        id\n");
                for (String next : search.keySet())
				{
                    messageBuilder.addText(next + "    " + search.get(next) + "\n");
                }
                messageBuilder.addText("发送\"添加追剧 id\"来添加一个追剧");
                api.sendMessage(messageBuilder, groupMessage.getTarget());
            }
			catch (IOException e)
			{
                api.sendLongTextMessage("网络异常请重试", groupMessage.getTarget());
                api.printStackTrace(e);
            }
			catch (JSONException e2)
			{
                api.sendLongTextMessage("未搜索到数据请重试", groupMessage.getTarget());
                api.printStackTrace(e2);
            }
			catch (TokenExpiredException e3)
			{
                refreshtoken();
                api.sendLongTextMessage("token过期，现已重新获取，请重试", groupMessage.getTarget());
                api.printStackTrace(e3);
                return;
            }
        }
		else if (groupMessage.toString().matches("精确搜索美剧\\s+.*"))
		{
            try
			{
                api.sendLongTextMessage(searchAccurate(groupMessage.toString().replaceAll("精确搜索美剧\\s+", "")), groupMessage.getTarget());
            }
			catch (IOException e4)
			{
                api.sendLongTextMessage("网络异常请重试", groupMessage.getTarget());
                api.printStackTrace(e4);
            }
			catch (JSONException e5)
			{
                api.sendLongTextMessage("未搜索到数据请重试", groupMessage.getTarget());
                api.printStackTrace(e5);
            }
			catch (TokenExpiredException e6)
			{
                refreshtoken();
                api.sendLongTextMessage("token过期，现已重新获取，请重试", groupMessage.getTarget());
                api.printStackTrace(e6);
                return;
            }
        }
		else if (groupMessage.toString().matches("添加追剧\\s+.*"))
		{
            String replaceAll = groupMessage.toString().replaceAll("添加追剧\\s+", "");
            try
			{
                if (addTvIdToRecord(replaceAll, getTvNameById(replaceAll)))
				{
                    api.sendLongTextMessage("成功添加至追剧列表", groupMessage.getTarget());
                }
				else
				{
                    api.sendLongTextMessage("添加失败，重复添加", groupMessage.getTarget());
                }
            }
			catch (JSONException e7)
			{
                api.sendLongTextMessage("获取美剧信息失败，请检查id或者重试", groupMessage.getTarget());
                api.printStackTrace(e7);
                return;
            }
			catch (IOException e8)
			{
                api.sendLongTextMessage("网络错误请重试", groupMessage.getTarget());
                api.printStackTrace(e8);
                return;
            }
			catch (TokenExpiredException e9)
			{
                refreshtoken();
                api.sendLongTextMessage("token过期，现已重新获取，请重试", groupMessage.getTarget());
                api.printStackTrace(e9);
                return;
            }
        }
		else if (groupMessage.toString().equals("追剧列表"))
		{
            String str = "";
            try
			{
                JSONArray jSONArray = new JSONArray(readFromFile(api.getPluginConfigPath() + "rarbg.json"));
                for (int i = 0; i < jSONArray.length(); i++)
				{
                    str +=  "name: " + jSONArray.getJSONObject(i).getString("name") + "\n" + "id: " + jSONArray.getJSONObject(i).getString("id") + "\n" + "自动更新: " + jSONArray.getJSONObject(i).getBoolean("enabled") + "\n";
					str += "------\n";
                }
                if (str.isEmpty())
				{
                    api.sendLongTextMessage("当前追剧列表为空", groupMessage.getTarget());
                }
				else
				{
                    api.sendLongTextMessage(str, groupMessage.getTarget());
                }
            }
			catch (Exception e10)
			{
                api.printStackTrace(e10);
                return;
            }
        }
        if (groupMessage.toString().matches("搜索电影\\s+.*"))
		{
            try
			{
                HashMap<String, String> searchmovie = searchmovie(groupMessage.toString().replaceAll("搜索电影\\s+", ""));
                MessageBuilder messageBuilder2 = new MessageBuilder();
                messageBuilder2.addText("名称        id\n");
                for (String next2 : searchmovie.keySet())
				{
                    messageBuilder2.addText(searchmovie.get(next2) + "    " + next2 + "\n");
                }
                messageBuilder2.addText("发送\"搜索电影id id\"来精确搜索");
                api.sendLongTextMessage(messageBuilder2.toString(), groupMessage.getTarget());
            }
			catch (IOException e11)
			{
                api.sendLongTextMessage("网络异常请重试", groupMessage.getTarget());
                api.printStackTrace(e11);
            }
			catch (JSONException e12)
			{
                api.sendLongTextMessage("未搜索到数据请重试", groupMessage.getTarget());
                api.printStackTrace(e12);
            }
			catch (TokenExpiredException e13)
			{
                refreshtoken();
                api.sendLongTextMessage("token过期，现已重新获取，请重试", groupMessage.getTarget());
                api.printStackTrace(e13);
                return;
            }
        }
        if (groupMessage.toString().matches("搜索电影id\\s+.*"))
		{
            try
			{
                api.sendLongTextMessage(searchmovieid(groupMessage.toString().replaceAll("搜索电影id\\s+", "")), groupMessage.getTarget());
            }
			catch (IOException e14)
			{
                api.sendLongTextMessage("网络异常请重试", groupMessage.getTarget());
                api.printStackTrace(e14);
            }
			catch (JSONException e15)
			{
                api.sendLongTextMessage("未搜索到数据请重试", groupMessage.getTarget());
                api.printStackTrace(e15);
            }
			catch (TokenExpiredException e16)
			{
                refreshtoken();
                api.sendLongTextMessage("token过期，现已重新获取，请重试", groupMessage.getTarget());
                api.printStackTrace(e16);
            }
        }
		else if (groupMessage.toString().equals("立即更新"))
		{
            startProcess();
        }
		else if (groupMessage.toString().matches("开启追剧\\s+[0-9]+"))
		{
			String replaceAll = groupMessage.toString().replaceAll("开启追剧\\s+", "");
			try
			{
                JSONArray jSONArray = new JSONArray(readFromFile(api.getPluginConfigPath() + "rarbg.json"));
                for (int i = 0; i < jSONArray.length(); i++)
				{
                    if (jSONArray.getJSONObject(i).getString("id").equals(replaceAll))
					{
						if (jSONArray.getJSONObject(i).getBoolean("enabled"))
						{
							api.sendLongTextMessage("该剧已开启自动更新", groupMessage.getTarget());
							return;
						}
						jSONArray.getJSONObject(i).put("enabled", true);
						api.sendLongTextMessage("开启成功，自动爬取该剧的数据", groupMessage.getTarget());
						return;
					}
                }

				api.sendLongTextMessage("未找到id为: " + replaceAll + " 的剧", groupMessage.getTarget());

            }
			catch (Exception e10)
			{
                api.printStackTrace(e10);
                return;
            }

		}
		else if (groupMessage.toString().matches("关闭追剧\\s+[0-9]+"))
		{
			String replaceAll = groupMessage.toString().replaceAll("关闭追剧\\s+", "");
			try
			{
                JSONArray jSONArray = new JSONArray(readFromFile(api.getPluginConfigPath() + "rarbg.json"));
                for (int i = 0; i < jSONArray.length(); i++)
				{
                    if (jSONArray.getJSONObject(i).getString("id").equals(replaceAll))
					{
						if (!jSONArray.getJSONObject(i).getBoolean("enabled"))
						{
							api.sendLongTextMessage("该剧已关闭自动更新", groupMessage.getTarget());
							return;
						}
						jSONArray.getJSONObject(i).put("enabled", false);
						writeTxtFile(toPrettyFormat(jSONArray.toString()), new File(api.getPluginConfigPath() + "rarbg.json"));
						api.sendLongTextMessage("关闭成功，不会爬取该剧的数据", groupMessage.getTarget());
						return;
					}
                }

				api.sendLongTextMessage("未找到id为: " + replaceAll + " 的剧", groupMessage.getTarget());

            }
			catch (Exception e10)
			{
                api.printStackTrace(e10);
                return;
            }

		}
    }

    private void startProcess()
	{
        JSONArray record;
        String pluginConfigPath = api.getPluginConfigPath();
        try
		{
            record = new JSONArray(readFromFile(pluginConfigPath + "rarbg.json"));
        }
		catch (JSONException e)
		{
            record = null;
        }
        int i = 0;
        while (i < record.length())
		{
            try
			{
				if (!record.getJSONObject(i).getBoolean("enabled"))
				{
					api.log(0,record.getJSONObject(i).getString("name") + " disabled");
					i += 1;
					continue;
				}
				api.log(0,record.getJSONObject(i).getString("name"));
                JSONArray newData = parseEpisode(new JSONObject(this.http.httpGet("https://torrentapi.org/pubapi_v2.php?mode=search&search_tvdb=" + record.getJSONObject(i).getString("id") + "&token=" + this.token + "&format=json_extended&category=18;41;49&app_id=rarbg&limit=100&ranked=0'", true, new String[0])).getJSONArray("torrent_results"));
				//System.out.println(parseEpisode.toString());
                JSONArray recordedSeasons = record.getJSONObject(i).optJSONArray("seasons");
                if (recordedSeasons == null)//没记录过
				{
                    record.getJSONObject(i).put("seasons", newData);
                    writeTxtFile(toPrettyFormat(record.toString()), new File(pluginConfigPath + "rarbg.json"));
                    sendRecordInitedMessage(newData, record.getJSONObject(i).getString("name"));
                }
				else
				{//已经记录过，应该判断是否到了新的季度
                    JSONArray jSONArray2 = newData.getJSONObject(0).getJSONArray("episodes");
                    int i2 = newData.getJSONObject(0).getInt("seasonindex");
                    JSONArray jSONArray3 = recordedSeasons.getJSONObject(0).getJSONArray("episodes");
					int ii=getNewestSeason(recordedSeasons);
					if (i2 == ii + 1)
					{//说明到了新的季度了
						JSONArray newst = new JSONArray(new LinkedList());
						newst.put(newData.getJSONObject(0));
						for (int i7 = 0; i7 < recordedSeasons.length(); i7++)
						{
							newst.put(recordedSeasons.getJSONObject(i7));
						}
						record.getJSONObject(i).put("seasons", newst);
						writeTxtFile(toPrettyFormat(record.toString()), new File(pluginConfigPath + "rarbg.json"));
						sendRecordNewSeasobInitedMessage(newData, record.getJSONObject(i).getString("name"));
						i += 1;
					}
					else
					{

						JSONArray jSONArray4 = new JSONArray(new LinkedList());
						String str = "";
						for (int i3 = 0; i3 < jSONArray2.length(); i3++)
						{
							int i4 = jSONArray2.getJSONObject(i3).getInt("episodeIndex");
							boolean z = false;
							int i5 = 0;
							while (true)
							{
								if (i5 >= jSONArray3.length())
								{
									break;
								}
								else if (i4 == jSONArray3.getJSONObject(i5).getInt("episodeIndex"))
								{
									z = true;
									break;
								}
								else
								{
									i5++;
								}
							}
							if (!z)
							{
								jSONArray4.put(jSONArray2.getJSONObject(i3));
								String string = jSONArray2.getJSONObject(i3).getString("airDate");
								String string2 = jSONArray2.getJSONObject(i3).getString("episodeTitle");
								String str2 = "----------------\n";
								for (int i6 = 0; i6 < jSONArray2.getJSONObject(i3).getJSONArray("magnet").length(); i6++)
								{
									JSONObject jSONObject = jSONArray2.getJSONObject(i3).getJSONArray("magnet").getJSONObject(i6);
									str2 = str2 + jSONObject.getString("magnet").replaceAll("&tr=.*", "") + "     ----   " + jSONObject.getLong("fileSize") + "M\n-----------------\n";
								}
								if (i4 != 1000000)
								{
									str = (((str + "第" + i2 + "季第" + i4 + "集已更新\n更新日期: ") + string + "\n") + "标题: " + string2 + "\n磁力链:\n") + str2;
								}
								else
								{
									str = (((str + "第" + i2 + "季季度包已更新\n更新日期: ") + string + "\n") + "标题: " + string2 + "\n磁力链:\n") + str2;
								}
							}
						}
						if (!str.isEmpty())
						{
							api.sendLongTextMessage(record.getJSONObject(i).getString("name") + "已更新\n" + str, new GroupTarget().setGroupUin(this.groupset));
						}
						for (int i7 = 0; i7 < jSONArray3.length(); i7++)
						{
							jSONArray4.put(jSONArray3.getJSONObject(i7));
						}
						recordedSeasons.getJSONObject(0).put("episodes", jSONArray4);
						writeTxtFile(toPrettyFormat(record.toString()), new File(pluginConfigPath + "rarbg.json"));
						i++;
					}
				}
            }
			catch (JSONException e2)
			{
                try
				{
                    Thread.currentThread().sleep(800);
                }
				catch (InterruptedException e3)
				{
                    api.printStackTrace(e3);
                }
                api.printStackTrace(e2);
            }
			catch (TokenExpiredException e4)
			{
                refreshtoken();
                api.printStackTrace(e4);
            }
        }
    }

	private int getNewestSeason(JSONArray recordedSeasons) throws JSONException
	{
		int current=0;
		for (int index=0;index < recordedSeasons.length();index += 1)
		{
			int a=recordedSeasons.getJSONObject(index).getInt("seasonindex");
			if (a > current)
			{
				current = a;
			}
		}
		return current;
	}

	private void sendRecordNewSeasobInitedMessage(JSONArray jSONArray, String str) throws JSONException
	{
		int i = 0;
        String msg="";
        msg += ("美剧: " + str + " 新季度已初始化\n");
        JSONObject jSONObject = jSONArray.getJSONObject(0);
        msg += ("当前更新至: 第" + jSONObject.getInt("seasonindex") + "季");
        while (true)
		{
            int i2 = jSONObject.getJSONArray("episodes").getJSONObject(i).getInt("episodeIndex");
            if (i2 != 1000000)
			{
				String str2 = "----------------\n";
				for (int i6 = 0; i6 < jSONObject.getJSONArray("episodes").getJSONObject(i).getJSONArray("magnet").length(); i6++)
				{
					JSONObject jo = jSONObject.getJSONArray("episodes").getJSONObject(i).getJSONArray("magnet").getJSONObject(i6);
					str2 = str2 + jo.getString("magnet").replaceAll("&tr=.*", "") + "     ----   " + jo.getLong("fileSize") + "M\n-----------------\n";
				}
                msg += ("第" + i2 + "集\n更新日期: " + jSONObject.getJSONArray("episodes").getJSONObject(i).getString("airDate") + "\n" + "标题: " + jSONObject.getJSONArray("episodes").getJSONObject(i).getString("episodeTitle") + "\n磁力链:\n" + str2);
				api.sendLongTextMessage(msg, new GroupTarget().setGroupUin(this.groupset));
                return;
            }
            i++;
        }
	}

    private void sendRecordInitedMessage(JSONArray jSONArray, String str) throws JSONException
	{
        int i = 0;
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.addText("美剧: " + str + " 已初始化\n");
        JSONObject jSONObject = jSONArray.getJSONObject(0);
        messageBuilder.addText("当前更新至: 第" + jSONObject.getInt("seasonindex") + "季");
        while (true)
		{
            int i2 = jSONObject.getJSONArray("episodes").getJSONObject(i).getInt("episodeIndex");
            if (i2 != 1000000)
			{
                messageBuilder.addText("第" + i2 + "集");
                api.sendMessage(messageBuilder, new GroupTarget().setGroupUin(this.groupset));
                return;
            }
            i++;
        }
    }

    private JSONArray parseEpisode(JSONArray jSONArray) throws JSONException
	{
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true)
		{
            int i2 = i;
            if (i2 >= jSONArray.length())
			{
                break;
            }
            try
			{
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                JSONObject jSONObject2 = jSONObject.getJSONObject("episode_info");
                api.log(0,jSONObject2.toString());
                EpisodeInfo episodeInfo = new EpisodeInfo();
                episodeInfo.episodeTitle = jSONObject2.getString("title");
                episodeInfo.download = jSONObject.getString("download");
                episodeInfo.fileSize = (jSONObject.getLong("size") / 1024) / 1024;
                episodeInfo.episodeIndex = Integer.parseInt(jSONObject2.getString("epnum"));
                episodeInfo.seasonIndex = Integer.parseInt(jSONObject2.getString("seasonnum"));
                episodeInfo.airDate = jSONObject2.getString("airdate");
                SeasonInfo seasonInfo = null;
                Iterator it = arrayList.iterator();
                while (it.hasNext())
				{
                    SeasonInfo seasonInfo2 = (SeasonInfo) it.next();
                    if (seasonInfo2.seasonindex != episodeInfo.seasonIndex)
					{
                        seasonInfo2 = seasonInfo;
                    }
                    seasonInfo = seasonInfo2;
                }
                if (seasonInfo == null)
				{
                    seasonInfo = new SeasonInfo();
                    seasonInfo.seasonindex = episodeInfo.seasonIndex;
                    arrayList.add(seasonInfo);
                }
                seasonInfo.episodes.add(episodeInfo);
            }
			catch (NumberFormatException | JSONException e)
			{
            }
            i = i2 + 1;
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext())
		{
            Collections.sort(((SeasonInfo) it2.next()).episodes);
        }
        Collections.sort(arrayList);
        JSONArray jSONArray2 = new JSONArray(new LinkedList());
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext())
		{
            SeasonInfo seasonInfo3 = (SeasonInfo) it3.next();
            JSONObject jSONObject3 = new JSONObject(new LinkedHashMap());
            jSONObject3.put("seasonindex", seasonInfo3.seasonindex);
            LinkedHashMap<Integer,JSONObject> linkedHashMap = new LinkedHashMap<Integer,JSONObject>();
            Iterator it4 = seasonInfo3.episodes.iterator();
            while (it4.hasNext())
			{
                EpisodeInfo episodeInfo2 = (EpisodeInfo) it4.next();
                JSONObject jSONObject4 = (JSONObject) linkedHashMap.get(Integer.valueOf(episodeInfo2.episodeIndex));
                if (jSONObject4 == null)
				{
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("episodeTitle", episodeInfo2.episodeTitle);
                    jSONObject5.put("episodeIndex", episodeInfo2.episodeIndex);
                    jSONObject5.put("airDate", episodeInfo2.airDate);
                    JSONArray jSONArray3 = new JSONArray();
                    JSONObject jSONObject6 = new JSONObject();
                    jSONObject6.put("fileSize", episodeInfo2.fileSize);
                    jSONObject6.put("magnet", episodeInfo2.download);
                    jSONArray3.put(jSONObject6);
                    jSONObject5.put("magnet", jSONArray3);
                    linkedHashMap.put(Integer.valueOf(episodeInfo2.episodeIndex), jSONObject5);
                }
				else
				{
                    JSONArray jSONArray4 = jSONObject4.getJSONArray("magnet");
                    JSONObject jSONObject7 = new JSONObject();
                    jSONObject7.put("fileSize", episodeInfo2.fileSize);
                    jSONObject7.put("magnet", episodeInfo2.download);
                    jSONArray4.put(jSONObject7);
                }
            }
            JSONArray jSONArray5 = new JSONArray(new LinkedList());
            for (JSONObject put : linkedHashMap.values())
			{
                jSONArray5.put(put);
            }
            jSONObject3.put("episodes", jSONArray5);
            jSONArray2.put(jSONObject3);
        }
        return jSONArray2;
    }

    private boolean addTvIdToRecord(String str, String str2)
	{
        JSONArray jSONArray;
        String pluginConfigPath = api.getPluginConfigPath();
        String readFromFile = readFromFile(pluginConfigPath + "rarbg.json");
        try
		{
            if (readFromFile.isEmpty())
			{
                jSONArray = new JSONArray();
            }
			else
			{
                jSONArray = new JSONArray(readFromFile);
            }
            for (int i = 0; i < jSONArray.length(); i++)
			{
                if (jSONArray.getJSONObject(i).getString("id").equals(str))
				{
                    return false;
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("name", str2);
            jSONObject.put("id", str);
			jSONObject.put("enabled", true);
            jSONArray.put(jSONObject);
            writeTxtFile(toPrettyFormat(jSONArray.toString()), new File(pluginConfigPath + "rarbg.json"));
        }
		catch (JSONException e)
		{
            api.printStackTrace(e);
        }
        return true;
    }

    public static boolean writeTxtFile(String str, File file)
	{
        try
		{
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes("UTF-8"));
            fileOutputStream.close();
            return true;
        }
		catch (Exception e)
		{
            api.printStackTrace(e);
            return false;
        }
    }

    private String getTvNameById(String str) throws JSONException, IOException, TokenExpiredException
	{
        return getTvname(new JSONObject(this.http.httpGet("https://torrentapi.org/pubapi_v2.php?mode=search&search_tvdb=" + str + "&token=" + this.token + "&format=json_extended&category=18;41;49&app_id=rarbg'", true, new String[0])).getJSONArray("torrent_results").getJSONObject(0).getString("title"));
    }

    private HashMap<String, String> search(String str) throws JSONException, IOException, TokenExpiredException
	{
        HashMap<String, String> hashMap = new HashMap<>();
        JSONArray jSONArray = new JSONObject(this.http.httpGet("https://torrentapi.org/pubapi_v2.php?mode=search&search_string=" + str + "&token=" + this.token + "&format=json_extended&category=18;41;49&app_id=rarbg'", true, new String[0])).getJSONArray("torrent_results");
        for (int i = 0; i < jSONArray.length(); i++)
		{
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String string = jSONObject.getString("title");
            hashMap.put(getTvname(string), jSONObject.getJSONObject("episode_info").getInt("tvdb") + "");
        }
        return hashMap;
    }

    private String searchAccurate(String str) throws JSONException, IOException, TokenExpiredException
	{
        String str2 = "";
        JSONArray jSONArray = new JSONObject(this.http.httpGet("https://torrentapi.org/pubapi_v2.php?mode=search&limit=50&search_string=" + str.replaceAll(" ", "+") + "&token=" + this.token + "&format=json_extended&18;41;49&app_id=rarbg'", true, new String[0])).getJSONArray("torrent_results");
        for (int i = 0; i < jSONArray.length(); i++)
		{
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            str2 = str2 + jSONObject.getString("title") + " " + jSONObject.getString("download").replaceAll("&dn=.*", "") + " " + jSONObject.getInt("seeders") + " " + jSONObject.getInt("leechers") + " " + ((int) ((jSONObject.getLong("size") / 1024) / 1024)) + " " + jSONObject.getString("pubdate").replace(" +0000", "") + "\n\n";
        }
        if (str2.isEmpty())
		{
            return "未搜索到数据";
        }
        return "名字 磁力链 做种 下载 大小(M) 公布日期\n" + str2;
    }

    private HashMap<String, String> searchmovie(String str) throws JSONException, IOException, TokenExpiredException
	{
        HashMap<String, String> hashMap = new HashMap<>();
        JSONArray jSONArray = new JSONObject(this.http.httpGet("https://torrentapi.org/pubapi_v2.php?mode=search&limit=50&search_string=" + str + "&token=" + this.token + "&format=json_extended&category=14;48;17;44;45;47;50;51;52;46;42;23;25&app_id=rarbg'", true, new String[0])).getJSONArray("torrent_results");
        for (int i = 0; i < jSONArray.length(); i++)
		{
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            hashMap.put(jSONObject.getJSONObject("episode_info").getInt("themoviedb") + "", jSONObject.getString("title"));
        }
        return hashMap;
    }

    private String searchmovieid(String str) throws JSONException, IOException, TokenExpiredException
	{
        String str2 = "";
        JSONArray jSONArray = new JSONObject(this.http.httpGet("https://torrentapi.org/pubapi_v2.php?mode=search&limit=50&search_themoviedb=" + str + "&token=" + this.token + "&format=json_extended&category=48;44;45;47;50;51;52;46;42;23;25&app_id=rarbg'", true, new String[0])).getJSONArray("torrent_results");
        for (int i = 0; i < jSONArray.length(); i++)
		{
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            str2 = str2 + jSONObject.getString("title") + " " + jSONObject.getString("download").replaceAll("&dn=.*", "") + " " + jSONObject.getInt("seeders") + " " + jSONObject.getInt("leechers") + " " + ((int) ((jSONObject.getLong("size") / 1024) / 1024)) + " " + jSONObject.getString("pubdate").replace(" +0000", "") + "\n\n";
        }
        if (str2.isEmpty())
		{
            return "未搜索到数据";
        }
        return "名字 磁力链 做种 下载 大小(M) 公布日期\n" + str2;
    }

    private String getTvname(String str)
	{
        return str.replaceAll("\\.S[0-9]+.*", "");
    }

    private static String toPrettyFormat(String str)
	{
        if (str.startsWith("["))
		{
            return toPrettyFormatarray(str);
        }
        return new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(str).getAsJsonObject());
    }

    private static String toPrettyFormatarray(String str)
	{
        return new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(str).getAsJsonArray());
    }

    private String readFromFile(String str)
	{
        return new String(bytesFromFile(str));
    }

    private static byte[] toByteArray(InputStream inputStream) throws IOException
	{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true)
		{
            int read = inputStream.read(bArr);
            if (read == -1)
			{
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static byte[] bytesFromFile(String str)
	{
        try
		{
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] byteArray = toByteArray(fileInputStream);
            fileInputStream.close();
            return byteArray;
        }
		catch (Exception e)
		{
            api.printStackTrace(e);
            return new byte[0];
        }
    }
}

