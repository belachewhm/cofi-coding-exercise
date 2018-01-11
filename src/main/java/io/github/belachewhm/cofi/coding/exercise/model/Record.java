package io.github.belachewhm.cofi.coding.exercise.model;

import java.util.Date;

import lombok.Data;

@Data
public class Record
{
	private String ticker;
	private Date date;
	
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;

	private double ex_dividend;

	private double split_ratio;
	
	private double adj_open;
	private double adj_high;
	private double adj_low;
	private double adj_close;
	private long adj_volume;
}