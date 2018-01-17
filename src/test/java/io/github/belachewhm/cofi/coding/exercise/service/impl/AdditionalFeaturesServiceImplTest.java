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

@RunWith(MockitoJUnitRunner.class)
public class AdditionalFeaturesServiceImplTest {
	@Mock
	private List<StockRecord> mockStockRecords;

	@InjectMocks
	private AdditionalFeaturesServiceImpl additionalFeaturesServiceImpl;

	private static final Double DELTA = 0.001;

	private List<StockRecord> returnRecords;

	@Before
	public void setup() {
		returnRecords = new ArrayList<StockRecord>();
		Mockito.when(mockStockRecords.stream()).thenReturn(returnRecords.stream());
	}

	@Test
	public void testMaxDailyProfit_profit() {
		Double high_1 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double high_2 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double high_3 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double low_1 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double low_2 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double low_3 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setHigh(high_1);
				setLow(low_1);
				setDate(new Date());
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setHigh(high_2);
				setLow(low_2);
				setDate(new Date());
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setHigh(high_3);
				setLow(low_3);
				setDate(new Date());
			}
		});
		Double profit_1 = (high_1 - low_1);
		Double profit_2 = (high_2 - low_2);
		Double profit_3 = (high_3 - low_3);
		Double maxProfit = Math.max(profit_1, Math.max(profit_2, profit_3));
		Assert.assertEquals(maxProfit,
				Double.parseDouble(
						additionalFeaturesServiceImpl.maxDailyProfit("TEST_TICKER").get("TEST_TICKER").get("profit")),
				DELTA);
	}

	@Test
	public void testMaxDailyProfit_date() {
		Date date = new Date(0);
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setHigh(100.0);
				setLow(50.0);
				setDate(new Date());
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setHigh(100.0);
				setLow(95.0);
				setDate(new Date());
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setHigh(100.0);
				setLow(10.0);
				setDate(date);
			}
		});
		Assert.assertEquals((new SimpleDateFormat("yyyy-MM-dd")).format(date),
				additionalFeaturesServiceImpl.maxDailyProfit("TEST_TICKER").get("TEST_TICKER").get("date"));
	}

	@Test
	public void testBiggestLoser() {
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER_1");
				setOpen(100.0);
				setClose(95.0);
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER_1");
				setOpen(100.0);
				setClose(10.0);
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER_2");
				setOpen(100.0);
				setClose(5.0);
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER_3");
				setOpen(100.0);
				setClose(100.0);
			}
		});
		Assert.assertEquals("2", additionalFeaturesServiceImpl.biggestLoser(new String[] { "TEST_TICKER_1" })
				.get("TEST_TICKER_1").toString());
	}

	@After
	public void teardown() {
		returnRecords = null;
	}
}
