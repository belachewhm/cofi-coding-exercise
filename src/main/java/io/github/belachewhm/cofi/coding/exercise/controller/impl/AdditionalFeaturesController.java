package io.github.belachewhm.cofi.coding.exercise.controller.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.belachewhm.cofi.coding.exercise.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "2. Extra Features", description = "Extra Features for this Coding Exercise")
public class AdditionalFeaturesController
{
	@Autowired
	private StockService stockService;

	private String[] tickers = { "COF", "GOOGL", "MSFT" };
	
	@RequestMapping(value = "/maxDailyProfit", method = RequestMethod.GET)
	@ApiOperation(value = "Which day would provide the highest amount of profit for each security if purchased at the day's low and sold at the day's high", response = String.class)
	public Map<String, Map<String, String>> maxDailyProfit() throws JsonProcessingException
	{
		log.info("***** Request Recieved to " + this.getClass().getName() + " *****");
		return stockService.maxDailyProfit(tickers);
	}

	@RequestMapping(value = "/busyDay", method = RequestMethod.GET)
	@ApiOperation(value = "Which days generated unusually high activity for the securities", response = String.class)
	public Map<String, Map<String, String>> busyDay() throws JsonProcessingException
	{
		log.info("***** Request Recieved to " + this.getClass().getName() + " *****");
		return stockService.busyDay(tickers);
	}

	@RequestMapping(value = "/biggestLoser", method = RequestMethod.GET)
	@ApiOperation(value = "Which security had the most days where the closing price was lower than the opening price", response = String.class)
	public Entry<String, Integer> biggestLoser() throws JsonProcessingException
	{
		log.info("***** Request Recieved to " + this.getClass().getName() + " *****");
		return stockService.biggestLoser(tickers);
	}
}