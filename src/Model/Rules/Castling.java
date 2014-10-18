package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Piece;
import Model.Rule;

public class Castling extends Rule {
	public Castling() {
		
		super( "Castling is not allowed", new String[][] { {"Do you want to castle?"}, { "Yes", "No" } } );
	}
	
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		Move currentMove = moves.peek();
		currentMove.getPiece();
		Piece movingPiece = board.getTile(currentMove.getEnd()).getPiece();
		/*if (!(movingPiece.getName().compareTo("King") == 0))
			return Rule.VALID_MOVE;
		
		int currentTeam = movingPiece.getTeam();
		*/
		// find king
		// find rook
		// check if it is king?		
		// is king in check?
		// is king and rook moved?
		// is there another piece in between?

		
		System.out.println("hello world" + movingPiece);
		return Rule.VALID_MOVE;
	}

}
