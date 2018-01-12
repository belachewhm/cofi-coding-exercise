package io.github.belachewhm.cofi.coding.exercise.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public interface StockService {
	Map<String, Map<String, Map<String, String>>> averageMonthlyOpenAndClose(String ticker);
	Map<String, Map<String, Map<String, String>>> averageMonthlyOpenAndClose(String[] tickers);
	Map<String, Map<String, Map<String, String>>> averageMonthlyOpenAndClose(List<String> tickers);

	Map<String, Map<String, String>> maxDailyProfit(String ticker);
	Map<String, Map<String, String>> maxDailyProfit(String[] tickers);
	Map<String, Map<String, String>> maxDailyProfit(List<String> tickers);

	// double averageVolume(String ticker);
	// double averageVolume(String[] tickers);
	// double averageVolume(List<String> tickers);
	
	Map<String, Map<String, String>> busyDay(String ticker);
	Map<String, Map<String, String>> busyDay(String[] tickers);
	Map<String, Map<String, String>> busyDay(List<String> tickers);

	Entry<String, Integer> biggestLoser(String[] tickers);
	Entry<String, Integer> biggestLoser(List<String> tickers);
}
