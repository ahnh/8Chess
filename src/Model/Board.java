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
		
		if ( pos.x == -1 || pos.y == -1 || pos.x >= width || pos.y >= height){
			
			return null;
		}
		if (board[pos.x][pos.y] != null) {
			return board[pos.x][pos.y];
		} else {
			System.err
					.println("Attempted to access a position that doesn't exist on the board: "
							+ pos.x + ", " + pos.y);
			return null;
		}
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public boolean compare(Board othBoard) {

		if (othBoard.getHeight() == height && othBoard.getWidth() == width) {

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {

					if (board[j][i].getPiece().getDisplayChar() != othBoard
							.getTile(new Point(j, i)).getPiece()
							.getDisplayChar()) {
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
		// TODO: Store captured piece in dead array
		//Piece captured = board[position.x][position.y].getPiece();
		board[position.x][position.y].setPiece(capturer);
	}
	
}
