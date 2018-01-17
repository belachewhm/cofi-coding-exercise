package io.github.belachewhm.cofi.coding.exercise.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;

@RunWith(MockitoJUnitRunner.class)
public class SpringConfigTest {
	@Mock
	private Environment environment;

	@Mock
	RestTemplateBuilder builder;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private BufferedReader bufferedReader;

	@InjectMocks
	SpringConfig springConfig;

	@Test
	public void testRestTemplate() {
		Mockito.when(builder.build()).thenReturn(restTemplate);
		Assert.assertEquals(restTemplate, springConfig.restTemplate(builder));
	}

	@Test
	public void testBufferedReader() {
		Mockito.when(environment.getProperty(Mockito.anyString())).thenReturn("TEST");
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn("");
		BufferedReader bufferedReader = springConfig.bufferedReader();
		Assert.assertNotNull(bufferedReader);
	}

	@Test
	public void testStockRecords_10000() throws IOException {
		File testFile = new File("src/test/resources/WIKI-PRICES_10000.csv");
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		try {
			Mockito.when(bufferedReader.lines()).thenReturn(br.lines());
			List<StockRecord> records = springConfig.stockRecords();
			Assert.assertEquals(10000, records.size());

		} catch (FileNotFoundException fnfe) {
			Assert.fail(fnfe.getMessage());
		} finally {
			br.close();
		}
	}

	@Test
	public void testStockRecords_375() throws IOException {
		File testFile = new File("src/test/resources/WIKI-PRICES_375.csv");
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		try {
			Mockito.when(bufferedReader.lines()).thenReturn(br.lines());
			List<StockRecord> records = springConfig.stockRecords();
			Assert.assertEquals(375, records.size());

		} catch (FileNotFoundException fnfe) {
			Assert.fail(fnfe.getMessage());
		} finally {
			br.close();
		}
	}
}
