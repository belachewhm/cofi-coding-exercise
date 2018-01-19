package io.github.belachewhm.cofi.coding.exercise.service;

import java.util.List;
import java.util.Map;

public interface MainFeaturesService
{
	Map<String, Map<String, Map<String, String>>> getAllAverageMonthlyOpenAndCloses();
	
	Map<String, Map<String, Map<String, String>>> getAverageMonthlyOpenAndClose(String ticker);

	Map<String, Map<String, Map<String, String>>> getAverageMonthlyOpenAndClose(String[] tickers);

	Map<String, Map<String, Map<String, String>>> getAverageMonthlyOpenAndClose(List<String> tickers);
}
