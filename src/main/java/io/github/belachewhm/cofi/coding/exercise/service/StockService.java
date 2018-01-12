package io.github.belachewhm.cofi.coding.exercise.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;

@Service
public class StockService
{
	@Autowired
	private List<StockRecord> stockRecords;
	
	/**
	 * 
	 * @param ticker a single ticker symbol
	 * @return
	 */
	public Map<String, Map<String, Map<String, Double>>> averageMonthlyOpenAndClose(String ticker)
	{	
		List<String> tickers = new ArrayList<String>()
		{{
			add(ticker);
		}};
		return averageMonthlyOpenAndClose(tickers);
	}
	
	/**
	 * 
	 * @param tickers a list of ticker symbols
	 * @return
	 */
	public Map<String, Map<String, Map<String, Double>>> averageMonthlyOpenAndClose(List<String> tickers)
	{	
		return averageMonthlyOpenAndClose(tickers.toArray(new String[tickers.size()]));
	}
	
	/**
	 * 
	 * @param tickers an array of ticker symbols ex. {"COF","GOOGL","MSFT"}
	 * @return
	 */
	public Map<String, Map<String, Map<String, Double>>> averageMonthlyOpenAndClose(String[] tickers)
	{	
		Map<String, Map<String, Map<String, Double>>> recordsByTicker = new HashMap<String, Map<String, Map<String, Double>>>();
		for(String ticker : tickers)
		{
			recordsByTicker.put(ticker, new HashMap<String, Map<String, Double>>()
			{{
				Map<String, List<StockRecord>> recordsByMonth = stockRecords.stream().filter(record->record.getTicker().equalsIgnoreCase(ticker)).collect(Collectors.groupingBy(StockRecord::getMonthAndYear));
				for(String month : recordsByMonth.keySet())
				{
					Double average_open = truncatePrice(recordsByMonth.get(month).stream().collect(Collectors.averagingDouble(StockRecord::getOpen)));
					Double average_close = truncatePrice(recordsByMonth.get(month).stream().collect(Collectors.averagingDouble(StockRecord::getClose)));
					
					put(month, new HashMap<String, Double>()
					{{
						put("average_open", average_open);
						put("average_close", average_close);
					}});
				}
			}});
		}
		return recordsByTicker;
	}
	
	public String maxDailyProfit() {
		return String.valueOf(stockRecords.size());
	}

	public String busyDay() {
		return String.valueOf(stockRecords.size());
	}

	public String biggestLoser() {
		return String.valueOf(stockRecords.size());
	}
	
	/**
	 * important to set scale and round AFTER all calculations have been completed, so as not to lose precision
	 * 
	 * @param value
	 * @return
	 */
	private Double truncatePrice(Double value)
	{		
		return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
}