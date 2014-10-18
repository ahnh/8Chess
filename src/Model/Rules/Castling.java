package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Piece;
import Model.Rule;

public class Castling extends Rule {
	public Castling() {
		super( "Castling is not allowed", null);
	}
	
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		Move currentMove = moves.peek();
		Piece currentPiece = currentMove.getPiece();
		int currentTeam = currentPiece.getTeam();
		int row = currentTeam % 2 == 0 ? 0 : 7;
		// check if the piece is King
		if (!(currentPiece.getName().compareTo("King") == 0))
			return Rule.VALID_MOVE;
		
		// check if king has moved
		if (currentPiece.hasMoved())
			return Rule.VALID_MOVE;
		
		if (currentMove.getDistanceY() < 2 && currentMove.getDistanceX() < 2)
			return Rule.VALID_MOVE;
		
		if (currentMove.getDistanceY() > 0 || currentMove.getDistanceX() != 2)
			return Rule.VALID_MOVE;
		
		// check valid move
		int col = currentMove.getStart().x;
		Piece Rook = null;
		Point newRookLocation = new Point(currentMove.getEnd().x, currentMove.getEnd().y);
		Point oldRookLocation = null;
		
		// don't like this section but works for now. checking the move toward it's destination
		// if there is piece, the move shouldn't be made.
		if (currentMove.getPureDistanceX() > 0)
		{
			for (int i = 1; i < 4; i++)
				if (board.getTile(new Point(i, row)).getPiece() != null)
					return Rule.VALID_MOVE;
			if (board.getTile(new Point(0, row)).getPiece().getName().compareTo("Rook") == 0)
			{
				Rook = board.getTile(new Point(0, row)).getPiece();
				newRookLocation.x++;
				oldRookLocation = new Point(0, row);
			}
		}
		else
		{
			for (int i = 5; i < 7; i++)
				if (board.getTile(new Point(i, row)).getPiece() != null)
					return Rule.VALID_MOVE;
			if (board.getTile(new Point(7, row)).getPiece().getName().compareTo("Rook") == 0)
			{
				Rook = board.getTile(new Point(7, row)).getPiece();
				newRookLocation.x--;
				oldRookLocation = new Point(7, row);
			}
		}
		
		// check rook is moved or not.
		if (Rook == null || Rook.hasMoved())
			return Rule.VALID_MOVE;
		
        // move rook
		board.getTile(newRookLocation).setPiece(Rook);
        board.getTile(oldRookLocation).setPiece(null);
        //moves.push(new Move(oldRookLocation, newRookLocation, Rook));

		
		return Rule.VALID_MOVE;
	}

}
