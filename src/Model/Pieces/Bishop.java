package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Piece;

public class Bishop extends Piece{
	public Bishop(int team, Point curPos) {
		super(team, curPos);
		this.displayChar = 'B';
		this.name = "Bishop";
	}

	@Override
	public boolean checkDestination(Point cusPos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Point> getAvailDestination() {
		// TODO Auto-generated method stub
		return null;
	}
}
