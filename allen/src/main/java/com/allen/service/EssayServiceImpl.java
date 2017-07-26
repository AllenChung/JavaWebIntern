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
		for (Essay essay: essays) {
			essayBeans.add(new EssayBean(essay));
		}
		return essayBeans;
	}

	@Override
	public EssayBean getEssay(String id) { 
		return new EssayBean(essayDao.getEssay(id));
	}

	@Override
	public ResultBean restPutOrPostEssay(EssayBean essayBean) {
		ResultBean result = new ResultBean();
		try {
			essayDao.saveOrUpdate(new Essay(essayBean));
		} catch (ParseException e) {
			result.setResult(0);
			result.setReason("wrong date");
			e.printStackTrace();
			return result;
		}
		result.setResult(1);
		return result;
	}

	@Override
	public ResultBean restDeleteEssay(String id) {
		essayDao.deleteEssay(id);
		ResultBean result = new ResultBean();
		result.setResult(1);
		return result;
	}

	@Override
	public ResultBean restGetEssayList() {
		List<Essay> essays = essayDao.getEssayList();
		List<EssayBean> essayBeans = new LinkedList<>();
		for (Essay essay: essays) {
			essayBeans.add(new EssayBean(essay));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("essayList", essayBeans);
		ResultBean result = new ResultBean();
		result.setResult(1);
		result.setData(map);
		return result;
	}

	@Override
	public ResultBean restGetEssay(String id) {
		ResultBean result = new ResultBean();
		result.setResult(1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("essay", new EssayBean(essayDao.getEssay(id)));
		result.setData(map);
		return result;
	}

	
}
