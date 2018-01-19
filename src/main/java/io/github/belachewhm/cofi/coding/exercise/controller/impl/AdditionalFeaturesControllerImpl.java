package io.github.belachewhm.cofi.coding.exercise.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.belachewhm.cofi.coding.exercise.controller.AdditionalFeaturesController;
import io.github.belachewhm.cofi.coding.exercise.service.AdditionalFeaturesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "2. Extra Features", description = "Extra Features for this Coding Exercise")
public class AdditionalFeaturesControllerImpl implements AdditionalFeaturesController {
	@Autowired
	private AdditionalFeaturesService additionalFeaturesService;

	// Max Daily Profit
	@ApiOperation(value = "Calculates and returns which day would provide the highest amount of profit for each security if purchased at the day's low and sold at the day's high", response = String.class)
	@RequestMapping(value = "/maxDailyProfit/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllMaxDailyProfits() {
		Map<?, ?> response = additionalFeaturesService.getAllMaxDailyProfits();
		if (response.isEmpty() || response == null) {
			log.error("ticker not found");
			return new ResponseEntity("data not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<?, ?>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Calculates and returns which day would provide the highest amount of profit for each security if purchased at the day's low and sold at the day's high", response = String.class)
	@RequestMapping(value = "/maxDailyProfit/{ticker}/", method = RequestMethod.GET)
	public ResponseEntity<?> getMaxDailyProfit(@PathVariable("ticker") String ticker) {
		Map<?, ?> response = additionalFeaturesService.getMaxDailyProfit(ticker);
		if (response.isEmpty() || response == null) {
			log.error("ticker not found");
			if (response.isEmpty() || response == null) {
				log.error("ticker not found");
				return new ResponseEntity(ticker + " not found", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity("", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<?, ?>>(response, HttpStatus.OK);
	}

	// Average Volume
	@ApiOperation(value = "Calculates and returns the average volumes for each security", response = String.class)
	@RequestMapping(value = "/averageVolume/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllAverageVolumes() {
		Map<?, ?> response = additionalFeaturesService.getAllAverageVolumes();
		if (response.isEmpty() || response == null) {
			log.error("ticker not found");
			return new ResponseEntity("data not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<?, ?>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Calculates and returns the average volumes for each security", response = String.class)
	@RequestMapping(value = "/averageVolume/{ticker}/", method = RequestMethod.GET)
	public ResponseEntity<?> getAverageVolume(@PathVariable("ticker") String ticker) {
		Map<?, ?> response = additionalFeaturesService.getAverageVolume(ticker);
		if (response.isEmpty() || response == null) {
			log.error("ticker not found");
			return new ResponseEntity(ticker + " not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<?, ?>>(response, HttpStatus.OK);
	}

	// Busy Day
	@ApiOperation(value = "Calculates and returns which days generated unusually high activity for the securities", response = String.class)
	@RequestMapping(value = "/busyDay/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBusyDays() {
		Map<?, ?> response = additionalFeaturesService.getAllBusyDays();
		return new ResponseEntity<Map<?, ?>>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Calculates and returns which days generated unusually high activity for the securities", response = String.class)
	@RequestMapping(value = "/busyDay/{ticker}/", method = RequestMethod.GET)
	public ResponseEntity<?> getBusyDay(@PathVariable("ticker") String ticker) {
		Map<?, ?> response = additionalFeaturesService.getBusyDay(ticker);
		if (response.isEmpty() || response == null) {
			log.error("ticker not found");
			return new ResponseEntity(ticker + " not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<?, ?>>(response, HttpStatus.OK);
	}

	// Biggest Loser
	@ApiOperation(value = "Calculates and returns which security had the most days where the closing price was lower than the opening price", response = String.class)
	@RequestMapping(value = "/biggestLoser/", method = RequestMethod.GET)
	public ResponseEntity<?> getBiggestLoser() {
		Map<?, ?> response = additionalFeaturesService.getBiggestLoser();
		if (response.isEmpty() || response == null) {
			log.error("ticker not found");
			return new ResponseEntity("data not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<?, ?>>(response, HttpStatus.OK);
	}
}