package io.github.belachewhm.cofi.coding.exercise.controller;

import org.springframework.http.ResponseEntity;

public interface MainFeaturesController
{
	ResponseEntity<?> getAllAverageMonthlyOpenAndCloses();
	
	ResponseEntity<?> getAverageMonthlyOpenAndClose(String ticker);
}
