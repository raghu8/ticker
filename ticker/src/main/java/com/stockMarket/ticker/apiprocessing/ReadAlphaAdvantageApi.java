package com.stockMarket.ticker.apiprocessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadAlphaAdvantageApi {

	private HttpURLConnection connection;

	public String retrivingStockPrices(String stockSymbol) {
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();

		try {
			// https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&outputsize=full&apikey=VH6I5JD3Y9KYWRFH
			// http://jsonplaceholder.typicode.com/albums
			URL url = new URL(
					"https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+stockSymbol+"&interval=30min&outputsize=compact&apikey=VH6I5JD3Y9KYWRFH");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			int status = connection.getResponseCode();
			System.out.println(status);
			if (status > 299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}

			JsonProcessor stockPrice = new JsonProcessor();
			System.out.println();
			return stockPrice.stockAndPrice(responseContent);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.disconnect();
		}
		return null;
		
	}
}
