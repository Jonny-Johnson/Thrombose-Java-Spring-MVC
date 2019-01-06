package com.john.websocket;

public class UpdatePlayerCount extends Message implements IBroadcastChatMessage {
	public final String action = "playerCountUpdate";
	public int count;

	public UpdatePlayerCount(int count) {
		this.count = count;
	}

	@Override
	public String createMessageForSender() {
		return createJSONMessage();
	}

	@Override
	public String createBroadcastMessage(String sender) {
		return createMessageForSender();
	}

}
