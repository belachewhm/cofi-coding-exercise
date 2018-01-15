package io.github.belachewhm.cofi.coding.exercise.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;
import io.github.belachewhm.cofi.coding.exercise.model.impl.StockRecordImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SpringConfig {
	@Autowired
	private Environment environment;

	@Autowired
	private BufferedReader bufferedReader;

	private URL url;

	private HttpURLConnection httpURLConnection;

	@Bean
	public BufferedReader bufferedReader() throws IOException {
		url = new URL(environment.getProperty("quandl.api.endpoint"));
		httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.setConnectTimeout(60 * 1000);
		httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
		log.info("Connecting to " + httpURLConnection.getURL().toExternalForm() + "...");
		httpURLConnection.connect();
		log.info("Connection Successful!");
		return new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
	}

	@Bean
	public List<StockRecord> stockRecords() throws IOException {
		log.info("Injesting Records...");
		List<StockRecord> stockRecords = null;
		try {
			stockRecords = bufferedReader.lines().skip(1).map(line -> {
				String[] x = Pattern.compile(",").split(line);
				StockRecord stockRecord = null;
				try {
					stockRecord = new StockRecordImpl(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9], x[10],
							x[11], x[12], x[13]);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return stockRecord;
			}).collect(Collectors.toList());
			log.info("Record Injestion Successful!");
			log.info("Number of Records Injested: " + stockRecords.size());
		} finally {
			bufferedReader.close();
		}
		return stockRecords;
	}
}