package io.github.belachewhm.cofi.coding.exercise.model.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockRecordImpl implements StockRecord {
	private String ticker;
	private Date date;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Double volume;
	private Double ex_dividend;
	private Double split_ratio;
	private Double adj_open;
	private Double adj_high;
	private Double adj_low;
	private Double adj_close;
	private Double adj_volume;

	public StockRecordImpl(String ticker, String date, String open, String high, String low, String close,
			String volume, String ex_dividend, String split_ratio, String adj_open, String adj_high, String adj_low,
			String adj_close, String adj_volume) throws ParseException {
		setTicker(ticker);
		setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		setOpen(Double.parseDouble(open));
		setHigh(Double.parseDouble(high));
		setLow(Double.parseDouble(low));
		setClose(Double.parseDouble(close));
		setVolume(Double.parseDouble(volume));
		setEx_dividend(Double.parseDouble(ex_dividend));
		setSplit_ratio(Double.parseDouble(split_ratio));
		setAdj_open(Double.parseDouble(adj_open));
		setAdj_high(Double.parseDouble(adj_high));
		setAdj_low(Double.parseDouble(adj_low));
		setAdj_close(Double.parseDouble(adj_close));
		setAdj_volume(Double.parseDouble(adj_volume));
	}

	public String getMonthAndYear() {
		return (new SimpleDateFormat("yyyy-MM")).format(date);
	}

	public Double calculateMaximumDailyProfit() {
		return (high - low);
	}

	public Boolean isLoser() {
		return (close < open);
	}
}