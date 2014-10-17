package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Move;
import Model.Piece;

public class Pawn extends Piece {
	private boolean hasMoved;
	
	public Pawn(int team) {
		super(team,'P',"Pawn");
		hasMoved = false;
	}

	@Override
	public boolean checkDestination(Move move) {
		if (!preCheckDestination(move))
			return false;
		// team 0 = white
		// team 1 = black
		int direction = -1;
		if (team % 2 == 0)
			direction = 1;
		
		Point start = move.getStart();
		Point end = move.getEnd();
		
		if (start.distance(end) > 2)
			return false;
		
		// Pawn can only move 2 spaces on it's first turn
		if (move.getDistanceY() == 2 && hasMoved)
			return false;
		
		// Pawn can't move 2 spaces horizontally
		if (move.getDistanceX() > 1)
			return false;
		
		// Pawn can't move backwards
		if (start.y != end.y+(direction * move.getDistanceY()))
			return false;
		
		return true;
	}
	@Override
	public void afterMove() {
		hasMoved = true;
	}

	@Override
	public ArrayList<Point> getAvailDestination() {
		return null;
	}

}
