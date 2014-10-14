package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Piece;

public class Pawn extends Piece {

	public Pawn(int team, Point curPos) {
		super(team, curPos);
		this.displayChar = 'P';
		this.name = "Pawn";
	}

	@Override
	public boolean checkDestination(Point pos) {
		if (!preCheckDestination(pos))
			return false;
		// team 0 = white
		// team 1 = black
		int direction = -1;
		if (team % 2 == 0)
			direction = 1;
		
		if (curPos.distance(pos) > 2)
			return false;
		if (curPos.x == pos.x && curPos.y == pos.y+direction)
			return true;
		if (curPos.x != pos.x && curPos.y == pos.y+direction && Math.abs(pos.x-curPos.x) == 1)
			return true;
		
		return false;
	}

	@Override
	public ArrayList<Point> getAvailDestination() {
		return null;
	}

}
