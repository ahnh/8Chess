package Model.Variants;

import java.awt.Point;
import java.util.List;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Variant;
import Model.Pieces.*;
import Model.Rules.*;

public class TestCheck extends Variant {
	public TestCheck(List<Board> boards) {
		super( 2 );	
		Board board = new Board();	
		initBoard(board);
		boards.add(board);
		// Add Rules
                rules.add(new CollisionMove());
		//rules.add(new Castling());
                rules.add(new Check());
                
                
	}
	private void initBoard(Board board) {
            TestCheck2(board);

                    
		
	}
        
        
        //King Not in Check, Surrounded by Pieces
        
        //Board doesn't need updating, just needs to check if any enemy piece can get to the king
        //if he moves to his desired location
        void TestCheck1(Board board)
        {
		// Team 1
		int team = 1;
		board.getTile(new Point(4,0)).setPiece(new King(team));
                


                
		// Team 2
		team = 2;
		board.getTile(new Point(5,1)).setPiece(new Rook(team));
		board.getTile(new Point(1,2)).setPiece(new Bishop(team));      
        }
        
        //Piece is blocking check,
        //Moving king behind piece should be allowed
         void TestCheck2(Board board)
        {
		// Team 1
		int team = 1;
		board.getTile(new Point(4,0)).setPiece(new King(team));
 		board.getTile(new Point(5,1)).setPiece(new Pawn(team));               
 		board.getTile(new Point(0,0)).setPiece(new Queen(team));

                
		// Team 2
		team = 2;
		board.getTile(new Point(5,4)).setPiece(new Rook(team));   
 		board.getTile(new Point(5,5)).setPiece(new Queen(team));   
                board.getTile(new Point(3,3)).setPiece(new Knight(team));
        }       
        
        
}
