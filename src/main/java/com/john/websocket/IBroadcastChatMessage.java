package com.john.websocket;

public interface IBroadcastChatMessage {
	
	//both methods must create JSON strings to be sent over the websocket
	
	public String createMessageForSender();
	
	public String createBroadcastMessage(String sender);	
}
