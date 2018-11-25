package com.wangjing;

import org.junit.Test;

import com.wangjing.po.AccessToken;
import com.wangjing.utils.MessageUtils;
import com.wangjing.utils.WeiXinUtils;

import net.sf.json.JSONObject;

public class WxMain {

	@Test
	public void createMenu() {
		try {
			AccessToken token = WeiXinUtils.getAccessToken();

//			String path="C:/temp/IMG_2445.JPG";
//			String mediaId=WeiXinUtils.upload(path,token.getAccessToken(), "thumb");
//			System.out.println(mediaId);

			String menu = JSONObject.fromObject(MessageUtils.initMenu()).toString();
			int result = WeiXinUtils.createMenu(token.getAccessToken(), menu);
			if (result == 0) {
				System.out.println("成功");
			} else {
				System.out.println("失败:" + result);
			}

//			JSONObject jsonObject = WeiXinUtils.queryMenu(token.getAccessToken());
//			System.out.println(jsonObject);
//			QRCodeUtils.getUrlpicture(111);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
