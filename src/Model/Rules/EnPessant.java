package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.*;

import Model.Board;
import Model.Rule;

public class EnPessant extends Rule {
	public EnPessant(){
		super();
	}
	
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		Move currentMove = moves.peek();
		
		Piece movingPiece = board.getTile(currentMove.getStart()).getPiece();
		
		if (!movingPiece.getName().equalsIgnoreCase("pawn") )
			return Rule.VALID_MOVE;
		// Check if pawn made a diagonal move
		if (!(currentMove.getDistanceX() == 1 && currentMove.getDistanceY() == 1))
			return Rule.VALID_MOVE;
		
		// Diagonal move must not be a capture
		if (board.getTile(currentMove.getEnd()).getPiece() != null)
			return Rule.VALID_MOVE;
		
		// Check if last move was made by a pawn.
		Move lastMove = moves.get(moves.size());
		if (!lastMove.getPiece().getName().equalsIgnoreCase("pawn"))
			return Rule.INVALID_MOVE;
	
		// Check if last pawn moved 2 spaces forward
		if (lastMove.getDistanceY() < 2)
			return Rule.INVALID_MOVE;
		
		// Check if pawn is directly beside (horizontally) our pawn
		// Check if move is being made diagonally behind the other pawn
			// ALLOW EN Pessant... also remove the other pawn.
                
                //Grab a List of all pieces on the Board
                
                
                
		return Rule.VALID_MOVE;
		
	}
}
