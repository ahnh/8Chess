package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Rule;

public class Stalemate extends Rule {

	public Stalemate() {
		super("Stalemate condition active");
	}
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		// Stalemate doesn't have any effect on a move. The check is done during checkState
		return Rule.VALID_MOVE;
	}
}
