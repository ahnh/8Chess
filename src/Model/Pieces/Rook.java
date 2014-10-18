package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Move;
import Model.Piece;

public class Rook extends Piece {

	public Rook(int team) {
		super(team, 'R', "Rook");
	}

	@Override
	public boolean checkDestination(Move move) {
		if (!preCheckDestination(move))
			return false;

		Point start = move.getStart();
		Point end = move.getEnd();

		if (start.distance(end) > 7)
			return false;

		// check horizontal move
		if (start.x == end.x && Math.abs(end.y - start.y) < 8)
			return true;

		// check vertical move
		if (start.y == end.y && Math.abs(end.x - start.x) < 8)
			return true;

		return false;
	}

	@Override
	public ArrayList<Point> getAvailDestination() {
		return null;
	}

	@Override
	public void afterMove() {
		hasMoved = true;
	}
	
	
}
