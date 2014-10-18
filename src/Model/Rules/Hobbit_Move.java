package Model.Rules;

import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Piece;
import Model.Rule;

public class Hobbit_Move extends Rule {

	public Hobbit_Move() {
		super("Hobbit can only move diagonally to capture", null);
	}
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		Move currentMove = moves.peek();
		if (!currentMove.getPiece().getName().equalsIgnoreCase("pawn"))
			return Rule.VALID_MOVE;
		Piece pieceInEndSpace = board.getTile(currentMove.getEnd()).getPiece();
		if ( pieceInEndSpace == null){
			// Not a capture. Disallow diagonal moves
			if (currentMove.getStart().x != currentMove.getEnd().x && currentMove.getStart().y != currentMove.getEnd().y)
				return Rule.INVALID_MOVE;
		} else if ( pieceInEndSpace.getTeam() == currentMove.getPiece().getTeam()) {
			// can't capture members of your own team
			return Rule.INVALID_MOVE;
		} else {
			// Capture. Disallow  Vertical and horizontal moves
			if (currentMove.getDistanceX() == 0 || currentMove.getDistanceY() == 0)
				return Rule.INVALID_MOVE;
		}
		
		return Rule.VALID_MOVE;
	}

}
