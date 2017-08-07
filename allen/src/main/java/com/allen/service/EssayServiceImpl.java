package com.allen.service;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.dao.EssayDao;
import com.allen.model.Essay;

@Service("EssayService")
@Transactional
public class EssayServiceImpl implements EssayService {

	@Autowired
	private EssayDao essayDao;

	@Override
	public void saveOrUpdate(Essay essay) {
		essayDao.saveOrUpdate(essay);
	}

	@Override
	public List<Essay> getEssayList() {
		List<Essay> essays = essayDao.getEssayList();
		List<Essay> essayBeans = new LinkedList<Essay>();
		for (Essay essay : essays) {
			essayBeans.add(essay);
		}
		return essayBeans;
	}

	@Override
	public Essay getEssay(String id) {
		return essayDao.getEssay(id);
	}

	@Override
	public void putOrPostEssay(Essay essay) throws ParseException {
		essayDao.saveOrUpdate(essay);
	}

	@Override
	public void deleteEssay(String id) {
		essayDao.deleteEssay(id);
	}
}
