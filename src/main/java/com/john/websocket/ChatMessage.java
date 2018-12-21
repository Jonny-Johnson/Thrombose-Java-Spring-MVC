package com.john.websocket;

public class ChatMessage implements IBroadcastChatMessage {
	public String action = "chatmessage";
	public String message;
	
	public ChatMessage() {
		
	}
	
	public ChatMessage(String message) {
		this.message = message;
	}
	
	public ChatMessage createMessageForSender() {
		return new ChatMessage(String.format("You said: %s", this.message));
	}
	
	public ChatMessage createBroadcastMessage(String sender) {
		return new ChatMessage(String.format("%s said: %s", sender, this.message));
	}

}
