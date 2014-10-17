package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Rule;

public class Castling extends Rule {
	public Castling() {
		super("Castleing attempted");
	}
	
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		// TODO Auto-generated method stub
		return Rule.VALID_MOVE;
	}

}
