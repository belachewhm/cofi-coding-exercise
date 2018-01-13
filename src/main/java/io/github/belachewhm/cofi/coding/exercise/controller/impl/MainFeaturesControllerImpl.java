package io.github.belachewhm.cofi.coding.exercise.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.belachewhm.cofi.coding.exercise.controller.MainFeaturesController;
import io.github.belachewhm.cofi.coding.exercise.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "1. Main Feature", description = "Main Features for this Coding Exercise")
public class MainFeaturesControllerImpl implements MainFeaturesController
{
	@Autowired
	private StockService stockService;

	@SuppressWarnings("serial")
	private List<String> tickers = new ArrayList<String>(){{
		add("COF");
		add("GOOGL");
		add("MSFT");
	}};
	
	@RequestMapping(value = "/averageMonthlyOpenAndClose", method = RequestMethod.GET)
	@ApiOperation(value = "Displays the average monthly open and close prices for each security for each month of data in the data set", response = String.class)
	public Map<String, Map<String, Map<String, String>>> averageMonthlyOpenAndClose() throws JsonProcessingException {
		log.info("***** Request Recieved to " + this.getClass().getName() + " *****");
		return stockService.averageMonthlyOpenAndClose(tickers);
	}
}