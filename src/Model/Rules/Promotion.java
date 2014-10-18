package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Rule;

public class Promotion extends Rule {

	public Promotion() {
		super( "Castling is not allowed",
				new String[][] { 
				{"What piece do you want to promote to?"}, 
				{   "Queen",
					"Rook",
					"Knight",
					"Bishop"
				}
				}
		);
	}
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		// TODO Auto-generated method stub
		return 0;
	}

}
