///**
// * Moving this into a seperate class because because it will need to be refactored later.
// * Having to use PowerMockRunner in this project highlightes a problem with my class/method design and/or
// * dependency-injection model in the StockServiceImpl.
// * 
// * Also, EclEmma does not accurately report code coverage measured by PowerMockRunner, so it was more practical for
// * code coverage purposes to isolate this test into it's own class. I didn't want my use of PowerMockRunner for this one test
// * to skew code coverage numbers as measured locally.
// * 
// * TODO: Come back and fix this later
// */
//
//package io.github.belachewhm.cofi.coding.exercise.service.impl;
//
//import static org.mockito.Matchers.anyObject;
//import static org.mockito.Matchers.anyString;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;
//import io.github.belachewhm.cofi.coding.exercise.util.Util;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(AdditionalFeaturesServiceImplTest.class)
//public class AdditionalFeaturesServiceImplTest_PowerMockito {
//	@Mock
//	private List<StockRecord> mockStockRecords;
//
//	@InjectMocks
//	private AdditionalFeaturesServiceImpl additionalFeaturesServiceImpl;
//
//	private Double DELTA = 0.001;
//	private List<StockRecord> returnRecords;
//
//	@Before
//	public void setup() {
//		PowerMockito.mockStatic(AdditionalFeaturesServiceImplTest.class);
//		returnRecords = new ArrayList<StockRecord>();
//		Mockito.when(mockStockRecords.stream()).thenReturn(returnRecords.stream());
//	}
//
//	@Test
//	public void testBusyDay() {
//		Date date = new Date();
//		returnRecords.add(new StockRecord() {
//			{
//				setTicker("TEST_TICKER");
//				setVolume(100.0);
//				setDate(date);
//			}
//		});
//		returnRecords.add(new StockRecord() {
//			{
//				setTicker("TEST_TICKER");
//				setVolume(0.0);
//				setDate(new Date(0));
//			}
//		});
//		PowerMockito.when(Util.averageVolume(anyString(), anyObject())).thenReturn(50.0);
//		Map<String, Map<String, String>> busyDayMap = additionalFeaturesServiceImpl.busyDay("TEST_TICKER");
//		Assert.assertEquals("100.00",
//				busyDayMap.get("TEST_TICKER").get(new SimpleDateFormat("YYYY-MM-dd").format(date)));
//	}
//
//	@Test
//	public void testAverageVolume() {
//		Double volume_1 = Double.parseDouble(RandomStringUtils.random(10, false, true));
//		Double volume_2 = Double.parseDouble(RandomStringUtils.random(10, false, true));
//		Double volume_3 = Double.parseDouble(RandomStringUtils.random(10, false, true));
//		Double averageVolume = (volume_1 + volume_2 + volume_3) / 3;
//		mockStockRecords.add(new StockRecord() {
//			{
//				setTicker("TEST_TICKER");
//				setVolume(volume_1);
//			}
//		});
//		mockStockRecords.add(new StockRecord() {
//			{
//				setTicker("TEST_TICKER");
//				setVolume(volume_2);
//			}
//		});
//		mockStockRecords.add(new StockRecord() {
//			{
//				setTicker("TEST_TICKER");
//				setVolume(volume_3);
//			}
//		});
//		PowerMockito.when(Util.averageVolume(anyString(), anyObject())).thenReturn(averageVolume);
//		Assert.assertEquals(averageVolume,
//				Double.parseDouble(additionalFeaturesServiceImpl.averageVolume("TEST_TICKER").get("TEST_TICKER")), DELTA * 4);
//	}
//
//	@After
//	public void teardown() {
//		returnRecords = null;
//	}
//}
