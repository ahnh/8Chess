package Model;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Piece {
	protected int team;
	protected char displayChar;
	protected String name;
	protected boolean hasMoved;
	public Piece(int team, char disChar, String pName) {
		this.team = team;
		
		if (team%2 == 1){
			this.displayChar = Character.toLowerCase(disChar);
		}
		else{
			this.displayChar = Character.toUpperCase(disChar);
		}
		
		this.name = pName;
		this.hasMoved = false;
	}
	public abstract boolean checkDestination(Move move);
	
	public boolean preCheckDestination(Move move)
	{
		if (move.getStart().equals(move.getEnd()))
			return false;
		return true;
	}

	public void afterMove(){
		// Stub method that can be overridden if a piece needs to perform an action
		// after they move. (Ex. The pawn must know when it has been moved already
		// to determine if it can move 2 spaces)
	}
	
	public abstract ArrayList<Point> getAvailDestination();
	
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public char getDisplayChar() {
		return displayChar;
	}
	public String getDisplayStr() {
		return displayChar + "";
	}
	public void setDisplayChar(char displayChar) {
		this.displayChar = displayChar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean hasMoved(){
		return hasMoved;
	}
}