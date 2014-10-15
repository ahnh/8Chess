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
		
		if (!movingPiece.getName().equalsIgnoreCase("pawn") ) {
			return Rule.VALID_MOVE;
		}
		// Check if last move was made by a pawn.
		// Check if pawn moved 2 spaces forward
		// Check if pawn is directly beside (horizontally) our pawn
		// Check if move is being made diagonally behind the other pawn
			// ALLOW EN Pessant... also remove the other pawn.
		return Rule.VALID_MOVE;
		
	}

	@Override
	public int checkState(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

}
