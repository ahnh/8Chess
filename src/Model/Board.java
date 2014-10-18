package Model;

import java.awt.Point;

public class Board {
	private Tile[][] board;
	private int height;
	private int width;
	public static final int STD_WIDTH = 8;
	public static final int STD_HEIGHT = 8;

	public Board() {
		this(Board.STD_WIDTH, Board.STD_HEIGHT);
	}
	
	public Board(int w, int h) {
		board = new Tile[w][h];
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				board[x][y] = new Tile();
			}
		}
		height = h;
		width = w;
	}

	public boolean checkMove(Move move, int currentTeam) {
		// Check that the piece can make the move
		return move.getPiece().checkDestination(move);
	}
	
	public void placePiece(Piece piece, Point pos) {
		board[pos.x][pos.y].setPiece(piece);
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
	
	public boolean compare( Board othBoard ){
		
		if ( othBoard.getHeight() == height && othBoard.getWidth() == width ){
			
			for(int i = 0; i < height; i++){
				for(int j = 0; j < width; j++){
					
					if ( board[j][i].getPiece().getDisplayChar() != othBoard.getTile(new Point(j,i)).getPiece().getDisplayChar() ){
						return false;
					}
				}
			}
			
			return true;
		}
			
		return false;
	}
	
	public void move(Move move) {
		Tile origin = board[move.getStart().x][move.getStart().y];
		Piece toMove = origin.getPiece();
		origin.setPiece(null);
		
		capturePiece(move.getEnd(), toMove);
		
		// Perform any piece-level operations after the move.
		if (toMove != null)
			toMove.afterMove();
	}
	
	public void capturePiece(Point position, Piece capturer) {
		Piece captured = board[position.x][position.y].getPiece();
		// TODO: Store captured piece in dead array
		board[position.x][position.y].setPiece(capturer);
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
