package io.github.belachewhm.cofi.coding.exercise.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.belachewhm.cofi.coding.exercise.controller.MainFeaturesController;
import io.github.belachewhm.cofi.coding.exercise.service.MainFeaturesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "1. Main Feature", description = "Main Features for this Coding Exercise")
public class MainFeaturesControllerImpl implements MainFeaturesController {
	@Autowired
	private MainFeaturesService mainFeaturesService;

	@RequestMapping(value = "/averageMonthlyOpenAndClose/", method = RequestMethod.GET)
	@ApiOperation(value = "Displays the average monthly open and close prices for each security for each month of data in the data set", response = String.class)
	public ResponseEntity<?> getAllAverageMonthlyOpenAndCloses() {
		Map<?, ?> response = mainFeaturesService.getAllAverageMonthlyOpenAndCloses();
		if (response == null || response.isEmpty()) {
			String message = "";
			log.error(message);
			return new ResponseEntity(message, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<?, ?>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/averageMonthlyOpenAndClose/{ticker}/", method = RequestMethod.GET)
	@ApiOperation(value = "Displays the average monthly open and close prices for each security for each month of data in the data set", response = String.class)
	public ResponseEntity<?> getAverageMonthlyOpenAndClose(@PathVariable("ticker") String ticker) {
		Map<?, ?> response = mainFeaturesService.getAverageMonthlyOpenAndClose(ticker);
		if (response == null || response.isEmpty()) {
			String message = "";
			log.error(message);
			return new ResponseEntity(message, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<?, ?>>(response, HttpStatus.OK);
	}
}