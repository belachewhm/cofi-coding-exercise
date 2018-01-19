package io.github.belachewhm.cofi.coding.exercise.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;
import io.github.belachewhm.cofi.coding.exercise.service.DataRetrievalService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataRetrievalServiceImpl implements DataRetrievalService {
	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;

	public List<StockRecord> getStockRecords() {
		String endpoint = environment.getProperty("quandl.api.endpoint");
		String result = restTemplate.getForObject(endpoint, String.class);

		InputStream inputStream = new ByteArrayInputStream(result.getBytes());
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		log.info("Injesting Records...");
		List<StockRecord> stockRecords = null;

		stockRecords = bufferedReader.lines().skip(1).map(line -> {
			String[] x = Pattern.compile(",").split(line);
			StockRecord stockRecord = null;
			try {
				stockRecord = new StockRecord(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9], x[10], x[11],
						x[12], x[13]);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}
			return stockRecord;
		}).collect(Collectors.toList());
		log.info("Record Injestion Successful!");
		log.info("Number of Records Injested: " + stockRecords.size());

		try {
			bufferedReader.close();
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		return stockRecords;
	}
}
