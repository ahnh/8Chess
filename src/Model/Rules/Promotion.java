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
		
		for ( int i = 0; i < board.getWidth(); i++ ){
			
			if ( board.getTile(new Point(i, 0)).getPiece().getName().compareTo("Pawn") == 0 ||
				 board.getTile(new Point(i, board.getHeight()-1)).getPiece().getName().compareTo("Pawn") == 0 ){
				
				return Rule.PROMOTION;
			}
		}
		
		return Rule.VALID_MOVE;
	}

}
