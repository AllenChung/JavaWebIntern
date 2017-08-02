package com.allen.dao;

import org.springframework.stereotype.Repository;

import com.allen.model.ClientInfo;

@Repository("ClientInfoDao")
public class ClientInfoDaoImpl extends AbstractDao<String, ClientInfo>implements ClientInfoDao {

	@Override
	public void saveOrUpdate(ClientInfo clientInfo) {
		this.getSession().saveOrUpdate(clientInfo);
	}

}
