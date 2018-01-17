package io.github.belachewhm.cofi.coding.exercise.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;

public final class Util {
	/**
	 * important to set scale and round AFTER all calculations have been
	 * completed, so as not to lose precision
	 * 
	 * @param value
	 * @return
	 */
	public static Double truncateDoubleToPrice(Double value) {
		return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	/**
	 * 
	 * @param ticker
	 * @return
	 */
	public static Double averageVolume(String ticker, List<StockRecord> stockRecords) {
		Double sum = 0.0;
		int count = 0;
		for (StockRecord record : stockRecords) {
			if (record.getTicker().equalsIgnoreCase(ticker)) {
				sum = sum + record.getVolume();
				count = count + 1;
			}
		}
		Double averageVolume = sum / count;
		return averageVolume;
	}
}
