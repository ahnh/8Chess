package Model;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Piece {
	protected int team;
	protected char displayChar;
	protected String name;
	
	public Piece(int team, char disChar, String pName) {
		this.team = team;
		
		if (team%2 == 1){
			this.displayChar = Character.toLowerCase(disChar);
		}
		else{
			this.displayChar = Character.toUpperCase(disChar);
		}
		
		this.name = pName;
	}
	public abstract boolean checkDestination(Point pos, Point curPos);
	
	public boolean preCheckDestination(Point pos, Point curPos)
	{
		if (pos.equals(curPos))
			return false;
		return true;
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
}