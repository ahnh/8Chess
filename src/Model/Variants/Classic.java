package Model.Variants;

import java.awt.Point;
import java.util.List;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Variant;
import Model.Pieces.*;
import Model.Rules.*;

public class Classic extends Variant {
	public Classic(List<Board> boards) {
		super( 2 );
		
		Board board = new Board();
		
		initBoard(board);
		boards.add(board);
		
		// Add Rules
		rules.add(new Castling());
	}
	private void initBoard(Board board) {
		int team = 1;
		// Team 1
		board.getTile(new Point(0,0)).setPiece(new Rook(team));
		board.getTile(new Point(1,0)).setPiece(new Knight(team));
		board.getTile(new Point(2,0)).setPiece(new Bishop(team));
		board.getTile(new Point(3,0)).setPiece(new Queen(team));
		board.getTile(new Point(4,0)).setPiece(new King(team));
		board.getTile(new Point(5,0)).setPiece(new Bishop(team));
		board.getTile(new Point(6,0)).setPiece(new Knight(team));
		board.getTile(new Point(7,0)).setPiece(new Rook(team));
		for (int i = 0; i < board.getWidth(); i++) {
			board.getTile(new Point(i,1)).setPiece(new Pawn(team));
		}
		
		// Team 2
		team = 2;
		board.getTile(new Point(0,7)).setPiece(new Rook(team));
		board.getTile(new Point(1,7)).setPiece(new Knight(team));
		board.getTile(new Point(2,7)).setPiece(new Bishop(team));
		board.getTile(new Point(3,7)).setPiece(new Queen(team));
		board.getTile(new Point(4,7)).setPiece(new King(team));
		board.getTile(new Point(5,7)).setPiece(new Bishop(team));
		board.getTile(new Point(6,7)).setPiece(new Knight(team));
		board.getTile(new Point(7,7)).setPiece(new Rook(team));
		// Pawns
		for (int i = 0; i < board.getWidth(); i++) {
			board.getTile(new Point(i,6)).setPiece(new Pawn(team));
		}
	}
}
