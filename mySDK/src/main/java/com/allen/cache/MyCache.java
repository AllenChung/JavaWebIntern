package com.allen.cache;

import java.util.concurrent.ConcurrentHashMap;

//cache map
public class MyCache {
	
	public static final String KEY = "CRM.LOG.LEVEL";

	public static final ConcurrentHashMap<String, String> cacheMap = new ConcurrentHashMap<String, String>();
}
