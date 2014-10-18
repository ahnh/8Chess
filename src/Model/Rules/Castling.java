package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Rule;

public class Castling extends Rule {
	public Castling() {
		
		super( "Castling is not allowed", new String[][] { {"Do you want to castle?"}, { "Yes", "No" } } );
	}
	
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		// TODO Auto-generated method stub
		return Rule.VALID_MOVE;
	}

}
