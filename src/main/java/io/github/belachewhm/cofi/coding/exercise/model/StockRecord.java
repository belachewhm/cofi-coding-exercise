package io.github.belachewhm.cofi.coding.exercise.model;

import java.util.Date;

public interface StockRecord {
	String getMonthAndYear();

	double calculateMaximumDailyProfit();

	String getTicker();

	double getOpen();

	double getClose();

	Date getDate();
}