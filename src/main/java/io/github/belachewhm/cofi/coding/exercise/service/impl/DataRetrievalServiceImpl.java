package io.github.belachewhm.cofi.coding.exercise.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

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
		BufferedReader bufferedReader = this.getBufferedReader(environment.getProperty("quandl.api.endpoint"));

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

	@HystrixCommand(fallbackMethod = "reliable")
	private BufferedReader getBufferedReader(String endpoint) {
		String result = restTemplate.getForObject(endpoint, String.class);
		InputStream inputStream = new ByteArrayInputStream(result.getBytes());
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		return new BufferedReader(inputStreamReader);
	}

	@SuppressWarnings("unused")
	private BufferedReader reliable() throws FileNotFoundException {
		File file = new File("src/test/resources/WIKI-PRICES_375.csv");
		FileInputStream fileInputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		return new BufferedReader(inputStreamReader);
	}
}
