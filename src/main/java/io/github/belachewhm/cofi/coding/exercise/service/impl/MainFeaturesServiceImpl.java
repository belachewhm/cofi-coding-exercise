package io.github.belachewhm.cofi.coding.exercise.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;
import io.github.belachewhm.cofi.coding.exercise.service.MainFeaturesService;
import io.github.belachewhm.cofi.coding.exercise.util.Util;

@Service
public class MainFeaturesServiceImpl implements MainFeaturesService {
	@Autowired
	private List<StockRecord> stockRecords;

	@Override
	public Map<String, Map<String, Map<String, String>>> getAllAverageMonthlyOpenAndCloses() {
		List<String> tickers = new ArrayList<String>() {
			{
				add("COF");
				add("GOOGL");
				add("MSFT");
			}
		};
		return this.getAverageMonthlyOpenAndClose(tickers);
	}

	@Override
	public Map<String, Map<String, Map<String, String>>> getAverageMonthlyOpenAndClose(String ticker) {
		return getAverageMonthlyOpenAndClose(new String[] { ticker });
	}

	@Override
	public Map<String, Map<String, Map<String, String>>> getAverageMonthlyOpenAndClose(String[] tickers) {
		return getAverageMonthlyOpenAndClose(Arrays.asList(tickers));
	}

	@Override
	public Map<String, Map<String, Map<String, String>>> getAverageMonthlyOpenAndClose(List<String> tickers)
	{
		Map<String, Map<String, Map<String, String>>> resultMap = new LinkedHashMap<String, Map<String, Map<String, String>>>();
				
		if(!tickers.stream().allMatch(ticker -> stockRecords.stream().anyMatch(record -> record.getTicker().equals(ticker))))
		{
			return resultMap;
		}
		
		for (String ticker : tickers) {
			// Use a TreeMap to end up with a map ordered by Key (month, in this
			// case)
			resultMap.put(ticker, new TreeMap<String, Map<String, String>>() {
				{
					stockRecords.stream().filter(record -> record.getTicker().equalsIgnoreCase(ticker))
							.collect(Collectors.groupingBy(StockRecord::getMonthAndYear))
							.forEach((k, v) -> put(k, new LinkedHashMap<String, String>() {
								{
									String average_open = (new DecimalFormat("#.00")).format(Util.truncateDoubleToPrice(
											v.stream().collect(Collectors.averagingDouble(StockRecord::getOpen))));
									String average_close = (new DecimalFormat("#.00"))
											.format(Util.truncateDoubleToPrice(v.stream()
													.collect(Collectors.averagingDouble(StockRecord::getClose))));
									put("average_open", average_open);
									put("average_close", average_close);
								}
							}));
				}
			});
		}
		return resultMap;
	}
}
