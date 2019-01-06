package com.john.thrombose;

public class Field {
	private String id;
	private Color color;
	
	public enum Color {
		Black, White
	}	
	
	public IPiece piece;
	
	public Field(String id, Color color) {
		this.id = id;
		this.color = color;
	}
	
}
