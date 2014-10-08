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
	public boolean checkDestination(Point cusPos) {
		
		return false;
	}

	@Override
	public ArrayList<Point> getAvailDestination() {
		return null;
	}
}
