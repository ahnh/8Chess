package Model.Pieces;

import java.awt.Point;

import Model.Move;
import Model.Piece;

public class JediKnight extends Piece {
	// Jedi Knight is allowed to move 3 steps horizontally or vertically
	// as well as it's ordinary 'L' shaped moves
	public JediKnight(int team) {
		super(team, 'J', "Knight");
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
				|| (hDifference == 1 && vDifference == 2))
			// Regular Knight Movement
			return true;
		else if ((vDifference == 0 && hDifference <= 3)
				|| (hDifference == 0 && vDifference <= 3))
			// Jedi Knight Addon
			return true;
		else
			return false;
	}

}
