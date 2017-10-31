package com.lostray.master.driver;

import java.io.IOException;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.lostray.master.driver.HttpDriver;
import com.lostray.master.driver.HttpResult;

public class Test001 {

	@Test
	public void test01() {
		HttpDriver driver = new HttpDriver();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "username");
		jsonObject.put("password", "password");
		driver.setJson(jsonObject.toJSONString());
		driver.setUri("http://app.uat.idanlu.com/login/login.json");
		try {
			HttpResult response = driver.postWithJSON();
			System.out.print(response.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
