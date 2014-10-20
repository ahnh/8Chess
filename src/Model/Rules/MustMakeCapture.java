package Model.Rules;

import Model.Board;
import Model.Move;
import Model.Piece;
import Model.Rule;

import java.awt.Point;
import java.util.Stack;


public class MustMakeCapture extends Rule {
	
	public MustMakeCapture() {
		
		super( "Must capture enemies piece", null );
	}

	public int checkMove(Board board, Stack<Move> moves) {
		
		Move current = moves.peek();
		Piece endPiece = board.getTile( current.getEnd() ).getPiece();
		Piece currentPiece = current.getPiece();
		int currentTeam = currentPiece.getTeam();
		
		// Check if piece is already capturing
		if ( endPiece != null ){
			if ( currentTeam != endPiece.getTeam() ){
				
				return Rule.VALID_MOVE;
			}
		}
		
		// Check if any piece can capture
		for ( int i = 0; i < board.getHeight(); i++ ){
			for ( int j = 0; j < board.getWidth(); j++ ){
				
				currentPiece = board.getTile( new Point( j, i ) ).getPiece();
				
				if ( currentPiece != null ){
					if (currentPiece.getTeam() == currentTeam ){
						
						if ( canCapture( board, currentPiece, j, i ) ){
							
							return Rule.INVALID_MOVE;
						}
					}
				}
			}
		}
		
		return Rule.VALID_MOVE;
	}
	
	private boolean canCapture( Board board, Piece piece, int x, int y ){
		
		Stack<Move> moves = createMoveList( board, piece, x, y );
		Move move = null;
		
		move = moves.pop();
		
		while ( move != null ){
			
			if ( piece.getName() == "Pawn" ) {
				
				if ( (move.getStart().x != move.getEnd().x) && (move.getStart().y != move.getEnd().y) ){
					
					return true;
				}
			}
			else {
				
				if ( board.getTile( move.getEnd() ).getPiece() != null ) {
					
					return true;
				}
			}
			
			move = moves.pop();
		}
		
		return false;
	}
	
	// Get all potential places a piece can move
	private Stack<Move> createMoveList( Board board, Piece piece, int x, int y ){
		
		Stack<Move> moveList = new Stack<Move>();
		Rule collision = new CollisionMove();
		Move move = null;
		
		for (int i = 0; i < board.getHeight(); i++){
			for (int j = 0; j < board.getWidth(); j++){
				
				move = new Move(new Point(x, y), new Point(j,i), piece);
				moveList.push(move);
				
				if (!piece.checkDestination(move)
						&& collision.checkMove(board, moveList) != Rule.VALID_MOVE ){
					
					moveList.pop();
				}
			}
		}
		
		return moveList;
	}
}
