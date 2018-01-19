package io.github.belachewhm.cofi.coding.exercise.controller;

import org.springframework.http.ResponseEntity;

public interface AdditionalFeaturesController
{
	ResponseEntity<?> getAllMaxDailyProfits();
	
	ResponseEntity<?> getMaxDailyProfit(String ticker);
	
	ResponseEntity<?> getAllAverageVolumes();
	
	ResponseEntity<?> getAverageVolume(String ticker);
	
	ResponseEntity<?> getAllBusyDays();
	
	ResponseEntity<?> getBusyDay(String ticker);
	
	ResponseEntity<?> getBiggestLoser();
}
