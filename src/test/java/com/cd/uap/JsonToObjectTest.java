package com.cd.uap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class JsonToObjectTest {
	@Test
	public void testJson() {
		Map<String, Object> map = new HashMap<>();
		List<String> authorities = new ArrayList<>();
		authorities.add("aaa");
		authorities.add("bbb");
		authorities.add("ccc");
		map.put("username", "username");
		map.put("nickname", "nickname");
		map.put("phoneNumber", "phoneNumber");
		map.put("authorities", authorities);
		
		//map转json
//		String json =JSONObject.toJSON(map).toString();
		//json转map
//		HashMap<String,String> parseObject = JSONObject.parseObject(json, HashMap.class);
//		System.out.println(parseObject);
		//----------------这个----------
		String json1 = JSON.toJSONString(map);
		
		Map<String,Object> parse = (Map<String,Object>)JSON.parse(json1);
		System.out.println(parse);
		System.out.println(json1);
//		System.out.println(json);
	}
}
