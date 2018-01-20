package io.github.belachewhm.cofi.coding.exercise.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
public class AdditionalFeaturesServiceImplTest {
	@Mock
	private DataRetrievalService dataRetrievalService;

	@InjectMocks
	private AdditionalFeaturesServiceImpl additionalFeaturesServiceImpl;

	private static final Double DELTA = 0.001;

	private List<StockRecord> returnRecords;

	Random random;

	@Before
	public void setup() {
		random = new Random();
		returnRecords = new ArrayList<StockRecord>();
		Mockito.when(dataRetrievalService.getStockRecords()).thenReturn(returnRecords);
	}

	@Test
	public void testGetAllMaxDailyProfits_NotNull() {
		Assert.assertNotNull(additionalFeaturesServiceImpl.getAllMaxDailyProfits());
	}

	@Test
	public void testGetAllMaxDailyProfits_IsEmpty() {
		Assert.assertTrue(additionalFeaturesServiceImpl.getAllMaxDailyProfits().isEmpty());
	}

	@Test
	public void testGetAllMaxDailyProfits_NotEmpty() {
		returnRecords.add(new StockRecord() {
			{
				setTicker("COF");
				setHigh(100 * random.nextDouble());
				setLow(100 * random.nextDouble());
				setDate(new Date());
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("GOOGL");
				setHigh(100 * random.nextDouble());
				setLow(100 * random.nextDouble());
				setDate(new Date());
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("MSFT");
				setHigh(100 * random.nextDouble());
				setLow(100 * random.nextDouble());
				setDate(new Date());
			}
		});
		Mockito.when(dataRetrievalService.getStockRecords()).thenReturn(returnRecords);
		Assert.assertFalse(additionalFeaturesServiceImpl.getAllMaxDailyProfits().isEmpty());
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
		Assert.assertEquals(maxProfit, Double.parseDouble(
				additionalFeaturesServiceImpl.getMaxDailyProfit("TEST_TICKER").get("TEST_TICKER").get("profit")),
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
				additionalFeaturesServiceImpl.getMaxDailyProfit("TEST_TICKER").get("TEST_TICKER").get("date"));
	}

	@Test
	public void testGetAllAverageVolumes_NotNull() {
		Assert.assertNotNull(additionalFeaturesServiceImpl.getAllAverageVolumes());
	}

	@Test
	public void testGetAllAverageVolumes_IsEmpty() {
		Assert.assertTrue(additionalFeaturesServiceImpl.getAllAverageVolumes().isEmpty());
	}

	@Test
	public void testGetAllAverageVolumes_NotEmpty() {
		returnRecords.add(new StockRecord() {
			{
				setTicker("COF");
				setVolume(100 * random.nextDouble());
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("GOOGL");
				setVolume(100 * random.nextDouble());
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("MSFT");
				setVolume(100 * random.nextDouble());
			}
		});
		Mockito.when(dataRetrievalService.getStockRecords()).thenReturn(returnRecords);
		Assert.assertFalse(additionalFeaturesServiceImpl.getAllAverageVolumes().isEmpty());
	}

	@Test
	public void testGetAverageVolume() {
		Double volume1 = 100 * random.nextDouble();
		Double volume2 = 100 * random.nextDouble();
		Double volume3 = 100 * random.nextDouble();

		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST");
				setVolume(volume1);
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST");
				setVolume(volume2);
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST");
				setVolume(volume3);
			}
		});
		Mockito.when(dataRetrievalService.getStockRecords()).thenReturn(returnRecords);

		Assert.assertEquals((volume1 + volume2 + volume3) / 3,
				Double.parseDouble(additionalFeaturesServiceImpl.getAverageVolume("TEST").get("TEST")), DELTA * 6);
	}

	// TODO: add test cases for busyDay

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
		Assert.assertEquals("2", additionalFeaturesServiceImpl.getBiggestLoser().get("TEST_TICKER_1").toString());
	}

	@After
	public void teardown() {
		returnRecords = null;
	}
}
