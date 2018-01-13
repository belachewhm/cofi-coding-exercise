package io.github.belachewhm.cofi.coding.exercise.controller;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AdditionalFeaturesController
{
	Map<String, Map<String, String>> maxDailyProfit() throws JsonProcessingException;
	
	Map<String, Map<String, String>> busyDay() throws JsonProcessingException;
	
	Map<String, Integer> biggestLoser() throws JsonProcessingException;
}
