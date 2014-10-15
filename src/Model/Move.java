package Model;

import java.awt.Point;

public class Move {
	private Point start, end;
	private Piece piece;
	private int distance;
	public Move(Point startPoint, Point endPoint, Piece movingPiece) {
		start = startPoint;
		end = endPoint;
		piece = movingPiece;
		distance = board.getDistance(start, end);
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public int getDistance() {
		return distance;
	}
}
