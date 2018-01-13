package io.github.belachewhm.cofi.coding.exercise.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;
import io.github.belachewhm.cofi.coding.exercise.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	@Autowired
	private List<StockRecord> stockRecords;

	/**
	 * 
	 * @param ticker
	 *            a single ticker symbol
	 * @return
	 */
	@SuppressWarnings("serial")
	public Map<String, Map<String, Map<String, String>>> averageMonthlyOpenAndClose(String ticker) {
		return averageMonthlyOpenAndClose(new ArrayList<String>() {
			{
				add(ticker);
			}
		});
	}

	/**
	 * 
	 * @param tickers
	 *            an array of ticker symbols ex. {"COF","GOOGL","MSFT"}
	 * @return
	 */
	public Map<String, Map<String, Map<String, String>>> averageMonthlyOpenAndClose(String[] tickers) {
		return averageMonthlyOpenAndClose(Arrays.asList(tickers));
	}

	/**
	 * 
	 * @param tickers
	 *            a list of ticker symbols
	 * @return
	 */
	@SuppressWarnings("serial")
	public Map<String, Map<String, Map<String, String>>> averageMonthlyOpenAndClose(List<String> tickers) {
		Map<String, Map<String, Map<String, String>>> resultMap = new LinkedHashMap<String, Map<String, Map<String, String>>>();
		for (String ticker : tickers) {
			resultMap.put(ticker, new LinkedHashMap<String, Map<String, String>>() {
				{
					stockRecords.stream().filter(record -> record.getTicker().equalsIgnoreCase(ticker))
							.collect(Collectors.groupingBy(StockRecord::getMonthAndYear))
							.forEach((k, v) -> put(k, new LinkedHashMap<String, String>() {
								{
									String average_open = (new DecimalFormat("#.00")).format(truncateDoubleToPrice(
											v.stream().collect(Collectors.averagingDouble(StockRecord::getOpen))));
									String average_close = (new DecimalFormat("#.00")).format(truncateDoubleToPrice(
											v.stream().collect(Collectors.averagingDouble(StockRecord::getClose))));
									put("average_open", average_open);
									put("average_close", average_close);
								}
							}));
				}
			});
		}
		return resultMap;
	}

	/**
	 * 
	 * @param ticker
	 *            a single ticker symbol
	 * @return
	 */
	@SuppressWarnings("serial")
	public Map<String, Map<String, String>> maxDailyProfit(String ticker) {
		return maxDailyProfit(new ArrayList<String>() {
			{
				add(ticker);
			}
		});
	}

	/**
	 * 
	 * @param tickers
	 *            an array of ticker symbols ex. {"COF","GOOGL","MSFT"}
	 * @return
	 */
	public Map<String, Map<String, String>> maxDailyProfit(String[] tickers) {
		return maxDailyProfit(Arrays.asList(tickers));
	}

	/**
	 * 
	 * @param tickers
	 *            a list of ticker symbols
	 * @return
	 */
	@SuppressWarnings("serial")
	public Map<String, Map<String, String>> maxDailyProfit(List<String> tickers) {
		Map<String, Map<String, String>> resultMap = new LinkedHashMap<String, Map<String, String>>();
		for (String ticker : tickers) {
			resultMap.put(ticker, new LinkedHashMap<String, String>() {
				{
					Optional<StockRecord> stockRecord = stockRecords.stream()
							.filter(record -> record.getTicker().equalsIgnoreCase(ticker))
							.max(Comparator.comparingDouble(StockRecord::calculateMaximumDailyProfit));
					put("date", (new SimpleDateFormat("yyyy-MM-dd")).format(stockRecord.get().getDate()));
					put("profit", (new DecimalFormat("#.00"))
							.format(truncateDoubleToPrice(stockRecord.get().calculateMaximumDailyProfit())));
				}
			});
		}
		return resultMap;
	}

	/**
	 * 
	 * @param tickers
	 *            an array of ticker symbols ex. {"COF","GOOGL","MSFT"}
	 * @return
	 */
	public Map<String, Integer> biggestLoser(String[] tickers) {
		return biggestLoser(Arrays.asList(tickers));
	}

	/**
	 * 
	 * @param tickers
	 *            a list of ticker symbols
	 * @return
	 */
	@SuppressWarnings("serial")
	public Map<String, Integer> biggestLoser(List<String> tickers) {
		Map<String, Integer> mapOfLoserCount = new LinkedHashMap<String, Integer>();
		stockRecords.stream().filter(record -> record.isLoser()).collect(Collectors.groupingBy(StockRecord::getTicker))
				.forEach((k, v) -> {
					mapOfLoserCount.put(k, v.size());
				});
		
		Entry<String, Integer> biggestLoser = Collections.max(mapOfLoserCount.entrySet(), Comparator.comparingInt(Map.Entry::getValue));
		Map<String, Integer> biggestLoserMap = new LinkedHashMap<String, Integer>(){{
			put((String) biggestLoser.getKey(), (Integer) biggestLoser.getValue());
		}};
		return biggestLoserMap;
	}

	/**
	 * 
	 * @param ticker
	 *            a single ticker symbol
	 * @return
	 */
	@SuppressWarnings("serial")
	public Map<String, Map<String, String>> busyDay(String ticker) {
		return busyDay(new ArrayList<String>() {
			{
				this.add(ticker);
			}
		});
	}

	/**
	 * 
	 * @param tickers
	 *            an array of ticker symbols ex. {"COF","GOOGL","MSFT"}
	 * @return
	 */
	public Map<String, Map<String, String>> busyDay(String[] tickers) {
		return busyDay(Arrays.asList(tickers));
	}

	/**
	 * 
	 * @param tickers
	 *            a list of ticker symbols
	 * @return
	 */
	public Map<String, Map<String, String>> busyDay(List<String> tickers) {
		Map<String, Map<String, String>> resultMap = new LinkedHashMap<String, Map<String, String>>();
		for (String ticker : tickers) {
			// TODO: Add implementation
		}
		return resultMap;
	}

	/**
	 * important to set scale and round AFTER all calculations have been
	 * completed, so as not to lose precision
	 * 
	 * @param value
	 * @return
	 */
	private Double truncateDoubleToPrice(Double value) {
		return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
}