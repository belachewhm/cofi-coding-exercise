package io.github.belachewhm.cofi.coding.exercise.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;

public class UtilTest {
	List<StockRecord> testRecords;

	private static final Double DELTA = 0.001;

	@Before
	public void setup() {
		testRecords = new ArrayList<StockRecord>();
	}

	@Test
	public void testTruncateDoubleToPrice() {
		Assert.assertEquals(00.00, Util.truncateDoubleToPrice(00.00012345678), DELTA);
		Assert.assertEquals(00.00, Util.truncateDoubleToPrice(00.00123456789), DELTA);
		Assert.assertEquals(00.01, Util.truncateDoubleToPrice(00.01234567890), DELTA);
		Assert.assertEquals(00.12, Util.truncateDoubleToPrice(00.12345678900), DELTA);
		Assert.assertEquals(01.23, Util.truncateDoubleToPrice(01.23456789000), DELTA);
		Assert.assertEquals(12.35, Util.truncateDoubleToPrice(12.34567890000), DELTA);
		Assert.assertEquals(23.46, Util.truncateDoubleToPrice(23.45678900000), DELTA);
		Assert.assertEquals(34.57, Util.truncateDoubleToPrice(34.56789000000), DELTA);
		Assert.assertEquals(45.68, Util.truncateDoubleToPrice(45.67890000000), DELTA);
		Assert.assertEquals(56.79, Util.truncateDoubleToPrice(56.78900000000), DELTA);
		Assert.assertEquals(67.89, Util.truncateDoubleToPrice(67.89000000000), DELTA);
		Assert.assertEquals(78.90, Util.truncateDoubleToPrice(78.90000000000), DELTA);
		Assert.assertEquals(89.00, Util.truncateDoubleToPrice(89.00000000000), DELTA);
	}

	@Test
	public void testAverageVolume_1() {
		Double volume_1 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		testRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER_1");
				setVolume(volume_1);
			}
		});
		Assert.assertEquals(Util.averageVolume("TEST_TICKER_1", testRecords), volume_1, DELTA);
	}

	@Test
	public void testAverageVolume_2() {
		Double volume_1 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double volume_2 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double volume_3 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		testRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setVolume(volume_1);
			}
		});
		testRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setVolume(volume_2);
			}
		});
		testRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER");
				setVolume(volume_3);
			}
		});
		Assert.assertEquals(Util.averageVolume("TEST_TICKER", testRecords), ((volume_1 + volume_2 + volume_3) / 3),
				DELTA);
	}

	@Test
	public void testAverageVolume_3() {
		Double volume_1 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double volume_2 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		Double volume_3 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		testRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER_1");
				setVolume(volume_1);
			}
		});
		testRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER_1");
				setVolume(volume_2);
			}
		});
		testRecords.add(new StockRecord() {
			{
				setTicker("TEST_TICKER_2");
				setVolume(volume_3);
			}
		});
		Assert.assertEquals(Util.averageVolume("TEST_TICKER_1", testRecords), ((volume_1 + volume_2) / 2), DELTA);
	}
}
