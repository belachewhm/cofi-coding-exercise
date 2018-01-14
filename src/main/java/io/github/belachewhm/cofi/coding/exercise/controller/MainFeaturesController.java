package io.github.belachewhm.cofi.coding.exercise.controller;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MainFeaturesController
{
	Map<String, Map<String, Map<String, String>>> averageMonthlyOpenAndClose();
}
