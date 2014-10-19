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
						
						if ( canCapture( board, j, i ) ){
							
							return Rule.INVALID_MOVE;
						}
					}
				}
			}
		}
		
		return Rule.VALID_MOVE;
	}
	
	private boolean canCapture( Board board, int x, int y ){
		// destination set on the king
		/*enemyList = CreateEnemyList(board, currentTeam,
		playerKing.getStart());
		boolean isCheck = false;
		
		for (int x = 0; x < enemyList.size() && isCheck == false; x++) {
			Move move0 = enemyList.get(x);
			Stack moveStack = new Stack();
			moveStack.add(move0);
				if (move0.getPiece().getName() == "Pawn") {
					if (move0.getPiece().checkDestination(move0)
						&& ((move0.getStart().x != move0.getEnd().x) && (move0
								.getStart().y != move0.getEnd().y)))
					isCheck = true;
				} else {
					if (((CollisionMove.checkMove(board, moveStack)) == Rule.VALID_MOVE)
						&& enemyList.get(x).getPiece()
								.checkDestination(move0)) {
						isCheck = true;
					}
				}
		}*/
		return false;
	}
}
