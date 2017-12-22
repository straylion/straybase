package com.lostray.master.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;

public class TestJiulegouAPI {

	String filePath = "userorder.txt";
	String uc = "http://uc.uat.idanlu.com";
	String tc = "http://tc.uat.idanlu.com";
	String umpay = "http://192.168.30.229:9001";

	@BeforeSuite
	public void initTestData() {

	}

	@Test
	public void jiulegou() {
		newUser();
		modifyUser();
		newOrder();
		modifyOrder();
		sendOrder();
		cancelOrder();
	}

	@Test
	public void jiulegou1() {
		newUser();
		modifyUser();
		newOrder();
		modifyOrder();
		sendOrder();
		searchOrder();
		payOrder();
	}

	/**
	 * Request user_name: terminal_location:CHNP035C345D2998 terminal_address:
	 * terminal_name: store_type:S011 business_license_code: mer_id:99999999
	 * 
	 * Response { "req_no": null, "result_desc": "新增丹露用户成功", "mer_id":
	 * "99999999", "charset": null, "user_id":
	 * "b49a46df69b543eba74f6a95f6a42e94", "reserved": null }
	 */
	@Test(enabled = true)
	public void newUser() {
		String url = uc + "/user/add";
		List<NameValuePair> formdata = new ArrayList<NameValuePair>();
		formdata.add(new BasicNameValuePair("user_name", "testjlg" + currenTime()));
		formdata.add(new BasicNameValuePair("terminal_location", "CHNP035C345D2998"));
		formdata.add(new BasicNameValuePair("terminal_address", "NewAdd"));
		formdata.add(new BasicNameValuePair("terminal_name", "NewZdd"));
		formdata.add(new BasicNameValuePair("store_type", "S011"));
		formdata.add(new BasicNameValuePair("business_license_code", "123456"));
		formdata.add(new BasicNameValuePair("mer_id", "99999999"));
		Map<String, String> res = getResponse(url, formdata);
		Assert.assertEquals(res.get("result_desc"), "新增丹露用户成功", "新增用户失败");
		fileWrite(res.get("user_id"));
	}

	/**
	 * Request user_name: terminal_location:CHNP035C345D2998 terminal_address:
	 * terminal_name: store_type:S011 business_license_code: mer_id:99999999
	 * user_id
	 * 
	 * Response { "req_no": null, "result_desc": "修改丹露用户成功", "mer_id":
	 * "99999999", "charset": null, "user_id":
	 * "79076259d8d24d8b8fa63c0e7708bef4", "reserved": null }
	 */
	@Test(dependsOnMethods = "newUser")
	public void modifyUser() {
		String url = uc + "/user/modify";
		List<NameValuePair> formdata = new ArrayList<NameValuePair>();
		formdata.add(new BasicNameValuePair("user_name", "testjlg01" + currenTime()));
		formdata.add(new BasicNameValuePair("terminal_location", "CHNP035C345D2998"));
		formdata.add(new BasicNameValuePair("terminal_address", "changed!!!"));
		formdata.add(new BasicNameValuePair("terminal_name", "changed!!!"));
		formdata.add(new BasicNameValuePair("store_type", "S011"));
		formdata.add(new BasicNameValuePair("business_license_code", "123456"));
		formdata.add(new BasicNameValuePair("mer_id", "99999999"));
		formdata.add(new BasicNameValuePair("user_id", fileRead()));
		Map<String, String> res = getResponse(url, formdata);
		Assert.assertEquals(res.get("result_desc"), "修改丹露用户成功", "修改用户失败");
	}

	/**
	 * Request third_order_no:1011233211234567 pay_type:2 order_amount:10000
	 * buyer_id:(user_id) receive_person:lyf receive_tel:1300000000
	 * receive_address:address order_invoice:0 merchandise_info: mer_id:99999999
	 * req_no:741236985
	 * 
	 * Response { "req_no": "741236985", "result_desc": "第三方订单号已关联订单", "mer_id":
	 * "99999999", "charset": "UTF-8", "order_no": "" }
	 */
	@Test(dependsOnMethods = "modifyUser")
	public void newOrder() {
		String url = tc + "/order/add";
		List<NameValuePair> formdata = new ArrayList<NameValuePair>();
		formdata.add(new BasicNameValuePair("third_order_no", currenTime()));
		formdata.add(new BasicNameValuePair("pay_type", "2"));
		formdata.add(new BasicNameValuePair("order_amount", "100"));
		formdata.add(new BasicNameValuePair("buyer_id", fileRead()));
		formdata.add(new BasicNameValuePair("receive_person:lyf", "S011"));
		formdata.add(new BasicNameValuePair("receive_tel", "13000000000"));
		formdata.add(new BasicNameValuePair("receive_address:", "ADD"));
		formdata.add(new BasicNameValuePair("order_invoice", "0"));
		formdata.add(new BasicNameValuePair("merchandise_info",
				"[{\"merchandise_id\":\"e4bec19a23074046828b4bb893c907ea\",\"merchandise_num\":\"10\"}]"));
		formdata.add(new BasicNameValuePair("mer_id", "99999999"));
		formdata.add(new BasicNameValuePair("req_no", "123456789"));
		Map<String, String> res = getResponse(url, formdata);
		Assert.assertEquals(res.get("result_desc"), "", "创建订单失败");
		fileWrite(res.get("order_no"));
	}

