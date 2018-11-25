package com.wangjing.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.wangjing.po.AccessToken;
import com.wangjing.po.qr.PerQRScene;
import com.wangjing.po.qr.PerQRTicketApply;
import com.wangjing.po.qr.SceneId;
import com.wangjing.po.qr.SceneStr;
import com.wangjing.po.qr.TempQRScene;
import com.wangjing.po.qr.TempQRTicketApply;

import net.sf.json.JSONObject;

public class QRCodeUtils {

	public static void download(String urlString, String filename, String savePath) throws Exception {
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		con.setConnectTimeout(5 * 1000);
		InputStream is = con.getInputStream();

		byte[] bs = new byte[1024];
		int len;
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		os.close();
		is.close();
	}

	public static PerQRTicketApply makePerQRTicketApply(String sceneStr) {

		SceneStr scene_str = new SceneStr();
		scene_str.setScene_str(sceneStr);
		PerQRScene perQRScene = new PerQRScene();
		perQRScene.setScene(scene_str);
		PerQRTicketApply perQRTicketApply = new PerQRTicketApply();
		perQRTicketApply.setAction_info(perQRScene);
		return perQRTicketApply;
	}

	public static TempQRTicketApply makeTempQRTicketApply(int sceneId) {

		SceneId scene_id = new SceneId();
		scene_id.setScene_id(sceneId);
		TempQRScene tempQRScene = new TempQRScene();
		tempQRScene.setScene(scene_id);
		TempQRTicketApply tempQRTicketApply = new TempQRTicketApply();
		tempQRTicketApply.setAction_info(tempQRScene);
		tempQRTicketApply.setExpire_seconds(2592000);
		return tempQRTicketApply;

	}

	public static String getPerQRUrl(String sceneStr) {
		AccessToken accessToken;
		accessToken = WeiXinUtils.getAccessToken();

		String ticketUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="
				+ accessToken.getAccessToken();
		PerQRTicketApply perQRTicketApply = makePerQRTicketApply(sceneStr);
		JSONObject jsonObject = WeiXinUtils.doPostStr(ticketUrl, JSONObject.fromObject(perQRTicketApply).toString());
		String ticket = jsonObject.getString("ticket");
		String QRUrl = jsonObject.getString("url");
		return QRUrl;
	}

	public static String getTempRUrl(int sceneId) {
		AccessToken accessToken;
		accessToken = WeiXinUtils.getAccessToken();

		String ticketUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="
				+ accessToken.getAccessToken();
		TempQRTicketApply tempQRTicketApply = makeTempQRTicketApply(sceneId);
		JSONObject jsonObject = WeiXinUtils.doPostStr(ticketUrl, JSONObject.fromObject(tempQRTicketApply).toString());
		String ticket = jsonObject.getString("ticket");
		String QRUrl = jsonObject.getString("url");
		return QRUrl;
	}

	public static void getUrlpicture(int sceneId) {
		AccessToken accessToken;
		accessToken = WeiXinUtils.getAccessToken();

		String ticketUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="
				+ accessToken.getAccessToken();
		TempQRTicketApply tempQRTicketApply = makeTempQRTicketApply(sceneId);
		JSONObject jsonObject = WeiXinUtils.doPostStr(ticketUrl, JSONObject.fromObject(tempQRTicketApply).toString());
		String ticket = jsonObject.getString("ticket");

		@SuppressWarnings("deprecation")
		String pictureUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + URLEncoder.encode(ticket);
		try {
			download(pictureUrl, "QRcode.jpg", "C:\\tx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
