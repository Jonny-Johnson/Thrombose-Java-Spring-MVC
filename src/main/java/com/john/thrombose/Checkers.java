package com.john.thrombose;

import java.util.ArrayList;
import java.util.List;

import com.john.thrombose.Player.Color;
import com.john.websocket.UpdatePlayerCount;

public class Checkers {

	public List<Player> players = new ArrayList<Player>();
	public ChessBoard board = new ChessBoard();

	public Checkers() {

	}
	
	public UpdatePlayerCount removePlayer(Player player) {
		int oldPlayerCount = players.size();
		
		players.remove(player);
		
		if (oldPlayerCount != players.size()) {
			return new UpdatePlayerCount(players.size());
		} else {
			return null;
		}
	}

	public UpdatePlayerCount addPlayer(Player player) {
		int oldPlayerCount = players.size();
		
		if (players.contains(player)) {
			if (!player.disconnected) {
				try {
					throw new Exception(String.format("Player %s is already in the game!", player.toString()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				player.disconnected = false;
				//TODO: something else?
			}				
		} else if (players.size() > 1) {
			try {
				throw new Exception("There are already 2 players in the game!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			players.add(player);
		}
		
		//check if the player count changed
		//return null if it didn't
		if (oldPlayerCount != players.size()) {
			return new UpdatePlayerCount(players.size());
		} else {
			return null;
		}
	}

	public void chooseColor(Player player, Color color) {
		if (!players.contains(player)) {
			try {
				throw new Exception(String.format("Player %s is not in the game!", player.toString()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (!availableColors().contains(color)) {
			try {
				throw new Exception(String.format("Color %s is already taken!", color.toString()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			player.color = color;
		}
	}

	public List<Color> availableColors() {
		List<Color> colors = new ArrayList<Color>();
		colors.add(Color.White);
		colors.add(Color.Black);

		for (Player p : players) {
			colors.remove(p.color);
		}
		return colors;
	}

}
