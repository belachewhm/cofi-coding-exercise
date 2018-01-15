package io.github.belachewhm.cofi.coding.exercise.controller.impl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import io.github.belachewhm.cofi.coding.exercise.Application;
import io.github.belachewhm.cofi.coding.exercise.service.StockService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebMvcTest(MainFeaturesControllerImpl.class)
public class MainFeaturesControllerImplTest {
	@MockBean
	private StockService mockService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testAverageMonthlyOpenAndClose() throws Exception {
		Map<String, Map<String, Map<String, String>>> testMap = new LinkedHashMap<String, Map<String, Map<String, String>>>();
		Mockito.when(mockService.averageMonthlyOpenAndClose(Mockito.anyList())).thenReturn(testMap);
		MvcResult result = mockMvc.perform(get("/averageMonthlyOpenAndClose")).andDo(print()).andExpect(status().isOk())
				.andReturn();
		// TODO: Assert further expectations
	}
}