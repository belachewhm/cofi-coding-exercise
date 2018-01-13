package io.github.belachewhm.cofi.coding.exercise.controller;

import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AdditionalFeaturesController
{
	Map<String, Map<String, String>> maxDailyProfit() throws JsonProcessingException;
	
	Map<String, Map<String, String>> busyDay() throws JsonProcessingException;
	
	Entry<String, Integer> biggestLoser() throws JsonProcessingException;
}
