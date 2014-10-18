package Model;

import java.awt.Point;

public class Move {
	private Point start, end;
	private Piece piece;
	private int distanceX, distanceY;
	public int action;
	public boolean optionSelected;
	public int option;
	
	public Move(Point startPoint, Point endPoint, Piece movingPiece) {
		start = startPoint;
		end = endPoint;
		piece = movingPiece;

		distanceX = Math.abs( startPoint.x - endPoint.x );
		distanceY = Math.abs( startPoint.y - endPoint.y );
		
		action = Rule.VALID_MOVE;
		optionSelected = false;
		option = -1;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public Point getStart(){
		return start;
	}
	
	public Point getEnd(){
		return end;
	}
	
	public int getDistanceY() {
		return distanceY;
	}
	
	public int getDistanceX() {
		return distanceX;
	}
}
