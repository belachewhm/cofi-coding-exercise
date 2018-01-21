package io.github.belachewhm.cofi.coding.exercise.controller.impl;

import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
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
import io.github.belachewhm.cofi.coding.exercise.service.AdditionalFeaturesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebMvcTest(AdditionalFeaturesControllerImpl.class)
public class AdditionalFeaturesControllerImplTest {
	@MockBean
	private AdditionalFeaturesService mockService;

	@Autowired
	private MockMvc mockMvc;

	Map<String, Map<String, String>> dailyProfitMap;
	Map<String, String> averageVolumeMap;
	Map<String, Map<String, String>> busyDayMap;
	Map<String, Integer> biggestLoserMap;

	private SimpleDateFormat sdf;
	private Random random;
	private MvcResult result;

	@Before
	public void setup() {
		dailyProfitMap = new HashMap<String, Map<String, String>>();
		averageVolumeMap = new HashMap<String, String>();
		busyDayMap = new HashMap<String, Map<String, String>>();
		biggestLoserMap = new HashMap<String, Integer>();
		sdf = new SimpleDateFormat("YYYY-MM");
		random = new Random();
	}

	@Test
	public void testGetAllMaxDailyProfits_200() throws Exception {
		dailyProfitMap.put("TEST", new HashMap<String, String>() {
			{
				this.put(sdf.format(new Date()), new DecimalFormat("#.##").format(100 * random.nextDouble()));
			}
		});
		Mockito.when(mockService.getAllMaxDailyProfits()).thenReturn(dailyProfitMap);
		result = mockMvc.perform(get("/maxDailyProfit/")).andExpect(status().isOk()).andReturn();
		// Assert further expectations
	}

	@Test
	public void testGetAllMaxDailyProfits_404() throws Exception {
		Mockito.when(mockService.getAllMaxDailyProfits()).thenReturn(dailyProfitMap);
		mockMvc.perform(get("/maxDailyProfit/")).andExpect(status().isNotFound()).andReturn();
		// Assert further expectations

		Mockito.when(mockService.getAllMaxDailyProfits()).thenReturn(null);
		result = mockMvc.perform(get("/maxDailyProfit/")).andExpect(status().isNotFound()).andReturn();
		// Assert further expectations
	}

	@Test
	public void testGetMaxDailyProfit_200() throws Exception {
		dailyProfitMap.put("TEST", new HashMap<String, String>() {
			{
				this.put(sdf.format(new Date()), new DecimalFormat("#.##").format(100 * random.nextDouble()));
			}
		});
		Mockito.when(mockService.getMaxDailyProfit(anyString())).thenReturn(dailyProfitMap);
		result = mockMvc.perform(get("/maxDailyProfit/TEST/")).andExpect(status().isOk()).andReturn();
		// Assert further expectations
	}

	@Test
	public void testGetMaxDailyProfit_400() throws Exception {
		Mockito.when(mockService.getMaxDailyProfit(anyString())).thenReturn(dailyProfitMap);
		result = mockMvc.perform(get("/maxDailyProfit/TEST/")).andExpect(status().isNotFound())
				.andReturn();
		// Assert further expectations

		Mockito.when(mockService.getMaxDailyProfit(anyString())).thenReturn(null);
		result = mockMvc.perform(get("/maxDailyProfit/TEST/")).andExpect(status().isNotFound())
				.andReturn();
		// Assert further expectations
	}

	@Test
	public void testGetAllAverageVolumes_200() throws Exception {
		averageVolumeMap.put("TEST", new DecimalFormat("#.##").format(100 * random.nextDouble()));
		Mockito.when(mockService.getAllAverageVolumes()).thenReturn(averageVolumeMap);
		result = mockMvc.perform(get("/averageVolume/")).andExpect(status().isOk()).andReturn();
		// Assert further expectations
	}

	@Test
	public void testGetAllAverageVolumes_404() throws Exception {
		Mockito.when(mockService.getAllAverageVolumes()).thenReturn(averageVolumeMap);
		mockMvc.perform(get("/averageVolume/")).andExpect(status().isNotFound()).andReturn();
		// Assert further expectations

		Mockito.when(mockService.getAllAverageVolumes()).thenReturn(null);
		result = mockMvc.perform(get("/averageVolume/")).andExpect(status().isNotFound()).andReturn();
		// Assert further expectations
	}

