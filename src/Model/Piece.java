package Model;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Piece {
	protected int team;
	protected Point cusPos;
	protected char displayChar;
	protected String name;
	
	public Piece(int team, Point curPos) {
		this.team = team;
		this.cusPos = curPos;
	}
	public abstract boolean checkDestination(Point cusPos);
	public abstract ArrayList<Point> getAvailDestination();
	public void updatePosition(Point newPos)
	{
		this.cusPos = newPos;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public Point getCusPos() {
		return cusPos;
	}
	public void setCusPos(Point cusPos) {
		this.cusPos = cusPos;
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