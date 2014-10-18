package Model.Pieces;

import java.awt.Point;
import java.util.ArrayList;

import Model.Move;
import Model.Piece;

public class CheshireKing extends Piece {

	public CheshireKing(int team) {
		super(team, 'K', "King");
	}
        //Moves like Queen
	@Override
	public boolean checkDestination(Move move) 
        {
		if (!preCheckDestination(move))
			return false;

		Point start = move.getStart();
		Point end = move.getEnd();
                
                //King Move Code
                if(hasMoved)
                {
                // check 1 move around
		if (Math.abs(start.x - end.x) < 2 && Math.abs(start.y - end.y) < 2)
			return true;
		return false;
                }
                //Queen Move Code
                else
                {
		// check diagonal move
		if (Math.abs(end.x - start.x) < 8 && Math.abs(end.x - start.x) == Math.abs(end.y - start.y))
			return true;

		// check horizontal move
		if (start.x == end.x && Math.abs(end.y - start.y) < 8)
			return true;

		// check vertical move
		if (start.y == end.y && Math.abs(end.x - start.x) < 8)
			return true;

		return false;
                }
	}
        
	@Override
	public void afterMove() {
		hasMoved = true;
	}
        
	@Override
	public ArrayList<Point> getAvailDestination() {
		return null;
	}
}
