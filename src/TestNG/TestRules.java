package TestNG;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;

import java.awt.Point;
import java.util.Stack;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

import org.testng.annotations.Test;
import org.testng.*;

import Model.Board;
import Model.Move;
import Model.Piece;
import Model.Rule;
import Model.Pieces.*;

public class TestRules {
	Board board = new Board();
	
	@Test
	public void Promotion() 
	{
		Stack<Move> moves = new Stack();

		// Initialize a pawn in a position to move into promotion
		Piece pawn = new Pawn(0 /*team 1*/);
		board.getTile(new Point(0, board.getHeight() - 2)).setPiece(pawn);
		// Move the pawn into promotion
		Move promotion_move = new Move(new Point(0, board.getHeight() - 2), new Point(0, board.getHeight() - 1), pawn);
		board.move(promotion_move);
		moves.push(promotion_move);
		Rule promotion_rule = new Model.Rules.Promotion();
		
		Assert.assertEquals(promotion_rule.checkMove(board, moves),Rule.PROMOTION,"Pawn was not promoted when it should have been.");
		
		// Now try to promote to a queen
		moves.peek().action = Rule.PROMOTION;
		moves.peek().option = 1;
		moves.peek().optionSelected = true;
		promotion_rule.checkMove(board, moves);
		
		Assert.assertEquals(board.getTile( new Point(0, board.getHeight() - 1)).getPiece().getName(),"Queen","Pawn was not replaced by another piece even though promotion occurred.");
	}
	
	@Test
	public void Collision(){
		// Move non-knight over a piece
		
	}
}
