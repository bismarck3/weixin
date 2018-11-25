package com.wangjing.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.wangjing.menu.Button;
import com.wangjing.menu.Menu;
import com.wangjing.menu.ViewButton;
import com.wangjing.po.Image;
import com.wangjing.po.ImageMessage;
import com.wangjing.po.Music;
import com.wangjing.po.MusicMessage;
import com.wangjing.po.News;
import com.wangjing.po.NewsMessage;
import com.wangjing.po.TextMessage;

public class MessageUtils {

	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_SCAN = "SCAN";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLIKE = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_SCANCODE = "scancode_push";

	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		Map<String, String> map = new HashMap<>();
		SAXReader reader = new SAXReader();

		InputStream input = request.getInputStream();
		Document doc = reader.read(input);
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		for (Element element : list) {
			map.put(element.getName(), element.getText());
		}
		input.close();
		return map;
	}

	public static String textMessageToXml(TextMessage message) {
		XStream xStream = new XStream();
		xStream.alias("xml", message.getClass());
		return xStream.toXML(message);
	}

	public static String newsMessageToXml(NewsMessage newsMessage) {
		XStream xStream = new XStream();
		xStream.alias("xml", newsMessage.getClass());
		xStream.alias("item", News.class);
		return xStream.toXML(newsMessage);
	}

	public static String imageMessageToXml(ImageMessage imageMessage) {
		XStream xStream = new XStream();
		xStream.alias("xml", imageMessage.getClass());
		return xStream.toXML(imageMessage);
	}

	public static String musicMessageToXml(MusicMessage musicMessage) {
		XStream xStream = new XStream();
		xStream.alias("xml", musicMessage.getClass());
		return xStream.toXML(musicMessage);
	}

	public static String initText(String toUserName, String fromUserName, String content) {
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtils.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessageToXml(text);

	}

	/**
	 * 
	 * 图片+链接
	 * 
	 * @param toUsername
	 * @param fromUserName
	 * @return
	 */
	public static String initNewsMessage(String toUsername, String fromUserName) {
		String message = null;
		List<News> newsList = new ArrayList<>();
		NewsMessage newsMessage = new NewsMessage();

		News news = new News();
		news.setTitle("bilibili");
		news.setDescription("哔哩哔哩");
		news.setPicUrl("http://wjtest.tunnel.2bdata.com/WeiXinTest/picture/01.PNG");
		news.setUrl("http://www.bilibili.com");

		newsList.add(news);

		News news2 = new News();
		news2.setTitle("dilidili");
		news2.setDescription("嘀哩嘀哩");
		news2.setPicUrl("http://wjtest.tunnel.2bdata.com/WeiXinTest/picture/02.PNG");
		news2.setUrl("http://www.dilidili.com");

		newsList.add(news2);

		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUsername);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		message = newsMessageToXml(newsMessage);
		return message;
	}

	public static String menuText() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("xx:\n\n");
		stringBuilder.append("1.\n");
		stringBuilder.append("2.\n");
		stringBuilder.append("3.\n");
		stringBuilder.append("4.\n");
		stringBuilder.append("5.\n\n");
		stringBuilder.append("???.");
		return stringBuilder.toString();
	}

	public static String firstMenu() {
		StringBuilder stringBuffer = new StringBuilder();
		stringBuffer.append("1");
		return stringBuffer.toString();
	}

	public static String secondMenu() {
		StringBuilder stringBuffer = new StringBuilder();
		stringBuffer.append("2");
		return stringBuffer.toString();
	}

	public static String initImageMessage(String toUsername, String fromUserName) {
		String message = null;
		Image image = new Image();
		image.setMediaId("IVHHnggJ2marH07Cm_qPR3Zx00m_x5q4HPEXzCS_t8jUvSAOzbDGrlX6RhXp2jgc");
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setFromUserName(toUsername);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setMsgType(MESSAGE_IMAGE);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setImage(image);
		message = imageMessageToXml(imageMessage);
		return message;
	}

	public static String initMusicMessage(String toUsername, String fromUserName) {
		String message = null;
		Music music = new Music();
		music.setThumbMediaId("DVutV8OG0BjvaVDIwsvSzbh47ESu4Z1OKxarcynWGQ2-pf0hKElHpgO_0Q2RJa_F");
		music.setTitle("Test");
		music.setDescription("");
		music.setMusicUrl("http://wjtest.tunnel.2bdata.com/WeiXinTest/music/RADWIMPS.mp3");
		music.setHQMusicUrl("http://wjtest.tunnel.2bdata.com/WeiXinTest/music/RADWIMPS.mp3");

		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setFromUserName(toUsername);
		musicMessage.setToUserName(fromUserName);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMsgType(MESSAGE_MUSIC);
		musicMessage.setMusic(music);

		message = musicMessageToXml(musicMessage);
		return message;
	}

	@SuppressWarnings("deprecation")
	public static Menu initMenu() {
		Menu menu = new Menu();

//		// click1
//		ClickButton clickButton = new ClickButton();
//		clickButton.setName("click1");
//		clickButton.setType("click");
//		clickButton.setKey("1");

		// 爱家商城
		ViewButton ajscViewButton = new ViewButton();
		ajscViewButton.setName("爱家商城");
		ajscViewButton.setType("view");
//		ajscViewButton.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?" + "appid=" + WeiXinUtils.APPID
//				+ "&redirect_uri=" + URLEncoder.encode("http://prinzeugen.free.idcfengye.com/index/home/")
//				+ "&response_type=code&scope=" + "snsapi_base" + "&state=" + "123" + "#wechat_redirect");
		ajscViewButton.setUrl("www.bismarckgasuki.top/index/home/");

		// 个人中心
		ViewButton grzxView = new ViewButton();
		grzxView.setName("个人中心");
		grzxView.setType("view");
//		grzxView.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?" + "appid=" + WeiXinUtils.APPID
//				+ "&redirect_uri=" + URLEncoder.encode("http://prinzeugen.free.idcfengye.com/index/userHome/")
//				+ "&response_type=code&scope=" + "snsapi_base" + "&state=" + "123" + "#wechat_redirect");
		grzxView.setUrl("www.bismarckgasuki.top/index/userHome/");
//		// click2-scancode_push
//		ClickButton clickButton2 = new ClickButton();
//		clickButton2.setName("click2");
//		clickButton2.setType("scancode_push");
//		clickButton2.setKey("2");
//
//		// click3-location_select
//		ClickButton clickButton3 = new ClickButton();
//		clickButton3.setName("click3");
//		clickButton3.setType("location_select");
//		clickButton3.setKey("3");
//
//		Button button = new Button();
//		button.setName("2");
//		button.setSub_button(new Button[] { clickButton2, clickButton3 });

		menu.setButton(new Button[] { ajscViewButton, grzxView });
		return menu;
	}
}
