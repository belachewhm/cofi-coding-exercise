package io.github.belachewhm.cofi.coding.exercise.model.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;

public class StockRecordImplTest {
	private double DELTA = 0.001;

	private BeanTester beanTester;
	private StockRecord record;
	private Double high;
	private Double low;
	private Double close;
	private Double open;

	@Before
	public void setup() {
		beanTester = new BeanTester();
		record = new StockRecord();
	}

	@Test
	public void testBean() {
		beanTester.testBean(StockRecord.class);
	}

	@Test
	public void testConstructor() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String TEST_TICKER = RandomStringUtils.random(10, true, false);
		String TEST_DATE = sdf.format(new Date());
		String TEST_OPEN = RandomStringUtils.random(10, false, true);
		String TEST_HIGH = RandomStringUtils.random(10, false, true);
		String TEST_LOW = RandomStringUtils.random(10, false, true);
		String TEST_CLOSE = RandomStringUtils.random(10, false, true);
		String TEST_VOLUME = RandomStringUtils.random(10, false, true);
		String TEST_EX_DIVIDEND = RandomStringUtils.random(10, false, true);
		String TEST_SPLIT_RATIO = RandomStringUtils.random(10, false, true);
		String TEST_ADJ_OPEN = RandomStringUtils.random(10, false, true);
		String TEST_ADJ_HIGH = RandomStringUtils.random(10, false, true);
		String TEST_ADJ_LOW = RandomStringUtils.random(10, false, true);
		String TEST_ADJ_CLOSE = RandomStringUtils.random(10, false, true);
		String TEST_ADJ_VOLUME = RandomStringUtils.random(10, false, true);
		record = new StockRecord(TEST_TICKER, TEST_DATE, TEST_OPEN, TEST_HIGH, TEST_LOW, TEST_CLOSE, TEST_VOLUME,
				TEST_EX_DIVIDEND, TEST_SPLIT_RATIO, TEST_ADJ_OPEN, TEST_ADJ_HIGH, TEST_ADJ_LOW, TEST_ADJ_CLOSE,
				TEST_ADJ_VOLUME);
		Assert.assertEquals(TEST_TICKER, record.getTicker());
		Assert.assertEquals(sdf.parse(TEST_DATE), record.getDate());
		Assert.assertEquals(Double.parseDouble(TEST_OPEN), record.getOpen(), DELTA);
		Assert.assertEquals(Double.parseDouble(TEST_HIGH), record.getHigh(), DELTA);
		Assert.assertEquals(Double.parseDouble(TEST_LOW), record.getLow(), DELTA);
		Assert.assertEquals(Double.parseDouble(TEST_CLOSE), record.getClose(), DELTA);
		Assert.assertEquals(Double.parseDouble(TEST_VOLUME), record.getVolume(), DELTA);
		Assert.assertEquals(Double.parseDouble(TEST_EX_DIVIDEND), record.getEx_dividend(), DELTA);
		Assert.assertEquals(Double.parseDouble(TEST_SPLIT_RATIO), record.getSplit_ratio(), DELTA);
		Assert.assertEquals(Double.parseDouble(TEST_ADJ_OPEN), record.getAdj_open(), DELTA);
		Assert.assertEquals(Double.parseDouble(TEST_ADJ_HIGH), record.getAdj_high(), DELTA);
		Assert.assertEquals(Double.parseDouble(TEST_ADJ_LOW), record.getAdj_low(), DELTA);
		Assert.assertEquals(Double.parseDouble(TEST_ADJ_CLOSE), record.getAdj_close(), DELTA);
		Assert.assertEquals(Double.parseDouble(TEST_ADJ_VOLUME), record.getAdj_volume(), DELTA);
	}

	@Test
	public void testGetMonthAndYear() {
		Date date = new Date();
		record.setDate(date);
		Assert.assertEquals(new SimpleDateFormat("yyyy-MM").format(date), record.getMonthAndYear());
	}

	@Test
	public void testCalculateMaximumDailyProfit() {
		high = Double.parseDouble(RandomStringUtils.random(10, false, true));
		low = Double.parseDouble(RandomStringUtils.random(10, false, true));
		record.setHigh(high);
		record.setLow(low);
		Assert.assertEquals(high - low, record.calculateMaximumDailyProfit(), DELTA);
	}

	@Test
	public void testIsLoser_assertTrue() {
		close = Double.parseDouble(RandomStringUtils.random(8, false, true));
		open = Double.parseDouble(RandomStringUtils.random(10, false, true));
		record.setClose(close);
		record.setOpen(open);
		Assert.assertTrue(record.isLoser());
	}

	@Test
	public void testIsLoser_assertFalse() {
		close = Double.parseDouble(RandomStringUtils.random(10, false, true));
		open = Double.parseDouble(RandomStringUtils.random(8, false, true));
		record.setClose(close);
		record.setOpen(open);
		Assert.assertFalse(record.isLoser());
	}

	@After
	public void teardown() {
		beanTester = null;
		record = null;
	}
}
