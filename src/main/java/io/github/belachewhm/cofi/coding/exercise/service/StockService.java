package io.github.belachewhm.cofi.coding.exercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;

@Service
public class StockService {
	@Autowired
	private List<StockRecord> stockRecords;

	public String averageMonthlyOpenAndClose() {
		return String.valueOf(stockRecords.size());
	}

	public String maxDailyProfit() {
		return String.valueOf(stockRecords.size());
	}

	public String busyDay() {
		return String.valueOf(stockRecords.size());
	}

	public String biggestLoser() {
		return String.valueOf(stockRecords.size());
	}
}