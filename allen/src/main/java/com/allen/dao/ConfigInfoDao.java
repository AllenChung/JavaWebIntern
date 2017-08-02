package com.allen.dao;

import java.util.List;

import com.allen.model.ConfigInfo;

public interface ConfigInfoDao {

	public List<ConfigInfo> getList();
	
	public void saveOrUpdate(ConfigInfo cofigInfo);
	
	public String getValueBykey(String key);
}
