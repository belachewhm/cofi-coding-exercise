/**
 * Moving this into a seperate class because because it will need to be refactored later.
 * Having to use PowerMockRunner in this project highlightes a problem with my class/method design and/or
 * dependency-injection model in the StockServiceImpl.
 * 
 * Also, EclEmma does not accurately report code coverage measured by PowerMockRunner, so it was more practical for
 * code coverage purposes to isolate this test into it's own class. I didn't want my use of PowerMockRunner for this one test
 * to skew code coverage numbers as measured locally.
 * 
 * TODO: Come back and fix this later
 */

package io.github.belachewhm.cofi.coding.exercise.service.impl;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;
import io.github.belachewhm.cofi.coding.exercise.model.impl.StockRecordImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(StockServiceImpl.class)
public class StockServiceImplTest_busyDay {
	@Mock
	private List<StockRecord> mockStockRecords;

	@InjectMocks
	private StockServiceImpl stockServiceImpl;

	private List<StockRecord> returnRecords;

	@Before
	public void setup() {
		returnRecords = new ArrayList<StockRecord>();
		Mockito.when(mockStockRecords.stream()).thenReturn(returnRecords.stream());
	}

	@Test
	public void testBusyDay() {
		Date date = new Date();
		returnRecords.add(new StockRecordImpl() {
			{
				this.setTicker("TEST_TICKER");
				this.setVolume(100.0);
				this.setDate(date);
			}
		});
		returnRecords.add(new StockRecordImpl() {
			{
				this.setTicker("TEST_TICKER");
				this.setVolume(0.0);
				this.setDate(new Date(0));
			}
		});

		PowerMockito.mockStatic(StockServiceImpl.class);
		PowerMockito.when(StockServiceImpl.averageVolume(anyString(), anyObject())).thenReturn(50.0);
		Map<String, Map<String, String>> busyDayMap = stockServiceImpl.busyDay("TEST_TICKER");
		Assert.assertEquals("100.00",
				busyDayMap.get("TEST_TICKER").get(new SimpleDateFormat("YYYY-MM-dd").format(date)));
	}

	@After
	public void teardown() {
		returnRecords = null;
	}
}
