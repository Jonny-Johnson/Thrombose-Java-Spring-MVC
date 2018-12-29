package com.john.websocket;

public class NewConnection implements IBroadcastChatMessage {
	String remoteAddress;
	
	
	public NewConnection(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	@Override
	public ChatMessage createMessageForSender() {
		return new ChatMessage(String.format("You've successfully connected!%nYour IP: %s", this.remoteAddress));		
	}

	@Override
	public ChatMessage createBroadcastMessage(String sender) {
		return new ChatMessage(String.format("New connectiom from %s", this.remoteAddress));			

	}

}
