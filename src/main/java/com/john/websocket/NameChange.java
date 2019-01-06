package com.john.websocket;

public class NameChange implements IBroadcastChatMessage {
	public String action;
	public String name;
	
	private String oldName;
	
	public NameChange() {
		
	}
	
	public void addOldName(String oldName) {
		this.oldName = oldName;
	}

	public String createMessageForSender() {
		return new ChatMessage(String.format("You changed your name to: %s", this.name)).createJSONMessage();
	}
	

	public String createBroadcastMessage(String sender) {
		return new ChatMessage(String.format("%s changed their name to: %s", this.oldName, this.name)).createJSONMessage();
	}

}
