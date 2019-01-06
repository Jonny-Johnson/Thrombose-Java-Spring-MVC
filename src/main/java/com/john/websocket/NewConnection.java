package com.john.websocket;

public class NewConnection implements IBroadcastChatMessage {
	String remoteAddress;
	
	
	public NewConnection(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	@Override
	public String createMessageForSender() {
		return new ChatMessage(String.format("You've successfully connected!%nYour IP: %s", this.remoteAddress)).createJSONMessage();		
	}

	@Override
	public String createBroadcastMessage(String sender) {
		return new ChatMessage(String.format("New connectiom from %s", this.remoteAddress)).createJSONMessage();			

	}

}
