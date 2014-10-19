package Model.Pieces;

import java.awt.Point;

import Model.Move;
import Model.Piece;

public class RookKnight extends Piece {

	public RookKnight(int team) {
		super(team, 't', "RookKnight");
	}

	@Override
	public boolean checkDestination(Move move) {
		if (!preCheckDestination(move))
			return false;

		Point start = move.getStart();
		Point end = move.getEnd();

		int hDifference = Math.max(start.x, end.x) - Math.min(start.x, end.x);
		int vDifference = Math.max(start.y, end.y) - Math.min(start.y, end.y);

		if ((hDifference == 2 && vDifference == 1)
				| (hDifference == 1 && vDifference == 2))
			return true;

		else if (start.distance(end) > 7)
			return false;

		// check horizontal move
		else if (start.x == end.x && Math.abs(end.y - start.y) < 8)
			return true;

		// check vertical move
		else if (start.y == end.y && Math.abs(end.x - start.x) < 8)
			return true;

		return false;
	}

	@Override
	public void afterMove() {
		hasMoved = true;
	}

}
