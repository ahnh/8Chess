package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Rule;

public class Castling extends Rule {
	public Castling() {
		super();
	}
	
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		// TODO Auto-generated method stub
		return Rule.VALID_MOVE;
	}

	@Override
	public int checkState(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

}
