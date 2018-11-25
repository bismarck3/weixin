package com.wangjing.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

public class SignUtils {

	 private static String characterEncoding = "UTF-8";
	 
	    @SuppressWarnings("rawtypes")
	    public static String createSign(SortedMap<Object,Object> parameters,String key){  
	        StringBuffer sb = new StringBuffer();  
	        Set es = parameters.entrySet();
	        Iterator it = es.iterator();  
	        while(it.hasNext()) {  
	            Map.Entry entry = (Map.Entry)it.next();  
	            String k = (String)entry.getKey();  
	            Object v = entry.getValue();  
	            if(null != v && !"".equals(v)   
	                    && !"sign".equals(k) && !"key".equals(k)) {  
	                sb.append(k + "=" + v + "&");  
	            }  
	        }  
	        sb.append("key=" + key);
	        String sign = MD5Utils.MD5Encode(sb.toString(), characterEncoding).toUpperCase();  
	        return sign;  
	    }
	     
	    public static String getNonceStr() {
	        Random random = new Random();
	        return MD5Utils.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
	    }
	 
	    public static String getTimeStamp() {
	        return String.valueOf(System.currentTimeMillis() / 1000);
	    }
	
}
