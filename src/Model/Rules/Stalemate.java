package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Rule;

public class Stalemate extends Rule {

	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		// Stalemate doesn't have any effect on a move. The check is done during checkState
		return Rule.VALID_MOVE;
	}

	@Override
	public int checkState(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

}
