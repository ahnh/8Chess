package Model.Pieces;

import java.awt.Point;

import Model.Move;
import Model.Piece;

public class StationaryKing extends Piece {

	public StationaryKing(int team) {
		super(team, 'K', "King");
	}

	@Override
	public boolean checkDestination(Move move) {
		if (!preCheckDestination(move))
			return false;

		Point start = move.getStart();
		Point end = move.getEnd();

		return false;

	}


	@Override
	public void afterMove() {
		hasMoved = true;
	}

}
