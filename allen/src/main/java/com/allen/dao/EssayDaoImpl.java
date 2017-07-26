package com.allen.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.allen.model.Essay;

@Repository("EssayDao")
public class EssayDaoImpl extends AbstractDao<String, Essay> implements EssayDao {

	@Override
	public void saveOrUpdate(Essay essay) {
		this.getSession().saveOrUpdate(essay);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Essay> getEssayList() {
		return this.getSession().createQuery("select essay from Essay as essay order by essay.date desc").list();
	}

	@Override
	public Essay getEssay(String id) {
		return this.getByKey(id);
	}

	@Override
	public void deleteEssay(String id) {
		this.delete(this.getByKey(id));
	}
}