package io.github.belachewhm.cofi.coding.exercise.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;
import io.github.belachewhm.cofi.coding.exercise.service.DataRetrievalService;

@RunWith(MockitoJUnitRunner.class)
public class MainFeaturesServiceImplTest {
	@Mock
	private DataRetrievalService dataRetrievalService;

	@InjectMocks
	private MainFeaturesServiceImpl mainFeaturesServiceImpl;

	private static final Double DELTA = 0.001;

	private List<StockRecord> returnRecords;

	@Before
	public void setup() {
		returnRecords = new ArrayList<StockRecord>();
		Mockito.when(dataRetrievalService.getStockRecords()).thenReturn(returnRecords);
	}

	@Test
	public void testAverageMonthlyOpenAndClose_averageOpen() {
		Double open_1 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double open_2 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double open_3 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double close_1 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double close_2 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double close_3 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setOpen(open_1);
				setClose(close_1);
				setDate(new Date());
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setOpen(open_2);
				setClose(close_2);
				setDate(new Date());
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setOpen(open_3);
				setClose(close_3);
				setDate(new Date());
			}
		});
		String average_open = mainFeaturesServiceImpl.getAverageMonthlyOpenAndClose("TEST_TICKER").get("TEST_TICKER")
				.get((new SimpleDateFormat("YYYY-MM").format(new Date()))).get("average_open");
		Assert.assertEquals((open_1 + open_2 + open_3) / 3, Double.parseDouble(average_open), DELTA * 4);
	}

	@Test
	public void testAverageMonthlyOpenAndClose_averageClose() {
		Double open_1 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double open_2 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double open_3 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double close_1 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double close_2 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double close_3 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setOpen(open_1);
				setClose(close_1);
				setDate(new Date());
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setOpen(open_2);
				setClose(close_2);
				setDate(new Date());
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setOpen(open_3);
				setClose(close_3);
				setDate(new Date());
			}
		});
		String average_close = mainFeaturesServiceImpl.getAverageMonthlyOpenAndClose("TEST_TICKER").get("TEST_TICKER")
				.get((new SimpleDateFormat("YYYY-MM").format(new Date()))).get("average_close");
		Assert.assertEquals((close_1 + close_2 + close_3) / 3, Double.parseDouble(average_close), DELTA * 4);
	}

	@After
	public void teardown() {
		returnRecords = null;
	}
}
