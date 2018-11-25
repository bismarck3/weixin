package com.wangjing.supplier;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.function.Supplier;

import com.wangjing.consts.WechatConsts;
import com.wangjing.po.TextMessage;

public class TextMessageSupplier {

	public static Supplier<TextMessage> getTextMessage(String fromUserName, String toUserName, String text){
		Supplier<TextMessage> supplier = () -> {
			TextMessage textMessage = new TextMessage();
			textMessage.setFromUserName(toUserName);
			textMessage.setToUserName(fromUserName);
			textMessage.setContent(text);
			textMessage.setCreateTime(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
			textMessage.setMsgType(WechatConsts.MESSAGE_TYPE_TEXT);
			return textMessage;
		};
		return supplier;
	}
}
