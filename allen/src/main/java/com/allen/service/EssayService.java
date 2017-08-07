package com.allen.service;

import java.util.List;

import com.allen.model.Essay;

public interface EssayService {

	public void saveOrUpdate(Essay essay);
	
	public List<Essay> getEssayList();
	
	public Essay getEssay(String id);
	
	public void putOrPostEssay(Essay essays) throws Exception;
	
	public void deleteEssay(String id);
}