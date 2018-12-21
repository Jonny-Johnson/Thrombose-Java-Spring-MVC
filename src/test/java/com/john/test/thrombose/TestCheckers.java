package com.john.test.thrombose;

import static org.junit.Assert.assertTrue;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.adapter.standard.StandardWebSocketSession;

import com.john.thrombose.Checkers;
import com.john.thrombose.Player;
import com.john.thrombose.Player.Color;



public class TestCheckers {

	private final HttpHeaders headers = new HttpHeaders();
	private final Map<String, Object> attributes = new HashMap<>();
	
	
	Checkers game;
	
	@Before

	public void before() {

		System.out.println("Before Test Case: Create Game");
		game = new Checkers();		
	}


	@Test
	public void Colors2() {
		assertTrue(game.availableColors().size() == 2);
	}
	
	@Test
	public void ColorsBlack() {
		try {
			game.addPlayer(new Player(new StandardWebSocketSession(this.headers, this.attributes, null, new InetSocketAddress(InetAddress.getByName("10.10.10.10"), 20000))));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.chooseColor(game.players.get(0), Color.White);
		assertTrue(game.availableColors().get(0) == Color.Black && game.availableColors().size() == 1);
	}
	
	@Test
	public void ColorsWhite() {
		try {
			game.addPlayer(new Player(new StandardWebSocketSession(this.headers, this.attributes, null, new InetSocketAddress(InetAddress.getByName("10.10.10.10"), 20000))));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.chooseColor(game.players.get(0), Color.Black);
		assertTrue(game.availableColors().get(0) == Color.White && game.availableColors().size() == 1);
	}
	
	
	@Test
	public void ColorsNone() {
		try {
			game.addPlayer(new Player(new StandardWebSocketSession(this.headers, this.attributes, null, new InetSocketAddress(InetAddress.getByName("10.10.10.10"), 20000))));
			game.addPlayer(new Player(new StandardWebSocketSession(this.headers, this.attributes, null, new InetSocketAddress(InetAddress.getByName("11.10.10.10"), 20000))));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.chooseColor(game.players.get(0), Color.Black);
		game.chooseColor(game.players.get(1), Color.White);
		assertTrue(game.availableColors().size() == 0);
	}
	

}