	/**
	 * Request order_no: mer_id:99999999 req_no: order_modify_amount:
	 * 
	 * Response { "req_no": "741236985", "result_desc": "第三方订单号已关联订单", "mer_id":
	 * "99999999", "charset": "UTF-8", "order_no": "" }
	 */
	@Test(dependsOnMethods = "newOrder")
	public void modifyOrder() {
		String url = tc + "/order/modify";
		List<NameValuePair> formdata = new ArrayList<NameValuePair>();
		formdata.add(new BasicNameValuePair("order_no", fileRead()));
		formdata.add(new BasicNameValuePair("mer_id", "99999999"));
		formdata.add(new BasicNameValuePair("req_no", "10000001"));
		formdata.add(new BasicNameValuePair("order_modify_amount", "10"));
		Map<String, String> res = getResponse(url, formdata);
		Assert.assertEquals(res.get("result_desc"), "", "修改订单失败");
	}

	/**
	 * Request order_no: mer_id:99999999 req_no: notice_url:
	 * 
	 * Response { "req_no": "963214785", "result_desc": "订单状态异常或当前请求不适用该订单",
	 * "mer_id": "99999999", "charset": "UTF-8", "order_no": "" }
	 * 
	 * 
	 */
	@Test(dependsOnMethods = "modifyOrder")
	public void sendOrder() {
		String url = tc + "/order/send";
		List<NameValuePair> formdata = new ArrayList<NameValuePair>();
		formdata.add(new BasicNameValuePair("order_no", fileRead()));
		formdata.add(new BasicNameValuePair("mer_id", "99999999"));
		formdata.add(new BasicNameValuePair("req_no", "10000001"));
		formdata.add(new BasicNameValuePair("notice_url", "any"));
		Map<String, String> res = getResponse(url, formdata);
		Assert.assertEquals(res.get("result_desc"), "", "修改订单失败");
	}

	/**
	 * Request order_no: mer_id:99999999 req_no:
	 * 
	 * Response { "req_no": "741236985", "result_desc": "", "mer_id":
	 * "99999999", "charset": "UTF-8", "order_no": "10783411540003" }
	 * 
	 * 
	 */
	@Test(dependsOnMethods = "sendOrder")
	public void cancelOrder() {
		String url = tc + "/order/cancel";
		List<NameValuePair> formdata = new ArrayList<NameValuePair>();
		formdata.add(new BasicNameValuePair("order_no", fileRead()));
		formdata.add(new BasicNameValuePair("mer_id", "99999999"));
		formdata.add(new BasicNameValuePair("req_no", "10000001"));
		Map<String, String> res = getResponse(url, formdata);
		Assert.assertEquals(res.get("result_desc"), "", "修改订单失败");
	}

	@Test(dependsOnMethods = "sendOrder")
	public void searchOrder() {
		String url = umpay + "/searchPayOrder";
		List<NameValuePair> formdata = new ArrayList<NameValuePair>();
		formdata.add(new BasicNameValuePair("orderNo", fileRead()));
		formdata.add(new BasicNameValuePair("url", "http://pc.uat.idanlu.com"));
		String res = getResponseString(url, formdata);
		Assert.assertTrue(res.contains("处理成功"), "查支付单失败");
	}

	@Test(dependsOnMethods = "searchOrder")
	public void payOrder() {
		String url = umpay + "/payResult";
		List<NameValuePair> formdata = new ArrayList<NameValuePair>();
		formdata.add(new BasicNameValuePair("orderNo", fileRead()));
		formdata.add(new BasicNameValuePair("url", "http://pc.uat.idanlu.com"));
		formdata.add(new BasicNameValuePair("cardType", "01"));
		String res = getResponseString(url, formdata);
		Assert.assertTrue(res.contains("00"), "支付订单失败");
	}

	/**
	 * 
	 * clean test data.
	 */
	@AfterSuite
	public void afterSuite() {

	}

	public Map<String, String> getResponse(String url, List<NameValuePair> formdata) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formdata, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("Executing request: " + httppost.getURI());
			System.out.println("Request Payload: " + formdata);
			CloseableHttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			String str = EntityUtils.toString(entity, "UTF-8");
			System.out.println("Response JSON String is: " + str);
			@SuppressWarnings("unchecked")
			Map<String, String> resMap = JSON.parseObject(str, Map.class);
			System.out.println("Convert to Map: " + resMap + "\n");
			return resMap;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("");
		return null;
	}

	public String getResponseString(String url, List<NameValuePair> formdata) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formdata, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("Executing request: " + httppost.getURI());
			System.out.println("Request Payload: " + formdata);
			CloseableHttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			String str = EntityUtils.toString(entity, "UTF-8");
			System.out.println("Response String is: " + str);
			return str;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("");
		return null;
	}

	public String currenTime() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("MMddhhmmss");
		return format.format(date);
	}

	public void fileWrite(String str) {
		File file = new File(filePath);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// write
		byte bt[] = new byte[1024];
		bt = str.getBytes();
		try {
			FileOutputStream in = new FileOutputStream(file);
			try {
				in.write(bt, 0, bt.length);
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String fileRead() {
		try {
			// 读取文件内容 (输入流)
			FileInputStream is = new FileInputStream(filePath);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);

			String line;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				// stringBuilder.append(line);
				stringBuilder.append(line);
			}
			reader.close();
			is.close();
			return String.valueOf(stringBuilder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
