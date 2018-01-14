package io.github.belachewhm.cofi.coding.exercise.service.impl;

import java.util.ArrayList;
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
import io.github.belachewhm.cofi.coding.exercise.model.impl.StockRecordImpl;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceImplTest {
	@Mock
	private List<StockRecord> mockStockRecords;

	@InjectMocks
	private StockServiceImpl stockServiceImpl;

	private Double DELTA = 0.001;
	private Double volume_1;
	private Double volume_2;
	private Double volume_3;

	@Before
	public void setup() {
		volume_1 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		volume_2 = Double.parseDouble(RandomStringUtils.random(10, false, true));
		volume_3 = Double.parseDouble(RandomStringUtils.random(10, false, true));
	}

	@Test
	public void test_averageVolume_1() {
		List<StockRecord> mockRecord = new ArrayList<StockRecord>() {
			{
				add(new StockRecordImpl() {
					{
						this.setTicker("COF");
						this.setVolume(volume_1);
					}
				});
				add(new StockRecordImpl() {
					{
						this.setTicker("MSFT");
						this.setVolume(volume_2);
					}
				});
				add(new StockRecordImpl() {
					{
						this.setTicker("GOOGL");
						this.setVolume(volume_3);
					}
				});
			}
		};
		Mockito.when(mockStockRecords.stream()).thenReturn(mockRecord.stream());
		Assert.assertEquals(stockServiceImpl.averageVolume("COF"), volume_1, DELTA);

		Mockito.when(mockStockRecords.stream()).thenReturn(mockRecord.stream());
		Assert.assertEquals(stockServiceImpl.averageVolume("MSFT"), volume_2, DELTA);

		Mockito.when(mockStockRecords.stream()).thenReturn(mockRecord.stream());
		Assert.assertEquals(stockServiceImpl.averageVolume("GOOGL"), volume_3, DELTA);
	}

	@Test
	public void test_averageVolume_2() {
		List<StockRecord> mockRecord = new ArrayList<StockRecord>() {
			{
				add(new StockRecordImpl() {
					{
						this.setTicker("COF");
						this.setVolume(volume_1);
					}
				});
				add(new StockRecordImpl() {
					{
						this.setTicker("COF");
						this.setVolume(volume_2);
					}
				});
				add(new StockRecordImpl() {
					{
						this.setTicker("COF");
						this.setVolume(volume_3);
					}
				});
			}
		};
		Mockito.when(mockStockRecords.stream()).thenReturn(mockRecord.stream());
		Assert.assertEquals(stockServiceImpl.averageVolume("COF"), ((volume_1 + volume_2 + volume_3) / 3), DELTA);
	}

	@Test
	public void test_averageVolume_3() {
		List<StockRecord> mockRecord = new ArrayList<StockRecord>() {
			{
				add(new StockRecordImpl() {
					{
						this.setTicker("COF");
						this.setVolume(volume_1);
					}
				});
				add(new StockRecordImpl() {
					{
						this.setTicker("COF");
						this.setVolume(volume_2);
					}
				});
				add(new StockRecordImpl() {
					{
						this.setTicker("GOOGL");
						this.setVolume(volume_3);
					}
				});
			}
		};
		Mockito.when(mockStockRecords.stream()).thenReturn(mockRecord.stream());
		Assert.assertEquals(stockServiceImpl.averageVolume("COF"), ((volume_1 + volume_2) / 2), DELTA);
	}

	@After
	public void teardown() {
		volume_1 = null;
		volume_2 = null;
		volume_3 = null;
	}
}
