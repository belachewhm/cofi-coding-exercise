//package io.github.belachewhm.cofi.coding.exercise.service.impl;
//
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.text.ParseException;
//import java.util.List;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Service;
//
//import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class RestConnectionServiceImpl {
//	@Autowired
//	HttpURLConnection httpURLConnection;
//
//	@PostConstruct
//	public void connect() throws IOException
//	{
//		
//		
//		
//		
//		log.info("Connecting to " + httpURLConnection.getURL().toExternalForm() + "...");
//		httpURLConnection.connect();
//		log.info("Connection Successful!");
//	}
//
//	@Bean
//	public List<StockRecord> getStockRecords() throws IOException
//	{
//		log.info("Injesting Records...");
//		List<StockRecord> stockRecords = null;
//		try {
//			stockRecords = bufferedReader.lines().skip(1).map(line -> {
//				String[] x = Pattern.compile(",").split(line);
//				StockRecord stockRecord = null;
//				try {
//					stockRecord = new StockRecord(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9], x[10],
//							x[11], x[12], x[13]);
//				} catch (ParseException e) {
//					log.error(e.getMessage());
//				}
//				return stockRecord;
//			}).collect(Collectors.toList());
//			log.info("Record Injestion Successful!");
//			log.info("Number of Records Injested: " + stockRecords.size());
//		} finally {
//			bufferedReader.close();
//		}
//		return stockRecords;
//	}
//
//}
