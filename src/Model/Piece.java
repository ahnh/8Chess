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
}