package Model.Rules;

import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Rule;

public class Pawn_DiagonalMove extends Rule {
	public Pawn_DiagonalMove() {
		super();
	}
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		Move currentMove = moves.peek();
		
		if (currentMove.getPiece().getName().equalsIgnoreCase("pawn")
				// Diagonal
				&& (currentMove.getStart().x != currentMove.getEnd().x && currentMove.getStart().y != currentMove.getEnd().y)
				// Empty Space
				&& board.getTile(currentMove.getEnd()).getPiece() == null)
			return Rule.INVALID_MOVE;
		
		return Rule.VALID_MOVE;
	}
}
