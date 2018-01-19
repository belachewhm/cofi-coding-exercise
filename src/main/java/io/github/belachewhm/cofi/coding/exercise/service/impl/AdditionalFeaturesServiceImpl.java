package io.github.belachewhm.cofi.coding.exercise.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;
import io.github.belachewhm.cofi.coding.exercise.service.AdditionalFeaturesService;
import io.github.belachewhm.cofi.coding.exercise.util.Util;

@Service
public class AdditionalFeaturesServiceImpl implements AdditionalFeaturesService {
	@Autowired
	private List<StockRecord> stockRecords;

	@Override
	public Map<String, Map<String, String>> getAllMaxDailyProfits()
	{
		List<String> tickers = new ArrayList<String>() {
			{
				add("COF");
				add("GOOGL");
				add("MSFT");
			}
		};
		return this.getMaxDailyProfit(tickers);
	}
	
	@Override
	public Map<String, Map<String, String>> getMaxDailyProfit(String ticker) {
		return getMaxDailyProfit(new String[] { ticker });
	}

	@Override
	public Map<String, Map<String, String>> getMaxDailyProfit(String[] tickers) {
		return getMaxDailyProfit(Arrays.asList(tickers));
	}

	@Override
	public Map<String, Map<String, String>> getMaxDailyProfit(List<String> tickers) {
		Map<String, Map<String, String>> resultMap = new LinkedHashMap<String, Map<String, String>>();
		
		if(!tickers.stream().allMatch(ticker -> stockRecords.stream().anyMatch(record -> record.getTicker().equals(ticker))))
		{
			return resultMap;
		}
		
		for (String ticker : tickers) {
			resultMap.put(ticker, new LinkedHashMap<String, String>() {
				{
					Optional<StockRecord> stockRecord = stockRecords.stream()
							.filter(record -> record.getTicker().equalsIgnoreCase(ticker))
							.max(Comparator.comparingDouble(StockRecord::calculateMaximumDailyProfit));

					put("date", (new SimpleDateFormat("yyyy-MM-dd")).format(stockRecord.get().getDate()));
					put("profit", (new DecimalFormat("#.00"))
							.format(Util.truncateDoubleToPrice(stockRecord.get().calculateMaximumDailyProfit())));
				}
			});
		}
		return resultMap;
	}

	@Override
	public Map<String, String> getAllAverageVolumes()
	{
		List<String> tickers = new ArrayList<String>() {
			{
				add("j");
				add("GOOGL");
				add("MSFT");
			}
		};
		return this.getAverageVolume(tickers);
	}
	
	@Override
	public Map<String, String> getAverageVolume(String ticker) {
		return getAverageVolume(new String[] { ticker });
	}

	@Override
	public Map<String, String> getAverageVolume(String[] tickers) {
		return getAverageVolume(Arrays.asList(tickers));
	}

	@Override
	public Map<String, String> getAverageVolume(List<String> tickers)
	{
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		if(!tickers.stream().allMatch(ticker -> stockRecords.stream().anyMatch(record -> record.getTicker().equals(ticker))))
		{
			return resultMap;
		}
		
		for (String ticker : tickers) {
			resultMap.put(ticker, (new DecimalFormat("#.00")).format(Util.averageVolume(ticker, stockRecords)));
		}
		return resultMap;
	}

	@Override
	public Map<String, Map<String, String>> getAllBusyDays()
	{
		List<String> tickers = new ArrayList<String>() {
			{
				add("COF");
				add("GOOGL");
				add("MSFT");
			}
		};
		return this.getBusyDay(tickers);
	}
	
	@Override
	public Map<String, Map<String, String>> getBusyDay(String ticker) {
		return getBusyDay(new String[] { ticker });
	}

	@Override
	public Map<String, Map<String, String>> getBusyDay(String[] tickers) {
		return getBusyDay(Arrays.asList(tickers));
	}

	@Override
	public Map<String, Map<String, String>> getBusyDay(List<String> tickers)
	{
		Map<String, Map<String, String>> resultMap = new LinkedHashMap<String, Map<String, String>>();
		
		if(!tickers.stream().allMatch(ticker -> stockRecords.stream().anyMatch(record -> record.getTicker().equals(ticker))))
		{
			return resultMap;
		}
		
		for (String ticker : tickers) {
			Double averageVolume = Util.averageVolume(ticker, stockRecords);
			resultMap.put(ticker, new LinkedHashMap<String, String>() {
				{
					stockRecords.stream()
							.filter(record -> record.getTicker().equalsIgnoreCase(ticker)
									&& record.getVolume() > (averageVolume * 1.1))
							.collect(Collectors.toMap(StockRecord::getDate, record -> record.getVolume()))
							.forEach((k, v) -> {
								put(new SimpleDateFormat("YYYY-MM-dd").format(k),
										(new DecimalFormat("#.00")).format(Util.truncateDoubleToPrice(v)));
							});
				}
			});
		}
		return resultMap;
	}
	
	@Override
	public Map<String, Integer> getBiggestLoser() {
		Map<String, Integer> mapOfLoserCount = new LinkedHashMap<String, Integer>();
		stockRecords.stream().filter(record -> record.isLoser()).collect(Collectors.groupingBy(StockRecord::getTicker))
				.forEach((k, v) -> {
					mapOfLoserCount.put(k, v.size());
				});

		Entry<String, Integer> biggestLoser = Collections.max(mapOfLoserCount.entrySet(),
				Comparator.comparingInt(Map.Entry::getValue));

		Map<String, Integer> biggestLoserMap = new LinkedHashMap<String, Integer>() {
			{
				put((String) biggestLoser.getKey(), (Integer) biggestLoser.getValue());
			}
		};
		return biggestLoserMap;
	}
}
