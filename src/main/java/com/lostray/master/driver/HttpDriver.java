package com.lostray.master.driver;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpDriver {
	private String uri;
	private String json;
	private Map<String, String> params = new HashMap<String, String>();
	private HttpEntity httpEntity;
	private CloseableHttpClient client;
	private RequestConfig requestConfig;
	private CookieStore cookieStore = new BasicCookieStore();
	private Map<String, String> header = new HashMap<String, String>();

	private Logger logger = Logger.getLogger(this.getClass());

	private boolean isPrint = false;

	public HttpDriver(boolean isPrint) {
		this.setConfig();
		this.isPrint = isPrint;
	}

	public HttpDriver() {
		this.setConfig();
	}

	public HttpResult post() throws IOException {
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(httpEntity);
		return this.execute(httpPost, false);
	}

	public HttpResult postWithByte() throws IOException {
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(httpEntity);
		return this.execute(httpPost, true);
	}

	public HttpResult postWithJSON() throws IOException {
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Accept-Charset", "UTF-8");
		StringEntity se = new StringEntity(json, "UTF-8");
		se.setContentType("application/json");
		httpPost.setEntity(se);
		return this.execute(httpPost, false);
	}

	public HttpResult postWithJSONBody() throws IOException {
		return postWithJSON();
	}

	public HttpResult postWithBody() throws IOException {
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setHeader("Accept-Charset", "UTF-8");
		StringEntity se = new StringEntity(json, "UTF-8");
		httpPost.setEntity(se);
		return this.execute(httpPost, false);
	}

	public HttpResult get() throws IOException {
		HttpGet httpGet = new HttpGet(uri);
		return this.execute(httpGet, false);
	}

	public void getWithFile(String filePath) throws IOException {
		HttpGet httpGet = new HttpGet(uri);
		httpGet.setConfig(requestConfig);
		for (String key : header.keySet()) {
			httpGet.setHeader(key, header.get(key));
		}
		try {
			HttpResponse response = client.execute(httpGet);
			this.printResponse(response, null);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			File file = new File(filePath);
			file.getParentFile().mkdirs();
			FileOutputStream fileout = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int ch = 0;
			while ((ch = is.read(buffer)) != -1) {
				fileout.write(buffer, 0, ch);
			}
			is.close();
			fileout.flush();
			fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RemoteException();
		} finally {
			httpGet.releaseConnection();
		}
	}

	public HttpResult put() throws IOException {
		HttpPut httpPut = new HttpPut(uri);
		httpPut.setEntity(httpEntity);
		return this.execute(httpPut, false);
	}

	public HttpResult putWithByte() throws IOException {
		HttpPut httpPut = new HttpPut(uri);
		httpPut.setEntity(httpEntity);
		return this.execute(httpPut, true);
	}

	public HttpResult putWithJSON() throws IOException {
		HttpPut httpPut = new HttpPut(uri);
		httpPut.setHeader("Content-Type", "application/json");
		httpPut.setHeader("Accept-Charset", "UTF-8");
		StringEntity se = new StringEntity(json, "UTF-8");
		se.setContentType("application/json");
		httpPut.setEntity(se);
		return this.execute(httpPut, false);
	}

	public HttpResult delete() throws IOException {
		HttpDelete httpDelete = new HttpDelete(uri);
		return this.execute(httpDelete, false);
	}

	public HttpResult patch() throws IOException {
		HttpPatch httpPatch = new HttpPatch(uri);
		httpPatch.setEntity(httpEntity);
		return this.execute(httpPatch, false);
	}

	public HttpResult patchWithByte() throws IOException {
		HttpPatch httpPatch = new HttpPatch(uri);
		httpPatch.setEntity(httpEntity);
		return this.execute(httpPatch, true);
	}

	public HttpResult patchWithJSON() throws IOException {
		HttpPatch httpPatch = new HttpPatch(uri);
		httpPatch.setHeader("Content-Type", "application/json");
		httpPatch.setHeader("Accept-Charset", "UTF-8");
		StringEntity se = new StringEntity(json, "UTF-8");
		se.setContentType("application/json");
		httpPatch.setEntity(se);
		return this.execute(httpPatch, false);
	}

	public HttpResult postWithFile() throws IOException {
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setConfig(requestConfig);
		for (String key : header.keySet()) {
			httpPost.setHeader(key, header.get(key));
		}
		httpPost.setEntity(httpEntity);
		this.printRequest(httpPost);
		try {
			HttpResponse response = client.execute(httpPost);
			return getHttpResult(response.getStatusLine().getStatusCode(),
					EntityUtils.toString(response.getEntity(), "UTF-8"), null);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RemoteException();
		} finally {
			httpPost.releaseConnection();
			this.clear();
		}
	}

	private HttpResult execute(HttpRequestBase http, boolean isByte) throws IOException {
		http.setConfig(requestConfig);
		http.setHeader("Accept", "*/*");
		for (String key : header.keySet()) {
			http.setHeader(key, header.get(key));
		}
		this.printRequest(http);
		try {
			HttpResponse response = client.execute(http);
			HttpResult httpResult;
			HttpEntity responseEntity = response.getEntity();
			if (isByte) {
				httpResult = getHttpResult(response.getStatusLine().getStatusCode(), null,
						EntityUtils.toByteArray(responseEntity));
			} else if (responseEntity != null) {
				httpResult = getHttpResult(response.getStatusLine().getStatusCode(),
						EntityUtils.toString(responseEntity, "UTF-8"), null);
			} else {
				httpResult = getHttpResult(response.getStatusLine().getStatusCode(), "", null);
			}
			this.printResponse(response, httpResult);
			return httpResult;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RemoteException();
		} finally {
			http.releaseConnection();
			this.clear();
		}
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
			}
		}
		try {
			this.httpEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public void setParams(Map<String, String> params, Map<String, File> files) {
		this.params = params;
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				builder.addTextBody(entry.getKey(), entry.getValue(), ContentType.TEXT_PLAIN.withCharset("UTF-8"));
			}
		}
		if (files != null && !files.isEmpty()) {
			for (Map.Entry<String, File> entry : files.entrySet()) {
				builder.addPart(entry.getKey(), new FileBody(entry.getValue()));
			}
		}
		this.httpEntity = builder.build();
	}

	public void setParams(byte[] bytes) {
		this.httpEntity = new ByteArrayEntity(bytes);
	}

	private void printRequest(HttpRequestBase request) {
		if (isPrint) {
			logger.info("*****************request*****************");
			logger.info("uri=" + request.getURI());
			logger.info("method=" + request.getMethod());
			logger.info("Headers");
			for (Header header : request.getAllHeaders()) {
				logger.info("     " + header.getName() + ":" + header.getValue());
			}
			logger.info("Config");
			logger.info("     " + request.getConfig());
			logger.info("Json");
			logger.info("     " + json);
			logger.info("Params");
			if (params != null) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					logger.info("     " + entry.getKey() + ":" + entry.getValue());
				}
			}
		}
	}

	private void printResponse(HttpResponse response, HttpResult httpResult) throws IOException {
		if (isPrint) {
			logger.info("*****************response*****************");
			logger.info("Headers");
			for (Header header : response.getAllHeaders()) {
				logger.info("     " + header.getName() + ":" + header.getValue());
			}
			if (httpResult != null) {
				logger.info("Status Code: " + httpResult.getStatuscode());
				logger.info("Body: " + httpResult.getsBody());
			}
		}
	}

	private void clear() {
		uri = "";
		json = "";
		params = new HashMap<String, String>();

		this.httpEntity = null;
		this.header.remove("x-ap-id");
		this.header.remove("x-sign-type");
		this.header.remove("x-sign");
	}

	private HttpResult getHttpResult(int code, String sBody, byte[] bBody) throws IOException {
		HttpResult result = new HttpResult();
		result.setStatuscode(code);
		result.setsBody(sBody);
		result.setbBody(bBody);
		return result;
	}

	private void setConfig() {
		this.client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
		this.requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000)
				.setConnectionRequestTimeout(60000).build();
	}

	public static String getUri(String domain, String path) {
		return getUri(domain, path, null);
	}

	public static String getUri(String domain, String path, Map<String, String> params) {
		String uri = domain;
		if (!domain.startsWith("http://")) {
			uri = "http://" + domain;
		}
		uri = uri + path;
		int i = 1;
		if (params != null && params.size() > 0) {
			uri = uri + "?";
			for (Map.Entry<String, String> entry : params.entrySet()) {
				uri = uri + entry.getKey() + "=" + entry.getValue();
				if (i != params.size()) {
					uri = uri + "&";
				}
				i++;
			}

		}
		return uri;
	}

	public String getPath(String path, Map<String, String> params) {
		if (params == null || params.size() == 0) {
			return path;
		}
		String tmp = path + "?";
		int i = 0;
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (i == 0) {
				i++;
			} else {
				tmp = tmp + "&";
			}
			tmp = tmp + entry.getKey() + "=" + entry.getValue();
		}
		return tmp;
	}

	public static void main(String[] args) {
		try {
			HttpDriver httpDriver = new HttpDriver();
			httpDriver.setUri("http://10.2.1.47:8001/cache");
			HttpResult httpResult = httpDriver.delete();
			System.out.println(httpResult.getsBody());
			System.out.println(httpResult.getStatuscode());
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

	public void setHeader(String key, String value) {
		this.header.put(key, value);
	}

	public void setJson(String json) {
		this.json = json;
	}

	public void setUri(String url) {
		this.uri = url;
	}

	public boolean getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(boolean isPrint) {
		this.isPrint = isPrint;
	}
}
