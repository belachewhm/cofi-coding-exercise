package io.github.belachewhm.cofi.coding.exercise.service;

import java.util.List;
import java.util.Map;

public interface AdditionalFeaturesService
{
	Map<String, Map<String, String>> getAllMaxDailyProfits();
	
	Map<String, Map<String, String>> getMaxDailyProfit(String ticker);

	Map<String, Map<String, String>> getMaxDailyProfit(String[] tickers);

	Map<String, Map<String, String>> getMaxDailyProfit(List<String> tickers);

	Map<String, String> getAllAverageVolumes();
	
	Map<String, String> getAverageVolume(String ticker);

	Map<String, String> getAverageVolume(String[] tickers);

	Map<String, String> getAverageVolume(List<String> tickers);

	Map<String, Map<String, String>> getAllBusyDays();
	
	Map<String, Map<String, String>> getBusyDay(String ticker);

	Map<String, Map<String, String>> getBusyDay(String[] tickers);

	Map<String, Map<String, String>> getBusyDay(List<String> tickers);

	Map<String, Integer> getBiggestLoser();
}
