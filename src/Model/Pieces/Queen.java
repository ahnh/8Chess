package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Move;
import Model.Piece;

public class Queen extends Piece{

	public Queen(int team) {
		super(team,'Q',"Queen");
	}

	@Override
	public boolean checkDestination(Move move) {
		if (!preCheckDestination(move))
			return false;
		
		Point start = move.getStart();
		Point end = move.getEnd();
		
		// check diagonal move
		if (Math.abs(end.x - start.x) < 8 && Math.abs(end.x - start.x) == Math.abs(end.y - start.y))
			return true;
		
		// check horizontal move
		if (start.x == end.x && Math.abs(end.y-start.y) < 8)
			return true;
		
		// check vertical move
		if (start.y == end.y && Math.abs(end.x-start.x) < 8)
			return true;
		
		return false;
	}

	@Override
	public ArrayList<Point> getAvailDestination() {
		return null;
	}
}
