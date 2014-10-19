package Model.Pieces;

import java.awt.Point;

import Model.Move;
import Model.Piece;

public class Bishop extends Piece {
	public Bishop(int team) {
		super(team, 'B', "Bishop");
	}

	@Override
	public boolean checkDestination(Move move) {
		if (!preCheckDestination(move))
			return false;

		Point start = move.getStart();
		Point end = move.getEnd();

		// check diagonal move
		if (Math.abs(end.x - start.x) < 8
				&& Math.abs(end.x - start.x) == Math.abs(end.y - start.y))
			return true;

		return false;
	}

}
