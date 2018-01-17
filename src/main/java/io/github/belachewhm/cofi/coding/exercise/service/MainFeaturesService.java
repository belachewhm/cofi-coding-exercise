package io.github.belachewhm.cofi.coding.exercise.service;

import java.util.List;
import java.util.Map;

public interface MainFeaturesService
{
	Map<String, Map<String, Map<String, String>>> averageMonthlyOpenAndClose(String ticker);

	Map<String, Map<String, Map<String, String>>> averageMonthlyOpenAndClose(String[] tickers);

	Map<String, Map<String, Map<String, String>>> averageMonthlyOpenAndClose(List<String> tickers);
}
