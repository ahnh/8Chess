package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Piece;

public class Knight extends Piece{

	public Knight(int team, Point curPos) {
		super(team, curPos);
		this.displayChar = 'N';
		this.name = "Knight";

	}

	@Override
	public boolean checkDestination(Point pos) {
		if (!preCheckDestination(pos))
			return false;
		
		if (curPos.distance(pos) == 5.0f)
			return true;
		
		return false;
	}

	@Override
	public ArrayList<Point> getAvailDestination() {
		// TODO Auto-generated method stub
		return null;
	}

}
