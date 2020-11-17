package com.stockMarket.ticker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import com.stockMarket.ticker.apiprocessing.ReadAlphaAdvantageApi;
import com.stockMarket.ticker.csvfilecreator.CsvFileGenerator;

@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication
public class TickerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TickerApplication.class, args);
		ReadAlphaAdvantageApi stockPrices = new ReadAlphaAdvantageApi();
		CsvFileGenerator createExcel = new CsvFileGenerator();
		createExcel.createCsv("AAPL","NFLX","AMZN");
		context.close();
	}
}
