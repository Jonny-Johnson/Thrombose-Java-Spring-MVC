package com.john.test.thrombose;


import static org.junit.Assert.assertTrue;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.adapter.standard.StandardWebSocketSession;

import com.john.thrombose.Player;

public class TestPlayer {

	private final HttpHeaders headers = new HttpHeaders();
	private final Map<String, Object> attributes = new HashMap<>();
	
	Player player1;
	Player player2;
	
	@BeforeClass

	public static void beforeClass() {

		System.out.println("Before Class");

	}

	@Before

	public void before() {

		System.out.println("Before Test Case: Create Player instances");
		try {
			player1 = new Player(new StandardWebSocketSession(this.headers, this.attributes, null, new InetSocketAddress(InetAddress.getByName("10.10.10.10"), 20000)));
			
			player2 = new Player(new StandardWebSocketSession(this.headers, this.attributes, null, new InetSocketAddress(InetAddress.getByName("10.10.10.10"), 20000)));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Test
	public void equalPlayerTest() {
		System.out.println("Test");		
		assertTrue("Player1 is equal to Player2", Objects.equals(player1, player2));
	}
	
	@Test
	public void equalSession1Test() {
		System.out.println("equalSession1Test");		
		assertTrue("Player1 is equal to Player2", Objects.equals(new Player(player1.session), player2));
	}
	
	@Test
	public void equalSession2Test() {
		System.out.println("Test");		
		assertTrue("Player1 is equal to Player2", Objects.equals(player2, new Player(player1.session)));
	}
	
	@After

	public void after() {

		System.out.println("After Test Case");

	}

	@AfterClass

	public static void afterClass() {

		System.out.println("After Class");

	}

}
