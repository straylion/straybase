package com.lostary.master.driver;

import java.io.IOException;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

public class TestAG {
	
	final String ip1 = "172.16.3.2";
	final String ip2 = "172.16.3.4";
	final String ip3 = "172.16.3.5";
	final String ip4 = "172.16.3.3";
	final String ip5 = "172.16.51.6";

	/**
	 * 
	 * 验证 灰度 流量比例
	 */
	@Test
	void testGrayRule() {		
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
		for (int i = 0; i < 1000; i++) {
			HttpDriver driver = new HttpDriver(true);
			driver.setUri("http://10.2.1.46/status");
			driver.setHeader("Host", "test.lyf.com");
			try {
				HttpResult response = driver.post();
				JSONObject b = JSONObject.parseObject(response.getsBody());
				String ip = b.getString("localHostAddress");
				switch (ip) {
				case ip1:
					count1++;
					break;
				case ip2:
					count2++;
					break;
				case ip3:
					count3++;
					break;
				case ip4:
					count4++;
					break;
				case ip5:
					count5++;
					break;
				default:
					System.out.println("unexpected ip: " + ip);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(count1 + ":" + count2 + ":" + count3 + ":" + count4 + ":" + count5);
	}
}
