package com.allen.service;

import java.security.MessageDigest;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.allen.bean.ConfigBean;
import com.allen.dao.ConfigInfoDao;
import com.allen.dto.ConfigInfoTest;
import com.allen.model.ConfigInfo;
import com.allen.multiThread.AsyncTask;

@Service("ConfigInfoService")
@Transactional
public class ConfigInfoServiceImpl implements ConfigInfoService {

	@Autowired
	private ConfigInfoDao configDao;	
	
	@Autowired
	private RedisTemplate<String, ConfigBean> redis;
	
	@Autowired
	private AsyncTask ansync;
	
	@Override
	public List<ConfigInfo> getList() {
		return configDao.getList();
	}

	@Override
	public void saveOrUpdate(ConfigInfo configInfo) throws Exception {
		configDao.saveOrUpdate(configInfo);
		//store the md5 of key and value in cache
		String md5 = new String(MessageDigest.getInstance("MD5").digest((configInfo.getKey() + configInfo.getValue()).getBytes()));
		redis.opsForValue().set(configInfo.getKey(), new ConfigBean(configInfo.getValue(), md5));
		//send the key and value to the sdk (notice that using the first string in key as the topic)
		ansync.sendMsgToKafka(configInfo.getKey().split("\\.")[0], configInfo.getKey(), configInfo.getValue());
	}

	//check whether the config has changed
	@Override
	public boolean testConfigChange(ConfigInfoTest configInfoTest) {
		String md5 = redis.opsForValue().get(configInfoTest.getKey()).getMd5();
		if (md5 == null) {
			throw new RuntimeException("there is no this key in redis");
		}
		if (configInfoTest.getMd5().equals(md5)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String getValueBykey(String key) {
		String value = redis.opsForValue().get(key).getValue();
		if (value != null) {
			return value;
		}
		return configDao.getValueBykey(key);
	}
}
