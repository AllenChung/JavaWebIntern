package com.allen.quartz;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;
import com.allen.cache.MyCache;
import com.allen.config.ModifyFile;
import com.allen.dto.ConfigInfoTest;
import com.allen.dto.ResultBean;
import com.allen.myThreadPool.MyThreadPool;

//thread for quartz
public class MyJob implements Job {

	public MyJob() {
	}

	@SuppressWarnings("resource")
	public void execute(JobExecutionContext context) throws JobExecutionException {

		// post the key and md5 to check whether the config is up to date
		HttpPost post = new HttpPost("http://localhost:8080/configInfoTest");
		CloseableHttpResponse response = null;
		try (CloseableHttpClient client = HttpClients.createDefault();) {
			// if the config exists in cache
			if (MyCache.cacheMap.get(MyCache.KEY) != null) {
				StringEntity s = new StringEntity(
						JSON.toJSONString(new ConfigInfoTest(MyCache.KEY, new String(MessageDigest.getInstance("MD5")
								.digest((MyCache.KEY + MyCache.cacheMap.get(MyCache.KEY)).getBytes())))));
				s.setContentEncoding("UTF-8");
				s.setContentType("application/json");
				post.setEntity(s);

				response = client.execute(post);
				int code = response.getStatusLine().getStatusCode();
				if (code == HttpStatus.SC_NOT_MODIFIED) {
					// if the config is already up to date
					return;
				}
			}

			// if the config has changed or the config doesn't exit in cache
			// now get the newest config from the server
			HttpGet get = new HttpGet("http://localhost:8080/configInfo/" + MyCache.KEY);
			response = client.execute(get);
			ResultBean result = JSON.parseObject(EntityUtils.toString(response.getEntity()), ResultBean.class);
			if (1 == result.getResult()) {
				Map<String, Object> map = result.getData();
				MyThreadPool.myPool.submit(new ModifyFile(MyCache.KEY, (String) map.get(MyCache.KEY)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
