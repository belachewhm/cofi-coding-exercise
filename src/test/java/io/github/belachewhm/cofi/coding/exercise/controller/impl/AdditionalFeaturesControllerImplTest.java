package io.github.belachewhm.cofi.coding.exercise.controller.impl;

import static org.mockito.Matchers.anyList;
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
@WebMvcTest(AdditionalFeaturesControllerImpl.class)
public class AdditionalFeaturesControllerImplTest {
	@MockBean
	private StockService mockService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testMaxDailyProfit() throws Exception {
		Map<String, Map<String, String>> testMap = new LinkedHashMap<String, Map<String, String>>();
		Mockito.when(mockService.maxDailyProfit(anyList())).thenReturn(testMap);
		MvcResult result = mockMvc.perform(get("/maxDailyProfit")).andDo(print()).andExpect(status().isOk())
				.andReturn();
		// TODO: Assert further expectations
	}

	@Test
	public void testAverageVolume() throws Exception {
		Map<String, String> testMap = new LinkedHashMap<String, String>();
		Mockito.when(mockService.averageVolume(anyList())).thenReturn(testMap);
		MvcResult result = mockMvc.perform(get("/averageVolume")).andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void testBusyDay() throws Exception {
		Map<String, Map<String, String>> testMap = new LinkedHashMap<String, Map<String, String>>();
		Mockito.when(mockService.busyDay(anyList())).thenReturn(testMap);
		MvcResult result = mockMvc.perform(get("/busyDay")).andDo(print()).andExpect(status().isOk()).andReturn();
		// TODO: Assert further expectations
	}

	@Test
	public void testBiggestLoser() throws Exception {
		Map<String, Integer> testMap = new LinkedHashMap<String, Integer>();
		Mockito.when(mockService.biggestLoser(anyList())).thenReturn(testMap);
		MvcResult result = mockMvc.perform(get("/biggestLoser")).andDo(print()).andExpect(status().isOk()).andReturn();
		// TODO: Assert further expectations
	}
}
