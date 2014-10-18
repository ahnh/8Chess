package Model.Variants;

import java.awt.Point;
import java.util.List;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Rule;
import Model.Variant;
import Model.Pieces.*;
import Model.Rules.Castling;
import Model.Pieces.Queen;
import Model.Rules.Check;
import Model.Rules.CheckMate;
import Model.Rules.CollisionMove;
import Model.Rules.Hobbit_Move;
import Model.Rules.Stalemate;

public class Hobbit extends Variant {

	public Hobbit(List<Board> boards) {
		super( 2 );
		
		Board board = new Board();
		
		initBoard(board);
		boards.add(board);
		
		// Add Rules
        move_Rules.add(new CollisionMove());
		move_Rules.add(new Castling());
		move_Rules.add(new Hobbit_Move());
		move_Rules.add(new Check());
		
		state_Rules.add(new CheckMate());
		state_Rules.add(new Stalemate());
		state_Rules.add(new Castling());
	}
	public int checkMove(Board board, Stack<Move> moves, int currentTeam) {
		int returnVal = Rule.VALID_MOVE;
		for (int i = 0; i < move_Rules.size(); i++) {
			returnVal = move_Rules.get(i).checkMove(board, moves);
			if (returnVal != Rule.VALID_MOVE) {
				lastError = move_Rules.get(i).getError();
				break;
			}
		}
		return returnVal;
	}
	
	private void initBoard(Board board) {
		// Team 1
		int team = 2;
		board.getTile(new Point(0,0)).setPiece(new Rook(team));
		board.getTile(new Point(1,0)).setPiece(new Knight(team));
		board.getTile(new Point(2,0)).setPiece(new Bishop(team));
		board.getTile(new Point(3,0)).setPiece(new Queen(team));
		board.getTile(new Point(4,0)).setPiece(new King(team));
		board.getTile(new Point(5,0)).setPiece(new Bishop(team));
		board.getTile(new Point(6,0)).setPiece(new Knight(team));
		board.getTile(new Point(7,0)).setPiece(new Rook(team));
		for (int i = 0; i < board.getWidth(); i++) {
			board.getTile(new Point(i,1)).setPiece(new HobbitPawn(team));
		}
		
		// Team 2
		team = 1;
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
			board.getTile(new Point(i,6)).setPiece(new HobbitPawn(team));
		}
	}
}
