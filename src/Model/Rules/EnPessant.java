package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.*;

import Model.Board;
import Model.Rule;

public class EnPessant extends Rule {
	public EnPessant(){
		super("En Pessant is not allowed", null);
	}
	
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		Move currentMove = moves.peek();
		
		Piece movingPiece = board.getTile(currentMove.getStart()).getPiece();
		
		if (!EnPessant.moveIsEnPessantAttempt(board, moves))
			return Rule.VALID_MOVE;
		
		// Check if last move was made by a pawn.
		Move lastMove = moves.get(moves.size()-2);
		if (!lastMove.getPiece().getName().equalsIgnoreCase("pawn"))
			return Rule.INVALID_MOVE;
	
		// Check if last pawn moved 2 spaces forward
		if (lastMove.getDistanceY() < 2)
			return Rule.INVALID_MOVE;
		
		// Check if pawn is directly beside (horizontally) our pawn
		if (lastMove.getEnd().y == currentMove.getStart().y && lastMove.getEnd().distance(currentMove.getStart()) == 1) {
			// Check if move is being made diagonally behind the other pawn
			if (currentMove.getEnd().x == lastMove.getEnd().x){
				return Rule.VALID_MOVE;
			}
		}
                
		return Rule.INVALID_MOVE;
		
	}
	
	public static boolean moveIsEnPessantAttempt(Board board, Stack<Move> moves) {
		Move currentMove = moves.peek();
		
		Piece movingPiece = board.getTile(currentMove.getStart()).getPiece();
		
		if (!movingPiece.getName().equalsIgnoreCase("pawn") )
			return false;
		// Check if pawn made a diagonal move
		if (!(currentMove.getDistanceX() == 1 && currentMove.getDistanceY() == 1))
			return false;
		
		// Diagonal move must not be a capture
		if (board.getTile(currentMove.getEnd()).getPiece() != null)
			return false;
		
		return true;
	}
}
