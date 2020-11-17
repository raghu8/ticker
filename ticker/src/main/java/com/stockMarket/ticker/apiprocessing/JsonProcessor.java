package com.stockMarket.ticker.apiprocessing;

import java.util.ArrayList;

import org.json.JSONObject;

public class JsonProcessor {

	public String stockAndPrice(StringBuffer responseContent) {
		JSONObject object = new JSONObject(responseContent.toString());
		String[] jsonKeys = JSONObject.getNames(object);
		ArrayList<String> metaData = new ArrayList<String>();
		for (String key : jsonKeys) {
			Object value = object.get(key);
			metaData.add(value.toString());
		}
		//Testing commit 
		JSONObject metadataObject = new JSONObject(metaData.get(1));

		String symbol = metadataObject.getString(jsonIterator(1, metadataObject));
		String date = metadataObject.getString(jsonIterator(4, metadataObject));

		JSONObject timeseriesObject = new JSONObject(metaData.get(0));
		jsonIterator(0, timeseriesObject);
		JSONObject lastRefreshed = timeseriesObject.getJSONObject(date);
		String price = lastRefreshed.getString(jsonIterator(4, timeseriesObject.getJSONObject(date)));

		return symbol + "," + price;
	}

	public String jsonIterator(int itemNumber, JSONObject jsonObject) {
		String[] lastRefreshedPrice = JSONObject.getNames(jsonObject);
		ArrayList<String> jsonList = new ArrayList<String>();
		for (String key : lastRefreshedPrice) {
			jsonList.add(key);
		}
		return jsonList.get(itemNumber);

	}
}
