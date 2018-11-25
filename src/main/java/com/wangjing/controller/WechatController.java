package com.wangjing.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangjing.consts.WechatConsts;
import com.wangjing.po.TextMessage;
import com.wangjing.supplier.TextMessageSupplier;
import com.wangjing.utils.CheckUtils;
import com.wangjing.utils.MessageUtils;

@Controller
@RequestMapping("/wechat")
public class WechatController {

	private final static Logger logger = LoggerFactory.getLogger(WechatController.class);

	/**
	 * @param out
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("/index")
	public void checkSignature(HttpServletRequest request, HttpServletResponse response) {

		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		try (PrintWriter out = response.getWriter();) {
			if (CheckUtils.checkSignature(signature, timestamp, nonce)) {
				out.print(echostr);
			} else {
				out.print("");
			}
		} catch (IOException e) {
			logger.error("获取PrintWriter出错", e, e.getMessage());
		}

	}

	@PostMapping("/index")
	public void getWxContent(HttpServletRequest request, HttpServletResponse response) {
		try (PrintWriter out = response.getWriter();) {
			Map<String, String> map = MessageUtils.xmlToMap(request);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			String message = null;
			if (WechatConsts.MESSAGE_TYPE_TEXT.equals(msgType)) {
				TextMessage textMessage = TextMessageSupplier.getTextMessage(fromUserName, toUserName, content).get();
				message = MessageUtils.textMessageToXml(textMessage);
//				message = MessageUtils.initNewsMessage(toUserName, fromUserName);
			}

			out.print(message);
		} catch (IOException | DocumentException e) {
			logger.error("获取PrintWriter出错", e, e.getMessage());
		}
	}

}
