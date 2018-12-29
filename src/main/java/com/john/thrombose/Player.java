package com.john.thrombose;

import java.util.Objects;

import org.springframework.web.socket.WebSocketSession;

public class Player {
	public WebSocketSession session;
	public String name;
	public enum Color {
		Black, White
	}
	public boolean disconnected = false;
	
	public Color color;

	public Player(WebSocketSession session) {
		this.session = session;		
	}


	
	@Override
	public boolean equals(Object o) {
		//System.out.println("Custom equals");
		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of Player or not "null instanceof [type]" also
		 * returns false
		 */
		if (o instanceof Player) {
			// typecast o to Complex so that we can compare data members
			Player p = (Player) o;
			// Compare the data members and return accordingly
			return Objects.equals(this.session.getRemoteAddress(), p.session.getRemoteAddress());
		} else if (o instanceof WebSocketSession)  {
			return false;
//			WebSocketSession s = (WebSocketSession) o;
//			return Objects.equals(this.session.getRemoteAddress(), s.getRemoteAddress());
		} else {
			return false;
		}

	}
	
	@Override
	public String toString() {
		if (name != null && !name.isEmpty()) {
			return name;
		} else {
			return session.getRemoteAddress().toString();
		}
			
	}
}
