package io.github.belachewhm.cofi.coding.exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface StockController
{
	String averageMonthlyOpenAndClose() throws JsonProcessingException;
	
	String maxDailyProfit() throws JsonProcessingException;
	
	String busyDay() throws JsonProcessingException;
	
	String biggestLoser() throws JsonProcessingException;
}
