package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Move;
import Model.Piece;

public class HobbitPawn extends Piece {

	public HobbitPawn(int team) {
		super(team, 'H', "Pawn");
	}
	
	/*
	 * Hobbit Pawns may move 1 space orthogonally (forwards, sideways, backwards)
	 * They may capture 1 space diagonally forwards and backwards
	 */
	@Override
	public boolean checkDestination(Move move) {
		if (!preCheckDestination(move))
			return false;
		
		if (move.getDistanceX() <= 1 && move.getDistanceY() <=1)
			return true;
		
		return false;
	}
	
	@Override
	public ArrayList<Point> getAvailDestination() {
		// TODO Auto-generated method stub
		return null;
	}

}
