package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Piece;

public class King extends Piece{

	public King(int team) {
		super(team);
		this.displayChar = 'K';
		this.name = "King";
	}

	@Override
	public boolean checkDestination(Point pos, Point curPos) {
		if (!preCheckDestination(pos, curPos))
			return false;
		
		// check 1 move around
		if (Math.abs(curPos.x-pos.x) < 2 && Math.abs(curPos.y-pos.y) < 2 )
			return true;
		return false;
		
	}

	@Override
	public ArrayList<Point> getAvailDestination() {
		// TODO Auto-generated method stub
		return null;
	}
}
