package com.allen.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.bean.EssayBean;
import com.allen.bean.ResultBean;
import com.allen.dao.EssayDao;
import com.allen.model.Essay;
import com.allen.util.ResultBeanFactory;

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
	public ResultBean restPutOrPostEssay(EssayBean essayBean) throws ParseException {
		essayDao.saveOrUpdate(new Essay(essayBean));
		return ResultBeanFactory.getResult(1, null, null);
	}

	@Override
	public ResultBean restDeleteEssay(String id) {
		essayDao.deleteEssay(id);
		return ResultBeanFactory.getResult(1, null, null);
	}

	@Override
	public ResultBean restGetEssayList() {
		List<Essay> essays = essayDao.getEssayList();
		List<EssayBean> essayBeans = new LinkedList<>();
		for (Essay essay : essays) {
			essayBeans.add(new EssayBean(essay));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("essayList", essayBeans);
		return ResultBeanFactory.getResult(1, null, map);
	}

	@Override
	public ResultBean restGetEssay(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("essay", new EssayBean(essayDao.getEssay(id)));
		return ResultBeanFactory.getResult(1, null, map);
	}

}
