package com.allen.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ZAL_CLIENT_INFO")
public class ClientInfo {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name="ID")
	private String id;

	@Column(name="Client")
	private String client;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clientInfo")
	private Set<ConfigInfo> configInfos;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Set<ConfigInfo> getConfigInfos() {
		return configInfos;
	}

	public void setConfigInfos(Set<ConfigInfo> configInfos) {
		this.configInfos = configInfos;
	}
}
