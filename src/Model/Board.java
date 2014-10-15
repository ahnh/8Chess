package Model;

import java.awt.Point;
import java.util.ArrayList;

public class Board {
	private Tile[][] board;
	private ArrayList<Tile> tiles;
	private int height;
	private int width;

	public Board() {
		// TODO: depends on variants
		board = new Tile[8][8];
		height = 8;
		width = 8;
	}
	
	public Board(int w, int h) {
		// TODO: depends on variants
		board = new Tile[w][h];
		height = h;
		width = w;
	}

	public void placePiece(Piece piece, Point pos) {
		Tile tile = new Tile(piece);
		board[pos.x][pos.y] = tile;
		tiles.add(tile);
	}
	
	public Tile getTile(Point pos) {
		if (board[pos.x][pos.y] != null){
			return board[pos.x][pos.y];
		} else {
			System.err.println("Attempted to access a position that doesn't exist on the board: " + pos.x + ", " + pos.y);
			return null;
		}
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void move(Move move) {
		Tile origin = board[move.getStart().x][move.getStart().y];
		Tile destination = board[move.getEnd().x][move.getEnd().y];
		Piece toMove = origin.getPiece();
		origin.setPiece(null);
		
		// TODO: Add Capture logic here
		destination.setPiece(toMove);
	}
	/*
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
	}*/
}
