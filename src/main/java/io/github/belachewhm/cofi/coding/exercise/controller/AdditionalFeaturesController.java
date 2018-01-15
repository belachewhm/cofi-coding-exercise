package io.github.belachewhm.cofi.coding.exercise.controller;

import java.util.Map;

public interface AdditionalFeaturesController {
	Map<String, Map<String, String>> maxDailyProfit();

	Map<String, String> averageVolume();

	Map<String, Map<String, String>> busyDay();

	Map<String, Integer> biggestLoser();
}
