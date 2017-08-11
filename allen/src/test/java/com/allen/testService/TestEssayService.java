package com.allen.testService;

import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.allen.dao.EssayDao;
import com.allen.model.Essay;
import com.allen.service.EssayService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEssayService {

	@MockBean
	private EssayDao essayDao;
	
	@Autowired
	private EssayService essayService;
	
	private Essay essay;
	
	@Before
	public void testSave() {
		essay = new Essay();
		essay.setAuthor("allen");
		essay.setContent("me");
		essay.setDate(new Date());
		essay.setId("123");
		essay.setTitle("hihi");
		essayService.saveOrUpdate(essay);
	}
	
	@Test
	public void testGetList() {
		given(essayService.getEssayList()).willReturn(Arrays.asList(essay));
	}
	
	@Test
	public void testGetOne() {
		given(essayService.getEssay("123")).willReturn(essay);
	}
	
	@Test
	public void testPutOrPost() {
		essay.setContent("hello");
		essayService.saveOrUpdate(essay);
		given(essayService.getEssay("123")).willReturn(essay);
		
	}
	
	@Test
	public void testDelete() {
		essayService.deleteEssay(essay.getId());
		Assert.assertEquals(0, essayService.getEssayList().size());
	}
}
