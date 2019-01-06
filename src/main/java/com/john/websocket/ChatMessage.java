package com.john.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatMessage extends Message implements IBroadcastChatMessage {
	public String action = "chatmessage";
	public String message;
	
	
	public ChatMessage() {
		
	}
	
	public ChatMessage(String message) {
		this.message = message;
	}
	
	
	
	public String createMessageForSender() {
		ChatMessage senderMessage = new ChatMessage(String.format("You said: %s", this.message));
		return senderMessage.createJSONMessage();
	}
	
	public String createBroadcastMessage(String sender) {
		ChatMessage broadcastMessage = new ChatMessage(String.format("%s said: %s", sender, this.message));
		return broadcastMessage.createJSONMessage();
	}

}
