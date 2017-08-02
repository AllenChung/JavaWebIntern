package com.allen.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.allen.model.ConfigInfo;

@Repository("ConfigInfoDao")
public class ConfigInfoDaoImpl extends AbstractDao<String, ConfigInfo> implements ConfigInfoDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigInfo> getList() {
		return this.getSession().createQuery("select config from ConfigInfo as config").list();
	}

	@Override
	public void saveOrUpdate(ConfigInfo cofigInfo) {
		this.getSession().saveOrUpdate(cofigInfo);
	}

	@Override
	public String getValueBykey(String key) {
		return ((ConfigInfo)this.getSession().createQuery("from ConfigInfo where key=:key").setParameter("key", key).list().get(0)).getValue();
	}
}
