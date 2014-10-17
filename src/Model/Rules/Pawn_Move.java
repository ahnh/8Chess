package Model.Rules;

import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Rule;

public class Pawn_Move extends Rule {
	public Pawn_Move() {
		super();
	}
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		Move currentMove = moves.peek();
		if (!currentMove.getPiece().getName().equalsIgnoreCase("pawn"))
			return Rule.VALID_MOVE;
		
		if (board.getTile(currentMove.getEnd()).getPiece() == null){
			// Not a capture. Disallow diagonal moves
			if (currentMove.getStart().x != currentMove.getEnd().x && currentMove.getStart().y != currentMove.getEnd().y)
				return Rule.INVALID_MOVE;
		} else {
			// Capture. Disallow  Vertical and Horizontal moves
			if (currentMove.getDistanceX() == 0 || currentMove.getDistanceY() == 0)
				return Rule.INVALID_MOVE;
		}
		
		return Rule.VALID_MOVE;
	}
}
