package Model.Pieces;

import java.awt.Point;

import Model.Move;
import Model.Piece;

public class Knight extends Piece {

	public Knight(int team) {
		super(team, 'N', "Knight");
	}

	@Override
	public boolean checkDestination(Move move) {
		if (!preCheckDestination(move))
			return false;

		Point start = move.getStart();
		Point end = move.getEnd();

		// A KNight distance is actually...

		// Point origin = new Point(0,0);
		// Point dest = new Point (2,1);

		// System.out.println("KNIGHT MOVES:"+ origin.distance(dest)); //Answer
		// is 2.23606 etc

		// Check if the Difference in x is 2 and y is 1 or vice versa

		// Check Horizontal
		int hDifference = Math.max(start.x, end.x) - Math.min(start.x, end.x);
		int vDifference = Math.max(start.y, end.y) - Math.min(start.y, end.y);

		if ((hDifference == 2 && vDifference == 1)
				| (hDifference == 1 && vDifference == 2))
			return true;
		else
			return false;

		// if (start.distance(end) == 5.0f)
		// return true;

		// return false;
	}

}
