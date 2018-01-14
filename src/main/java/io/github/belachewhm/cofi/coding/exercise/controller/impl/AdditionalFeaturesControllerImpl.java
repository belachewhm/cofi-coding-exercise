package io.github.belachewhm.cofi.coding.exercise.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.belachewhm.cofi.coding.exercise.controller.AdditionalFeaturesController;
import io.github.belachewhm.cofi.coding.exercise.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "2. Extra Features", description = "Extra Features for this Coding Exercise")
public class AdditionalFeaturesControllerImpl implements AdditionalFeaturesController
{
	@Autowired
	private StockService stockService;

	@SuppressWarnings("serial")
	private List<String> tickers = new ArrayList<String>(){{
		add("COF");
		add("GOOGL");
		add("MSFT");
	}};
	
	@RequestMapping(value = "/maxDailyProfit", method = RequestMethod.GET)
	@ApiOperation(value = "Calculates and returns which day would provide the highest amount of profit for each security if purchased at the day's low and sold at the day's high", response = String.class)
	public Map<String, Map<String, String>> maxDailyProfit()
	{
		log.info("***** Request Recieved to " + this.getClass().getSimpleName() + " *****");
		return stockService.maxDailyProfit(tickers);
	}

	@RequestMapping(value = "/averageVolume", method = RequestMethod.GET)
	@ApiOperation(value = "Calculates and returns the average volumes for each security", response = String.class)
	public Map<String, String> averageVolume()
	{
		log.info("***** Request Recieved to " + this.getClass().getSimpleName() + " *****");
		return stockService.averageVolume(tickers);
	}
	
	@RequestMapping(value = "/busyDay", method = RequestMethod.GET)
	@ApiOperation(value = "Calculates and returns which days generated unusually high activity for the securities", response = String.class)
	public Map<String, Map<String, String>> busyDay()
	{
		log.info("***** Request Recieved to " + this.getClass().getSimpleName() + " *****");
		return stockService.busyDay(tickers);
	}
	
	@RequestMapping(value = "/biggestLoser", method = RequestMethod.GET)
	@ApiOperation(value = "Calculates and returns which security had the most days where the closing price was lower than the opening price", response = String.class)
	public Map<String, Integer> biggestLoser()
	{
		log.info("***** Request Recieved to " + this.getClass().getSimpleName() + " *****");
		return stockService.biggestLoser(tickers);
	}
}