package Model;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Piece {
	protected int team;
	protected Point curPos;
	protected char displayChar;
	protected String name;
	
	public Piece(int team, Point curPos) {
		this.team = team;
		this.curPos = curPos;
	}
	public abstract boolean checkDestination(Point pos);
	
	public boolean preCheckDestination(Point pos)
	{
		if (pos.equals(curPos))
			return false;
		return true;
	}
	public abstract ArrayList<Point> getAvailDestination();
	public void updatePosition(Point newPos)
	{
		this.curPos = newPos;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public Point getCusPos() {
		return curPos;
	}
	public void setCusPos(Point newPos) {
		this.curPos = newPos;
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