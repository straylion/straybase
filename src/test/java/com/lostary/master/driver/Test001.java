package com.lostary.master.driver;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

public class Test001 {

	public static void main(String[] args) {
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
