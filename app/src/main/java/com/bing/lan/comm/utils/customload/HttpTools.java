package com.bing.lan.comm.utils.customload;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 网络工具类
 * 
 * @author Lean
 * */
public class HttpTools {

	private final static String TAG = "HttpTools";

	/**
	 * 根据URL生成HttpURLConnection
	 * 
	 * @param url
	 * @return
	 */
	public static HttpURLConnection createURLConnection(String url) {
		HttpURLConnection urlCon = null;
		try {
			urlCon = (HttpURLConnection) new URL(url).openConnection();
			urlCon.setConnectTimeout(10000);
			urlCon.setReadTimeout(30000);
		} catch (Exception e) {
			Log.e(TAG, "createURLConnection:" + e.getMessage());
		}
		return urlCon;
	}

	/**
	 * get方式请求网络
	 * 
	 * @param url
	 */
	public static String doGetConnetion(String url) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = null;
		params = httpClient.getParams();
		//设置连接超时
		HttpConnectionParams.setConnectionTimeout(params, 5000);
		//设置请求数据超时
		HttpConnectionParams.setSoTimeout(params, 20000);
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse;
		StringBuffer sb = new StringBuffer();
		try {
			httpResponse = httpClient.execute(httpGet);
			if (200 == httpResponse.getStatusLine().getStatusCode()) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(httpResponse.getEntity()
								.getContent()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
			}
		} catch (Exception e) {
			Log.e(TAG, "doGetConnetion:" + e.getMessage());
		}
		return sb.toString();
	}

	/**
	 * post方式请求网络
	 * 
	 * @param url
	 * @param paramMap
	 */
	public static String doPostConnetion(String url,
			Map<String, Object> paramMap) {
		HttpClient client = new DefaultHttpClient();
		HttpParams params = null;
		params = client.getParams();
		//设置连接超时
		HttpConnectionParams.setConnectionTimeout(params, 5000);
		//设置请求数据超时
		HttpConnectionParams.setSoTimeout(params, 20000);
		HttpPost method = new HttpPost(url);
		StringBuffer sb = new StringBuffer();
		HttpResponse response;
		try {
			if (paramMap != null && paramMap.size() > 0) {
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						paramMap.size());
				// 添加对应的参数键值对
				for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
					nameValuePairs.add(new BasicNameValuePair(entry.getKey(),
							entry.getValue().toString()));
				}
				method.setEntity(new UrlEncodedFormEntity(nameValuePairs,
						"UTF-8"));

			}
			response = client.execute(method);
			if (200 == response.getStatusLine().getStatusCode()) {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));
				String str = null;
				while ((str = br.readLine()) != null) {
					sb.append(str);
				}
				br.close();
				br = null;
			}
		} catch (Exception e) {
			Log.e(TAG, "doPostConnetion:" + e.getMessage());
		}
		return sb.toString();

	}
}
