package io.github.belachewhm.cofi.coding.exercise.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.core.util.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class DataRetrievalServiceImplTest {
	@Mock
	private Environment environment;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	DataRetrievalServiceImpl dataRetrievalServiceImpl;

	String response;

	@Before
	public void setup() {
		response = new String();
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(response);
	}

	@Test
	public void testStockRecords_notNull() {
		response = "";
		Assert.assertNotNull(dataRetrievalServiceImpl.getStockRecords());
	}

	@Test
	public void testStockRecords_size_0() {
		response = "test, test";
		Assert.assertTrue(dataRetrievalServiceImpl.getStockRecords().size() == 0);
	}

	@Test
	public void testGetStockRecords_375() throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(
				new FileInputStream(new File("src/test/resources/WIKI-PRICES_375.csv")));
		response = IOUtils.toString(inputStreamReader);
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(response);
		Assert.assertEquals(375, dataRetrievalServiceImpl.getStockRecords().size());
	}

	@Test
	public void testGetStockRecords_10000() throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(
				new FileInputStream(new File("src/test/resources/WIKI-PRICES_10000.csv")));
		response = IOUtils.toString(inputStreamReader);
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(response);
		Assert.assertEquals(10000, dataRetrievalServiceImpl.getStockRecords().size());
	}
}
