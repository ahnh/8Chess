package Model.Pieces;

import java.awt.Point;

import Model.Move;
import Model.Piece;

public class BishopKnight extends Piece {
	public BishopKnight(int team) {
		super(team, 'B', "Bishop-Knight");
	}

	@Override
	public boolean checkDestination(Move move) {
		if (!preCheckDestination(move))
			return false;

		Point start = move.getStart();
		Point end = move.getEnd();

		int hDifference = Math.max(start.x, end.x) - Math.min(start.x, end.x);
		int vDifference = Math.max(start.y, end.y) - Math.min(start.y, end.y);

		// check diagonal move
		if (Math.abs(end.x - start.x) < 8
				&& Math.abs(end.x - start.x) == Math.abs(end.y - start.y))
			return true;

		else if ((hDifference == 2 && vDifference == 1)
				| (hDifference == 1 && vDifference == 2))
			return true;

		return false;
	}

}
