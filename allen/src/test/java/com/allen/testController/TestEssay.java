package com.allen.testController;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.allen.controller.EssayController;
import com.allen.model.Essay;
import com.allen.service.EssayService;

@RunWith(SpringRunner.class)
@WebMvcTest(EssayController.class)
public class TestEssay {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private EssayService essayService;
	
	@Before
	public void setUp() throws Exception {
		Essay essay = new Essay();
		essay.setAuthor("allen");
		essay.setContent("me");
		essay.setDate(new Date());
		essay.setId("123");
		essay.setTitle("hihi");
		essayService.saveOrUpdate(essay);
		
		given(essayService.getEssayList()).willReturn(Arrays.asList(essay));
	}
	
	@Test
	public void testEssayController() throws Exception {
		this.mvc.perform(get("/essay")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(containsString("allen")));
		
	}
}
