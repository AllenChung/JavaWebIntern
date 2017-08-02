package com.allen.myThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//static thread pool
public class MyThreadPool {

	public static final ExecutorService myPool = Executors.newCachedThreadPool();
}
