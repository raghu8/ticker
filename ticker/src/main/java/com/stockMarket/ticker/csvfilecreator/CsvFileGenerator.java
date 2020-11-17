package com.stockMarket.ticker.csvfilecreator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import com.stockMarket.ticker.apiprocessing.ReadAlphaAdvantageApi;

public class CsvFileGenerator {
	public void createCsv(String stockOne, String stockTwo, String stockThree) {
		try {

			int hours = LocalTime.now().getHour();
			int mins = LocalTime.now().getMinute();
			String timeStamp = Integer.toString(hours) + Integer.toString(mins);
			ReadAlphaAdvantageApi stockPrices = new ReadAlphaAdvantageApi();
			BufferedWriter writer = new BufferedWriter(new FileWriter("StockPrices-" + timeStamp + ".csv"));
			ArrayList<String> watchListPrices = new ArrayList<String>();
			watchListPrices.add(stockOne);
			watchListPrices.add(stockTwo);
			watchListPrices.add(stockThree);
			String tet = stockPrices.retrivingStockPrices("NVDA");
			for(int i=0;i<=watchListPrices.size()-1;i++) {
				writer.append(stockPrices.retrivingStockPrices(watchListPrices.get(i))+"\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
