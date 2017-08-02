package com.allen.service;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.dao.EssayDao;
import com.allen.dto.EssayBean;
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
	public List<EssayBean> getEssayList() {
		List<Essay> essays = essayDao.getEssayList();
		List<EssayBean> essayBeans = new LinkedList<>();
		for (Essay essay : essays) {
			essayBeans.add(new EssayBean(essay));
		}
		return essayBeans;
	}

	@Override
	public EssayBean getEssay(String id) {
		return new EssayBean(essayDao.getEssay(id));
	}

	@Override
	public void putOrPostEssay(EssayBean essayBean) throws ParseException {
		essayDao.saveOrUpdate(new Essay(essayBean));
	}

	@Override
	public void deleteEssay(String id) {
		essayDao.deleteEssay(id);
	}
}