	@Test
	public void testGetAverageVolume_200() throws Exception {
		averageVolumeMap.put("TEST", new DecimalFormat("#.##").format(100 * random.nextDouble()));
		Mockito.when(mockService.getAverageVolume(anyString())).thenReturn(averageVolumeMap);
		result = mockMvc.perform(get("/averageVolume/TEST/")).andExpect(status().isOk()).andReturn();
		// Assert further expectations
	}

	@Test
	public void testGetAverageVolume_404() throws Exception {
		Mockito.when(mockService.getAverageVolume(anyString())).thenReturn(averageVolumeMap);
		mockMvc.perform(get("/averageVolume/TEST/")).andExpect(status().isNotFound()).andReturn();
		// Assert further expectations

		Mockito.when(mockService.getAverageVolume(anyString())).thenReturn(null);
		result = mockMvc.perform(get("/averageVolume/TEST/")).andExpect(status().isNotFound())
				.andReturn();
		// Assert further expectations
	}

	@Test
	public void testGetAllBusyDays_200() throws Exception {
		busyDayMap.put("TEST", new HashMap<String, String>() {
			{
				this.put(sdf.format(new Date()), new DecimalFormat("#.##").format(100 * random.nextDouble()));
			}
		});
		Mockito.when(mockService.getAllBusyDays()).thenReturn(busyDayMap);
		result = mockMvc.perform(get("/busyDay/")).andExpect(status().isOk()).andReturn();
		// Assert further expectations
	}

	@Test
	public void testGetAllBusyDays_404() throws Exception {
		Mockito.when(mockService.getAllBusyDays()).thenReturn(busyDayMap);
		mockMvc.perform(get("/busyDay/")).andExpect(status().isNotFound()).andReturn();
		// Assert further expectations

		Mockito.when(mockService.getAllBusyDays()).thenReturn(null);
		result = mockMvc.perform(get("/busyDay/")).andExpect(status().isNotFound()).andReturn();
		// Assert further expectations
	}

	@Test
	public void testGetBusyDay_200() throws Exception {
		busyDayMap.put("TEST", new HashMap<String, String>() {
			{
				this.put(sdf.format(new Date()), new DecimalFormat("#.##").format(100 * random.nextDouble()));
			}
		});
		Mockito.when(mockService.getBusyDay(anyString())).thenReturn(busyDayMap);
		result = mockMvc.perform(get("/busyDay/TEST/")).andExpect(status().isOk()).andReturn();
		// Assert further expectations
	}

	@Test
	public void testGetBusyDay_404() throws Exception {
		Mockito.when(mockService.getBusyDay(anyString())).thenReturn(busyDayMap);
		mockMvc.perform(get("/busyDay/TEST/")).andExpect(status().isNotFound()).andReturn();
		// Assert further expectations

		Mockito.when(mockService.getBusyDay(anyString())).thenReturn(null);
		result = mockMvc.perform(get("/busyDay/TEST/")).andExpect(status().isNotFound()).andReturn();
		// Assert further expectations
	}

	@Test
	public void testGetBiggestLoser_200() throws Exception {
		biggestLoserMap.put("TEST", random.nextInt());
		Mockito.when(mockService.getBiggestLoser()).thenReturn(biggestLoserMap);
		result = mockMvc.perform(get("/biggestLoser/")).andExpect(status().isOk()).andReturn();
		// Assert further expectations
	}

	@Test
	public void testGetBiggestLoser_404() throws Exception {
		Mockito.when(mockService.getBiggestLoser()).thenReturn(biggestLoserMap);
		result = mockMvc.perform(get("/biggestLoser/")).andExpect(status().isNotFound()).andReturn();

		Mockito.when(mockService.getBiggestLoser()).thenReturn(null);
		result = mockMvc.perform(get("/biggestLoser/")).andExpect(status().isNotFound()).andReturn();
	}

	@After
	public void teardown() {
		dailyProfitMap = null;
		averageVolumeMap = null;
		busyDayMap = null;
		biggestLoserMap = null;
		sdf = null;
		random = null;
	}
}
