package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Piece;

public class Queen extends Piece{

	public Queen(int team) {
		super(team);
		this.displayChar = 'Q';
		this.name = "Queen";
	}

	@Override
	public boolean checkDestination(Point pos, Point curPos) {
		if (!preCheckDestination(pos, curPos))
			return false;
		
		// check diagonal move
		if (Math.abs(pos.x - curPos.x) < 8 && Math.abs(pos.x - curPos.x) == Math.abs(pos.y - curPos.y))
			return true;
		
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
