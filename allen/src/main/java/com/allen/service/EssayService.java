package com.allen.service;

import java.util.List;

import com.allen.dto.EssayBean;
import com.allen.model.Essay;

public interface EssayService {

	public void saveOrUpdate(Essay essay);
	
	public List<EssayBean> getEssayList();
	
	public EssayBean getEssay(String id);
	
	public void putOrPostEssay(EssayBean essayBean) throws Exception;
	
	public void deleteEssay(String id);
}