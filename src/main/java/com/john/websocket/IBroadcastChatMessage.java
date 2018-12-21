package com.john.websocket;

public interface IBroadcastChatMessage {
	public ChatMessage createMessageForSender();
	
	public ChatMessage createBroadcastMessage(String sender);	
}
