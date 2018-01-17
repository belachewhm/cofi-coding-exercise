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
public class StockServiceImplTest {
	@Mock
	private List<StockRecord> mockStockRecords;

	@InjectMocks
	private StockServiceImpl stockServiceImpl;

	private Double DELTA = 0.001;
	private List<StockRecord> returnRecords;

	@Before
	public void setup() {
		returnRecords = new ArrayList<StockRecord>();
		Mockito.when(mockStockRecords.stream()).thenReturn(returnRecords.stream());
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
		String average_open = stockServiceImpl.averageMonthlyOpenAndClose("TEST_TICKER").get("TEST_TICKER")
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
		String average_close = stockServiceImpl.averageMonthlyOpenAndClose("TEST_TICKER").get("TEST_TICKER")
				.get((new SimpleDateFormat("YYYY-MM").format(new Date()))).get("average_close");
		Assert.assertEquals((close_1 + close_2 + close_3) / 3, Double.parseDouble(average_close), DELTA * 4);
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
				Double.parseDouble(stockServiceImpl.maxDailyProfit("TEST_TICKER").get("TEST_TICKER").get("profit")),
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
				stockServiceImpl.maxDailyProfit("TEST_TICKER").get("TEST_TICKER").get("date"));
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
		Assert.assertEquals("2",
				stockServiceImpl.biggestLoser(new String[] { "TEST_TICKER_1" }).get("TEST_TICKER_1").toString());
	}

	@Test
	public void testAverageVolume_1() {
		Double volume_1 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER_1");
				setVolume(volume_1);
			}
		});
		Assert.assertEquals(stockServiceImpl.averageVolume("TEST_TICKER_1", returnRecords), volume_1, DELTA);
	}

	@Test
	public void testAverageVolume_2() {
		Double volume_1 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double volume_2 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double volume_3 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setVolume(volume_1);
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setVolume(volume_2);
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setVolume(volume_3);
			}
		});
		Assert.assertEquals(stockServiceImpl.averageVolume("TEST_TICKER", returnRecords),
				((volume_1 + volume_2 + volume_3) / 3), DELTA);
	}

	@Test
	public void testAverageVolume_3() {
		Double volume_1 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double volume_2 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double volume_3 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER_1");
				setVolume(volume_1);
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER_1");
				setVolume(volume_2);
			}
		});
		returnRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER_2");
				setVolume(volume_3);
			}
		});
		Assert.assertEquals(stockServiceImpl.averageVolume("TEST_TICKER_1", returnRecords), ((volume_1 + volume_2) / 2),
				DELTA);
	}

	@Test
	public void testTruncateDoubleToPrice() {
		Assert.assertEquals(00.00, stockServiceImpl.truncateDoubleToPrice(00.00012345678), DELTA);
		Assert.assertEquals(00.00, stockServiceImpl.truncateDoubleToPrice(00.00123456789), DELTA);
		Assert.assertEquals(00.01, stockServiceImpl.truncateDoubleToPrice(00.01234567890), DELTA);
		Assert.assertEquals(00.12, stockServiceImpl.truncateDoubleToPrice(00.12345678900), DELTA);
		Assert.assertEquals(01.23, stockServiceImpl.truncateDoubleToPrice(01.23456789000), DELTA);
		Assert.assertEquals(12.35, stockServiceImpl.truncateDoubleToPrice(12.34567890000), DELTA);
		Assert.assertEquals(23.46, stockServiceImpl.truncateDoubleToPrice(23.45678900000), DELTA);
		Assert.assertEquals(34.57, stockServiceImpl.truncateDoubleToPrice(34.56789000000), DELTA);
		Assert.assertEquals(45.68, stockServiceImpl.truncateDoubleToPrice(45.67890000000), DELTA);
		Assert.assertEquals(56.79, stockServiceImpl.truncateDoubleToPrice(56.78900000000), DELTA);
		Assert.assertEquals(67.89, stockServiceImpl.truncateDoubleToPrice(67.89000000000), DELTA);
		Assert.assertEquals(78.90, stockServiceImpl.truncateDoubleToPrice(78.90000000000), DELTA);
		Assert.assertEquals(89.00, stockServiceImpl.truncateDoubleToPrice(89.00000000000), DELTA);
	}

	@After
	public void teardown() {
		returnRecords = null;
	}
}