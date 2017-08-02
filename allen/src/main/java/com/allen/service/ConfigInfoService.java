package com.allen.service;

import java.util.List;

import com.allen.dto.ConfigInfoTest;
import com.allen.model.ConfigInfo;

public interface ConfigInfoService {

	public List<ConfigInfo> getList();
	
	public void saveOrUpdate(ConfigInfo configInfo) throws Exception;
	
	public boolean testConfigChange(ConfigInfoTest configInfoTest);
	
	public String getValueBykey(String key);
}
