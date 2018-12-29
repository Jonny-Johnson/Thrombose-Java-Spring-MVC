package com.john.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.john.thrombose.Checkers;
import com.john.thrombose.Player;

@Component
public class ThromboseWebSocketHandler extends AbstractWebSocketHandler {

	static List<Player> connections = new ArrayList<Player>();
	static Checkers game = new Checkers();

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		if (message instanceof TextMessage) {
			handleTextMessage(session, (TextMessage) message);
		} else if (message instanceof PongMessage) {
			handlePongMessage(session);

		}
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) {
		String clientMessage = message.getPayload();
		ObjectMapper objectMapper = new ObjectMapper();

		// determine message type and action. TODO: maybe replace with MessageBroker /
		// STOMP?		

		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(clientMessage);
			System.out.println(jsonNode.toString());
			String action = jsonNode.get("action").asText();
			System.out.println(action);

			switch (action) {
			case "move":
				// check if it's a legal move and respond with an updated pieces array
				break;
			case "chatmessage":
				handleChatMessage(session, clientMessage);
				break;
			case "chatname":
				handleChatNameChange(session, clientMessage);
				break;
			default:
				try {
					throw new Exception("Error: No message type matched for this message. No action triggered.");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void handleChatMessage(WebSocketSession session, String clientMessage) {
		ObjectMapper objectMapper = new ObjectMapper();
		ChatMessage chatMessage;
		try {
			chatMessage = objectMapper.readValue(clientMessage, ChatMessage.class);
			broadcastChatMessage(chatMessage, getPlayerBySession(session));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void handleChatNameChange(WebSocketSession session, String clientMessage) {
		ObjectMapper objectMapper = new ObjectMapper();
		NameChange nameChange;
		try {
			nameChange = objectMapper.readValue(clientMessage, NameChange.class);

			Player player = getPlayerBySession(session);
			nameChange.addOldName(player.toString());
			player.name = nameChange.name;

			broadcastChatMessage(nameChange, player);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void broadcastChatMessage(IBroadcastChatMessage chatMessage, Player sender) {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = "";

		ChatMessage broadcastMessage = chatMessage.createBroadcastMessage(sender.toString());
		ChatMessage senderMessage = chatMessage.createMessageForSender();

		try {
			for (Player p : connections) {
				if (!p.equals(sender)) {
					if (!p.disconnected) {
						jsonString = objectMapper.writeValueAsString(broadcastMessage);
						p.session.sendMessage(new TextMessage(jsonString));
					}
				} else {
					jsonString = objectMapper.writeValueAsString(senderMessage);
					p.session.sendMessage(new TextMessage(jsonString));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void handlePongMessage(WebSocketSession s) {

	}

	static Player getPlayerBySession(WebSocketSession session) {
		if (connections.contains(new Player(session))) {
			return connections.get(connections.indexOf(new Player(session)));
		} else {
			return null;
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		Player player = new Player(session);
		connections.add(player);
		game.addPlayer(player);

		NewConnection connection = new NewConnection(session.getRemoteAddress().toString());
		broadcastChatMessage(connection, player);

		System.out.format("New Session from Endpoint %s! Current Sessions: %n", session.getRemoteAddress().toString());
		printSessions();
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		Player player = getPlayerBySession(session);
		player.disconnected = true;
		game.players.remove(player);
		connections.remove(player);

		ClosedConnection connection = new ClosedConnection(session.getRemoteAddress().toString());
		broadcastChatMessage(connection, player);

		System.out.format("Closed Session from Endpoint %s! Current Sessions: %n",
				session.getRemoteAddress().toString());
		printSessions();
	}

	private void printSessions() {
		if (connections.size() == 0) {
			System.out.println("No active sessions");
		} else
			for (Player p : connections) {
				System.out.println(p.session.getRemoteAddress().toString());
			}
	}
}
