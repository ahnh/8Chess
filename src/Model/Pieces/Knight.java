package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Move;
import Model.Piece;

public class Knight extends Piece{

	public Knight(int team) {
		super(team,'N',"Knight");

	}

	@Override
	public boolean checkDestination(Move move) {
		if (!preCheckDestination(move))
			return false;
		
		Point start = move.getStart();
		Point end = move.getEnd();
		
		if (start.distance(end) == 5.0f)
			return true;
		
		return false;
	}

	@Override
	public ArrayList<Point> getAvailDestination() {
		// TODO Auto-generated method stub
		return null;
	}

}
