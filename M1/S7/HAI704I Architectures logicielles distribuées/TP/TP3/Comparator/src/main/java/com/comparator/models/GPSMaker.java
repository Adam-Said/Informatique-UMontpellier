package com.comparator.models;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GPSMaker {
	public static String gpsEncoder(String adr) throws IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();
        Geocoder geocoder = new Geocoder();

        String response = geocoder.GeocodeSync(adr);
        JsonNode responseJsonNode = mapper.readTree(response);

        JsonNode items = responseJsonNode.get("items");

        for (JsonNode item : items) {
            JsonNode position = item.get("position");

            String lat = position.get("lat").asText();
            String lng = position.get("lng").asText();
            return lat + " " + lng;
        }
		return "00.0000 00.0000";
    }
}