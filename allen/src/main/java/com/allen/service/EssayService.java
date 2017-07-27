package com.allen.service;

import java.util.List;

import com.allen.bean.EssayBean;
import com.allen.bean.ResultBean;
import com.allen.model.Essay;

public interface EssayService {

	public void saveOrUpdate(Essay essay);
	
	public List<EssayBean> getEssayList();
	
	public EssayBean getEssay(String id);
	
	public ResultBean restGetEssayList();
	
	public ResultBean restGetEssay(String id);
	
	public ResultBean restPutOrPostEssay(EssayBean essayBean) throws Exception;
	
	public ResultBean restDeleteEssay(String id);
}