package io.github.belachewhm.cofi.coding.exercise.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;

@RunWith(MockitoJUnitRunner.class)
public class SpringConfigTest {
	@Mock
	private Environment environment;

	@Mock
	private BufferedReader bufferedReader;
	
	@Mock
	private HttpURLConnection httpURLConnection;

	@Mock
	private InputStream inputStream;
	
	@InjectMocks
	SpringConfig springConfig;

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
