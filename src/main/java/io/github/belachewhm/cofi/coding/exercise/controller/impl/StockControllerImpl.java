package io.github.belachewhm.cofi.coding.exercise.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.belachewhm.cofi.coding.exercise.controller.StockController;
import io.github.belachewhm.cofi.coding.exercise.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api")
@Api(tags = "/api", description = "Expand the endpoints below to interact with web services")
public class StockControllerImpl implements StockController{
	@Autowired
	private StockService stockService;

	@Autowired
	private ObjectMapper mapper;
	
	@RequestMapping(value = "/averageMonthlyOpenAndClose", method = RequestMethod.GET)
	@ApiOperation(value = "Displays the average monthly open and close prices for each security for each month of data in the data set", response = String.class)
	public String averageMonthlyOpenAndClose() throws JsonProcessingException
	{
		log.info("***** Request Recieved to " + this.getClass().getName() + " *****");
		String[] tickers = { "COF", "GOOGL", "MSFT" };
		return mapper.writeValueAsString(stockService.averageMonthlyOpenAndClose(tickers));
	}

	@RequestMapping(value = "/maxDailyProfit", method = RequestMethod.GET)
	@ApiOperation(value = "Which day would provide the highest amount of profit for each security if purchased at the day's low and sold at the day's high", response = String.class)
	public String maxDailyProfit() throws JsonProcessingException
	{
		log.info("***** Request Recieved to " + this.getClass().getName() + " *****");
		String[] tickers = { "COF", "GOOGL", "MSFT" };
		return mapper.writeValueAsString(stockService.maxDailyProfit(tickers));
	}

	@RequestMapping(value = "/busyDay", method = RequestMethod.GET)
	@ApiOperation(value = "Which days generated unusually high activity for the securities", response = String.class)
	public String busyDay() throws JsonProcessingException
	{
		log.info("***** Request Recieved to " + this.getClass().getName() + " *****");
		return mapper.writeValueAsString(stockService.busyDay());
	}

	@RequestMapping(value = "/biggestLoser", method = RequestMethod.GET)
	@ApiOperation(value = "Which security had the most days where the closing price was lower than the opening price", response = String.class)
	public String biggestLoser() throws JsonProcessingException
	{
		log.info("***** Request Recieved to " + this.getClass().getName() + " *****");
		return mapper.writeValueAsString(stockService.biggestLoser());
	}
}