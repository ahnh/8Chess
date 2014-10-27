package TestNG;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;



import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
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

	
	@Test
	public void Promotion() 
	{
		Board board = new Board();
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
	public void CollisionPawn(){
		Board board = new Board();
		

		Stack<Move> moves = new Stack();
		
		// Initialize a pawn
		board.getTile(new Point(0,0)).setPiece(new Pawn(1));
		
		//Set a Piece directly in front of pawn
		board.getTile(new Point(0,1)).setPiece(new Pawn(1));		
		//Set the Move
		Move move = new Move(new Point(0, 0), new Point(0, 1), board.getTile(new Point(0,0)).getPiece());	
		moves.push(move);
		
		Rule collisionMove = new Model.Rules.CollisionMove();
		boolean actual = (collisionMove.checkMove(board, moves)==Model.Rule.VALID_MOVE);
		//boolean actual = (()== Model.Rule.VALID_MOVE);
		//Expecting False	
		Assert.assertEquals(actual,false,"Pawn must not be able to move on this square");
	}
	
	@Test
	public void CollisionRook(){
		Board board = new Board();
		

		Stack<Move> moves = new Stack();
		
		// Initialize a rook
		board.getTile(new Point(0,0)).setPiece(new Rook(1));
		
		//Set a Piece directly in front of pawn
		board.getTile(new Point(0,1)).setPiece(new Pawn(1));		
		//Set the Move
		Move move = new Move(new Point(0, 0), new Point(0, 1), board.getTile(new Point(0,0)).getPiece());	
		moves.push(move);
		
		Rule collisionMove = new Model.Rules.CollisionMove();
		boolean actual = (collisionMove.checkMove(board, moves)==Model.Rule.VALID_MOVE);
		//boolean actual = (()== Model.Rule.VALID_MOVE);
		//Expecting False	
		Assert.assertEquals(actual,false,"Pawn must not be able to move on this square");
	}	
	@Test
	public void CollisionBishop(){
		Board board = new Board();
		

		Stack<Move> moves = new Stack();
		
		// Initialize a bishop
		board.getTile(new Point(0,0)).setPiece(new Bishop(1));
		
		//Set a Piece directly in front of pawn
		board.getTile(new Point(1,1)).setPiece(new Pawn(1));		
		//Set the Move
		Move move = new Move(new Point(0, 0), new Point(1, 1), board.getTile(new Point(0,0)).getPiece());	
		moves.push(move);
		
		Rule collisionMove = new Model.Rules.CollisionMove();
		boolean actual = (collisionMove.checkMove(board, moves)==Model.Rule.VALID_MOVE);
		//boolean actual = (()== Model.Rule.VALID_MOVE);
		//Expecting False	
		Assert.assertEquals(actual,false,"Pawn must not be able to move on this square");
	}
	@Test
	public void CollisionKingDiagonal(){
		Board board = new Board();
	
		
		Stack<Move> moves = new Stack();
		
		// Initialize a king
		board.getTile(new Point(0,0)).setPiece(new King(1));
		
		//Set a Piece directly in front of pawn
		board.getTile(new Point(1,1)).setPiece(new Pawn(1));		
		//Set the Move
		Move move = new Move(new Point(0, 0), new Point(1, 1), board.getTile(new Point(0,0)).getPiece());	
		moves.push(move);
		
		Rule collisionMove = new Model.Rules.CollisionMove();
		boolean actual = (collisionMove.checkMove(board, moves)==Model.Rule.VALID_MOVE);
		//boolean actual = (()== Model.Rule.VALID_MOVE);
		//Expecting False	
		Assert.assertEquals(false,actual,"Pawn must not be able to move on this square");
	}	
	@Test
	public void CollisionKingOrthogonal(){
		Board board = new Board();
		

		Stack<Move> moves = new Stack();
		
		// Initialize a king
		board.getTile(new Point(0,0)).setPiece(new King(1));
		
		//Set a Piece directly in front of pawn
		board.getTile(new Point(0,1)).setPiece(new Pawn(1));		
		//Set the Move
		Move move = new Move(new Point(0, 0), new Point(0, 1), board.getTile(new Point(0,0)).getPiece());	
		moves.push(move);
		
		Rule collisionMove = new Model.Rules.CollisionMove();
		boolean actual = (collisionMove.checkMove(board, moves)==Model.Rule.VALID_MOVE);
		//boolean actual = (()== Model.Rule.VALID_MOVE);
		//Expecting False	
		Assert.assertEquals(false,actual,"Pawn must not be able to move on this square");
	}
	@Test
	public void Check(){
		Board board = new Board();
		

		Stack<Move> moves = new Stack();
		
		// Initialize a Rook
		board.getTile(new Point(0,0)).setPiece(new Rook(1));
		
		//Set an opposing King Diagonal of the rook
		board.getTile(new Point(1,7)).setPiece(new King(2));		
		//Set the Move
		Move move = new Move(new Point(1, 7), new Point(0, 7), board.getTile(new Point(0,0)).getPiece());	
		moves.push(move);
		
		Rule checkMove = new Model.Rules.Check();
		boolean actual = (checkMove.checkMove(board, moves)==Model.Rule.INVALID_MOVE);
		//boolean actual = (()== Model.Rule.VALID_MOVE);
		//Expecting True, king should not be allowed to move in check, if check is returned than state is correct
		Assert.assertEquals(actual,true,"King is not allowed to place himself in check");
	}
	
	@Test
	public void CheckMate(){
		Board board = new Board();
		

		Stack<Move> moves = new Stack();
		
		// Initialize Rooks
		board.getTile(new Point(0,0)).setPiece(new Rook(1));
		board.getTile(new Point(0,1)).setPiece(new Rook(1));	
		board.getTile(new Point(0,2)).setPiece(new Rook(1));				

		//Set an opposing King
		board.getTile(new Point(1,1)).setPiece(new King(2));	


		//Set the Move
		Move move = new Move(new Point(1, 7), new Point(0, 7), board.getTile(new Point(0,0)).getPiece());	
		moves.push(move);
		
		Rule checkmate = new Model.Rules.CheckMate();
		boolean actual = (checkmate.checkMove(board, moves)==Model.Rule.GAME_OVER_T2);
		//boolean actual = (()== Model.Rule.VALID_MOVE);
		//Expecting True, king should not be allowed to move in check, if check is returned than state is correct
		Assert.assertEquals(actual,true,"King is supposed to be flagged for checkmate");
	}
	
	@Test
	public void StaleMate(){
		Board board = new Board();
		

		Stack<Move> moves = new Stack();
		
		// Initialize Rooks
		board.getTile(new Point(0,0)).setPiece(new Rook(1));
		board.getTile(new Point(2,0)).setPiece(new Rook(1));		
		board.getTile(new Point(0,2)).setPiece(new Rook(1));				

		//Set an opposing King
		board.getTile(new Point(1,1)).setPiece(new King(2));	
		
		//Set King's AfterMove so it isn't allowed to castle ie escape this tight hold of rooks
		board.getTile(new Point(1,1)).getPiece().afterMove();

		//Set the Move

		
		Rule stalemate = new Model.Rules.Stalemate();
		boolean actual = (stalemate.checkMove(board, moves)==Model.Rule.GAME_OVER_TIE);
		
		//boolean actual = (()== Model.Rule.VALID_MOVE);
		//Expecting True, king should not be allowed to move in check, if check is returned than state is correct
		Assert.assertEquals(actual,true,"King is supposed to be flagged for stalemate");
	}
	
	@Test
	public void Absorb(){
		Board board = new Board();
		

		Stack<Move> moves = new Stack();
		
		Rule absorb = new Model.Rules.Absorb();
		
		List<Piece> pieceList= new ArrayList<Piece>();
		pieceList.add(new Rook(1));
		pieceList.add(new Queen(1));		
		pieceList.add(new Knight(1));			
		pieceList.add(new Bishop(1));
		

		//Test Rook Absorb
		Piece testPiece= new Rook(1);

		for(int i=0;i<pieceList.size();i++)		
		{
			board.getTile(new Point(0,0)).setPiece(testPiece); 
			if(testPiece.getName()!=pieceList.get(i).getName())
			{
			//Spawn Second Piece at Location
			board.getTile(new Point(0,1)).setPiece(pieceList.get(i));			
			Move move  = new Move(new Point(0, 0), new Point(0, 1), testPiece);	
			moves.push(move);			
			
			//Check the Rule
			absorb.checkMove(board, moves);
			//True = no match
			boolean actual = (board.getTile(new Point(0,1)).getPiece().getName()!=testPiece.getName());
			moves.pop();
			
			if(!actual)
			Assert.assertEquals(actual,true,"Absorb did not work with:"+testPiece.getName()+ "against:"+board.getTile(new Point(0,1)).getPiece().getName()+"is supposed to be flagged for stalemate");
			
			}
		}
		
		//Test Queen Absorb
		testPiece= new Queen(1);
		
		for(int i=0;i<pieceList.size();i++)		
		{
			board.getTile(new Point(0,0)).setPiece(testPiece); 
			if(testPiece.getName()!=pieceList.get(i).getName())
			{
			//Spawn Second Piece at Location
			board.getTile(new Point(0,1)).setPiece(pieceList.get(i));			
			Move move  = new Move(new Point(0, 0), new Point(0, 1), testPiece);	
			moves.push(move);			
			
			//Check the Rule
			absorb.checkMove(board, moves);
			//True = no match
			boolean actual = (board.getTile(new Point(0,1)).getPiece().getName()!=testPiece.getName());
			moves.pop();
			
			if(!actual)
			Assert.assertEquals(actual,true,"Absorb did not work with:"+testPiece.getName()+ "against:"+board.getTile(new Point(0,1)).getPiece().getName()+"is supposed to be flagged for stalemate");
			
			}
		}		
		
		//Test Bishop Absorb
		testPiece= new Queen(1);
		
		for(int i=0;i<pieceList.size();i++)		
		{
			board.getTile(new Point(0,0)).setPiece(testPiece); 
			if(testPiece.getName()!=pieceList.get(i).getName())
			{
			//Spawn Second Piece at Location
			board.getTile(new Point(1,1)).setPiece(pieceList.get(i));			
			Move move  = new Move(new Point(0, 0), new Point(1, 1), testPiece);	
			moves.push(move);			
			
			//Check the Rule
			absorb.checkMove(board, moves);
			//True = no match
			boolean actual = (board.getTile(new Point(1,1)).getPiece().getName()!=testPiece.getName());
			moves.pop();
			
			if(!actual)
			Assert.assertEquals(actual,true,"Absorb did not work with:"+testPiece.getName()+ "against:"+board.getTile(new Point(1,1)).getPiece().getName()+"is supposed to be flagged for stalemate");
			
			}
		}	
		
		//Test Knight Absorb
		testPiece= new Knight(1);
		
		for(int i=0;i<pieceList.size();i++)		
		{
			board.getTile(new Point(0,0)).setPiece(testPiece); 
			if(testPiece.getName()!=pieceList.get(i).getName())
			{
			//Spawn Second Piece at Location
			board.getTile(new Point(1,2)).setPiece(pieceList.get(i));			
			Move move  = new Move(new Point(0, 0), new Point(1, 2), testPiece);	
			moves.push(move);			
			
			//Check the Rule
			absorb.checkMove(board, moves);
			//True = no match
			boolean actual = (board.getTile(new Point(1,2)).getPiece().getName()!=testPiece.getName());
			moves.pop();
			
			if(!actual)
			Assert.assertEquals(actual,true,"Absorb did not work with:"+testPiece.getName()+ "against:"+board.getTile(new Point(1,1)).getPiece().getName()+"is supposed to be flagged for stalemate");
			}
		}
		
	}	
	
}
