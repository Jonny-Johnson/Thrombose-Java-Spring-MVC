package com.john.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Message {
	private ObjectMapper objectMapper = new ObjectMapper();	
	
	public String createJSONMessage() {
		String jsonString = "";
		try {
			jsonString = objectMapper.writeValueAsString(this);			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}
}
