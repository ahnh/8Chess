package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Move;
import Model.Piece;

public class Pawn extends Piece {
	public Pawn(int team) {
		super(team, 'P', "Pawn");
	}

	@Override
	public boolean checkDestination(Move move) {
		if (!preCheckDestination(move))
			return false;
		int direction = 1;
		if (team % 2 == 0)
			direction = -1;

		Point start = move.getStart();
		Point end = move.getEnd();

		if (start.distance(end) > 2)
			return false;

		// Pawn can only move 2 spaces on it's first turn
		if (move.getDistanceY() == 2 && hasMoved)
			return false;

		// Pawn can't move horizontally
		if (move.getDistanceY() == 0)
			return false;

		// Pawn can't move backwards
		if (start.y != end.y + (direction * move.getDistanceY()))
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
