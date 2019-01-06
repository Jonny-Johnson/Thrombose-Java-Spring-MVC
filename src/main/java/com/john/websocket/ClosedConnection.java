package com.john.websocket;

public class ClosedConnection implements IBroadcastChatMessage {
	String remoteAddress;
	
	
	public ClosedConnection(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	@Override
	public String createMessageForSender() {
		ChatMessage message = new ChatMessage("This message shouldn't be sent to anyone as it is intendend for the disconnected party");
		return message.createJSONMessage();
	}

	@Override
	public String createBroadcastMessage(String sender) {
		ChatMessage message =  new ChatMessage(String.format("Connection from %s was closed.", this.remoteAddress));	
		return message.createJSONMessage();
	}

}
