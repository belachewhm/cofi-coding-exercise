package io.github.belachewhm.cofi.coding.exercise.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class StockRecord {
	private String ticker;
	private Date date;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;
	private BigDecimal volume;
	private BigDecimal ex_dividend;
	private double split_ratio;
	private BigDecimal adj_open;
	private BigDecimal adj_high;
	private BigDecimal adj_low;
	private BigDecimal adj_close;
	private double adj_volume;

	public StockRecord(String ticker, String date, String open, String high, String low, String close, String volume,
			String ex_dividend, String split_ratio, String adj_open, String adj_high, String adj_low, String adj_close,
			String adj_volume) throws ParseException {
		this.setTicker(ticker);
		this.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		this.setOpen(new BigDecimal(open));
		this.setHigh(new BigDecimal(high));
		this.setLow(new BigDecimal(low));
		this.setClose(new BigDecimal(close));
		this.setVolume(new BigDecimal(volume));
		this.setEx_dividend(new BigDecimal(ex_dividend));
		this.setSplit_ratio(Double.parseDouble(split_ratio));
		this.setAdj_open(new BigDecimal(adj_open));
		this.setAdj_high(new BigDecimal(adj_high));
		this.setAdj_low(new BigDecimal(adj_low));
		this.setAdj_close(new BigDecimal(adj_close));
		this.setAdj_volume(Double.parseDouble(adj_volume));
	}
}