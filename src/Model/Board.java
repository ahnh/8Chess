package Model;

import java.awt.Point;
import java.util.ArrayList;

public class Board {
	private Piece[][] board;
	private ArrayList<Piece> pieces;

	public Board() {
		// TODO: depends on variants
		board = new Piece[8][8];
		pieces = new ArrayList<Piece>();
		clearBoard();
	}

	public void update() {

	}

	public void setup() {
		// depends on the variants?
		// classic
		// 8x8 board

		createBoardWithPieces();
		addPieces();
	}

	private void createBoardWithPieces() {
		// create white pawn
		for (int col = 0; col < 8; col++) {
			int row = 1;
			// white pawn
			board[row][col] = new Piece(0, 5, new Point(row, col));
			// black pawn
			board[row + 5][col] = new Piece(1, 5, new Point(row + 5, col));
		}

	}

	public void clearBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = null;
			}
		}
	}

	public void addPieces() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null)
					pieces.add(board[i][j]);
			}
		}

	}

	public void printBoard() {
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				if (j == 0) {
					System.out.print(i + " ");
				}
				if (board[i][j] == null)
					System.out.print(".");
				else
					System.out.print(board[i][j].getPieceName());
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
}
