package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Piece;

public class Rook extends Piece{

	public Rook(int team, Point curPos) {
		super(team, curPos);
		this.displayChar = 'R';
		this.name = "Rook";
	}

	@Override
	public boolean checkDestination(Point pos) {
		if (!preCheckDestination(pos))
			return false;
		
		if (curPos.distance(pos) > 7)
			return false;
		
		// check horizontal move
		if (curPos.x == pos.x && Math.abs(pos.y-curPos.y) < 8)
			return true;
		
		// check vertical move
		if (curPos.y == pos.y && Math.abs(pos.x-curPos.x) < 8)
			return true;
		
		return false;
	}

	@Override
	public ArrayList<Point> getAvailDestination() {
		return null;
	}
}
