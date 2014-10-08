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
	public boolean checkDestination(Point cusPos) {
		return false;
	}

	@Override
	public ArrayList<Point> getAvailDestination() {
		return null;
	}

}
