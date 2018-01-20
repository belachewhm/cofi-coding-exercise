package io.github.belachewhm.cofi.coding.exercise.controller.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import io.github.belachewhm.cofi.coding.exercise.Application;
import io.github.belachewhm.cofi.coding.exercise.service.MainFeaturesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebMvcTest(MainFeaturesControllerImpl.class)
public class MainFeaturesControllerImplTest
{
	@MockBean
	private MainFeaturesService mockService;

	@Autowired
	private MockMvc mockMvc;

	Map<String, Map<String, Map<String, String>>> averageMonthlyOpenAndCloseMap;
	
	private SimpleDateFormat sdf;
	private Random random;
	private MvcResult result;
	
	@Before
	public void setup()
	{
		averageMonthlyOpenAndCloseMap = new HashMap<String, Map<String, Map<String, String>>>();
		sdf = new SimpleDateFormat("YYYY-MM");
		random = new Random();
	}
	
	@Test
	public void testAverageMonthlyOpenAndClose_200() throws Exception
	{
		averageMonthlyOpenAndCloseMap.put("TEST", new HashMap<String, Map<String, String>>()
		{{
			this.put(sdf.format(new Date()), new HashMap<String, String>()
			{{
				this.put("average_open", new DecimalFormat("#.##").format(100 * random.nextDouble()));
				this.put("average_close", new DecimalFormat("#.##").format(100 * random.nextDouble()));
			}});
		}});
		
	}
	
	@Test
	public void testAverageMonthlyOpenAndClose_404() throws Exception
	{

	}
}