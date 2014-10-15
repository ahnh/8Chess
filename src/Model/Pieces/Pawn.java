package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Move;
import Model.Piece;

public class Pawn extends Piece {

	public Pawn(int team) {
		super(team,'P',"Pawn");
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
		if (start.x == end.x && start.y == end.y+direction)
			return true;
		if (start.x != end.x && start.y == end.y+direction && Math.abs(end.x-start.x) == 1)
			return true;
		
		return false;
	}

	@Override
	public ArrayList<Point> getAvailDestination() {
		return null;
	}

}
