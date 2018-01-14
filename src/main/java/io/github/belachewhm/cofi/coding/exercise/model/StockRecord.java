package io.github.belachewhm.cofi.coding.exercise.model;

import java.util.Date;

public interface StockRecord {
	String getMonthAndYear();

	Double calculateMaximumDailyProfit();

	String getTicker();

	Double getOpen();

	Double getClose();

	Date getDate();
	
	Boolean isLoser();
	
	Double getVolume();
}