package com.john.thrombose;

import java.util.ArrayList;
import java.util.List;

import com.john.thrombose.Player.Color;

public class Checkers {

	public List<Player> players = new ArrayList<Player>();

	public Checkers() {

	}

	public void addPlayer(Player player) {
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
