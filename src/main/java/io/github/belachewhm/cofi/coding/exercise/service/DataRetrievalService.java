package io.github.belachewhm.cofi.coding.exercise.service;

import java.io.IOException;
import java.util.List;

import io.github.belachewhm.cofi.coding.exercise.model.StockRecord;

public interface DataRetrievalService
{
	List<StockRecord> getStockRecords();
}
