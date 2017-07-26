package com.allen.dao;

import java.util.List;

import com.allen.model.Essay;

public interface EssayDao {

	public void saveOrUpdate(Essay essay);
	
	public List<Essay> getEssayList();
	
	public Essay getEssay(String id);
	
	public void deleteEssay(String id);
}
