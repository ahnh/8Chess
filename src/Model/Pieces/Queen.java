package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Piece;

public class Queen extends Piece{

	public Queen(int team, Point curPos) {
		super(team, curPos);
		this.displayChar = 'Q';
		this.name = "Queen";
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
