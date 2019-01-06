package com.john.thrombose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

	
	public Map fields = new HashMap<String, Field>(64);
	private List<String> columns = new ArrayList<String>(List.of("A", "B", "C", "D", "E", "F", "G", "H"));
	private List<Integer> rows = new ArrayList<Integer>(List.of(1, 2, 3, 4, 5, 6, 7, 8));

	public ChessBoard() {
		boolean isWhiteField = true;
		for (Integer r : rows) {
			for (String c : columns) {
				//TODO: Specify board transmission interface with main.js. Define rows 8,7,6 (main.js) vs 1,2,3 
			}
		}
	}
}
