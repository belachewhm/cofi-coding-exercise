package io.github.belachewhm.cofi.coding.exercise.service;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;

@Service
public class StockService
{
	@Autowired
	private List<StockRecord> stockRecords;

	public String averageMonthlyOpenAndClose()
	{		
		Map<Month, List<StockRecord>> cofRecordsByMonth = stockRecords.stream().filter(record->record.getTicker().equalsIgnoreCase("COF")).collect(Collectors.groupingBy(StockRecord::getMonth));
		for(Month month : cofRecordsByMonth.keySet())
		{
			List<StockRecord> monthRecords = cofRecordsByMonth.get(month);
			double average_open = monthRecords.stream().collect(Collectors.averagingDouble(StockRecord::getOpen));
			double average_close = monthRecords.stream().collect(Collectors.averagingDouble(StockRecord::getClose));
			
			System.out.println("Month: " + month);
			System.out.println("average_open: " + average_open);
			System.out.println("average_close: " + average_close);
			System.out.println();
		}
		
		
		
		
		
		
		List<StockRecord> googlRecords = stockRecords.stream().filter(record->record.getTicker().equalsIgnoreCase("GOOGL")).collect(Collectors.toList());
		List<StockRecord> msftRecords = stockRecords.stream().filter(record->record.getTicker().equalsIgnoreCase("MSFT")).collect(Collectors.toList());
		
		
		
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