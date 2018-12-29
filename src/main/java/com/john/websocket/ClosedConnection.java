package com.john.websocket;

public class ClosedConnection implements IBroadcastChatMessage {
	String remoteAddress;
	
	
	public ClosedConnection(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	@Override
	public ChatMessage createMessageForSender() {
		return new ChatMessage("This message shouldn't be sent to anyone as it is intendend for the disconnected party");		
	}

	@Override
	public ChatMessage createBroadcastMessage(String sender) {
		return new ChatMessage(String.format("Connection from %s was closed.", this.remoteAddress));			

	}

}
