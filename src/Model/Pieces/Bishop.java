package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Piece;

public class Bishop extends Piece{
	public Bishop(int team) {
		super(team);
		this.displayChar = 'B';
		this.name = "Bishop";
	}

	@Override
	public boolean checkDestination(Point pos, Point curPos) {
		if (!preCheckDestination(pos, curPos))
			return false;
		
		//check diagonal move
		if (Math.abs(pos.x - curPos.x) < 8 && Math.abs(pos.x - curPos.x) == Math.abs(pos.y - curPos.y))
			return true;
		
		return false;
	}

	@Override
	public ArrayList<Point> getAvailDestination() {
		// TODO Auto-generated method stub
		return null;
	}
}
