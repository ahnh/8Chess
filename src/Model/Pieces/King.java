package Model.Pieces;

import java.awt.Point;

import Model.Move;
import Model.Piece;

public class King extends Piece {

	public King(int team) {
		super(team, 'K', "King");
	}

	@Override
	public boolean checkDestination(Move move) {
		if (!preCheckDestination(move))
			return false;

		Point start = move.getStart();
		Point end = move.getEnd();

		// Castling checking
		if (!hasMoved
				&& (Math.abs(start.x - end.x) == 2 && Math.abs(start.y - end.y) == 0))
			return true;
		// check 1 move around
		else if (Math.abs(start.x - end.x) < 2 && Math.abs(start.y - end.y) < 2)
			return true;

		return false;

	}

	@Override
	public void afterMove() {
		hasMoved = true;
	}

}
