package Model.Rules;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Piece;
import Model.Rule;
import Model.Tile;

//Class is used to see if a Piece can move to the location without hopping over any pieces

public class CollisionMove extends Rule {
	public CollisionMove() {
		super("Piece collides with another during movement", null);
	}

	@Override
	public int checkMove(Board board, Stack<Move> moves) {

		// Grab CurrentMove
		Move currentMove = moves.peek();
		// Find the Piece being Moved
		Piece movingPiece = board.getTile(currentMove.getStart()).getPiece();

		// Find out Current Piece's team
		int currentTeam = movingPiece.getTeam();

		int maxWidth = board.getWidth();
		int maxHeight = board.getHeight();

		Piece comparePiece;

		List<Move> pieceList = new ArrayList<Move>();
		// Grab a List of All Pieces

		// Grab all pieces excluding the one being moved
		for (int y = 0; y < maxHeight; y++)
			for (int x = 0; x < maxWidth; x++) {
				Tile tile = null;// = board.getTile(point);
				comparePiece = null;

				Point point = new Point(x, y);

				// Assign tile and Check for Null
				if ((tile = board.getTile(point)) != null)
					if (((comparePiece = tile.getPiece()) != null))
						if (comparePiece != movingPiece) {
							Move m = new Move(point, point, comparePiece);
							pieceList.add(m);
						}
			}

		// Check if our moving piece is a knight
		if (movingPiece.getName().contains("Knight"))  {
			if (movingPiece.checkDestination(currentMove)
					&& board.getTile(currentMove.getEnd()).getExists()) {

				if (board.getTile(currentMove.getEnd()).getPiece() == null)
					return Rule.VALID_MOVE;

				else if (board.getTile(currentMove.getEnd()).getPiece()
						.getTeam() != currentTeam)
					return Rule.VALID_MOVE;
			}
			return Rule.INVALID_MOVE;
		}

		else {
			// System.out.println("Testing Collision");

			// Left -> right
			// Top Left -> Bottom Right

			int startX = currentMove.getStart().x;// Math.min(currentMove.getStart().x,
													// currentMove.getEnd().x);
			int endX = currentMove.getEnd().x;// Math.max(currentMove.getStart().x,
												// currentMove.getEnd().x);

			int startY = currentMove.getStart().y;// Math.min(currentMove.getStart().y,
													// currentMove.getEnd().y);
			int endY = currentMove.getEnd().y;// Math.max(currentMove.getStart().y,
												// currentMove.getEnd().y);

			// System.out.println(" MinX ="+startX+"\n MaxX ="+endX);
			// System.out.println(" MinX ="+startY+"\n MaxX ="+endY);
			// System.out.println(" Piece List = "+ pieceList.size());
			// // We need to Check if any piece lies between our
			// Start-Destination not including the destination itself

			Point p1 = new Point(startX, startY);
			Point p2 = new Point(endX, endY);

			boolean isValid = false;
			// If it's vertical
			if (startX == endX)
				isValid = CheckVertical(pieceList, startY, endY, startX,
						currentMove);
			else if (startY == endY)
				isValid = CheckHorizontal(pieceList, startX, endX, startY,
						currentMove);
                        else{
				isValid = CheckDiagonal(pieceList, p1, p2, currentMove);
                        //Enpassant fix
                        //Pawns will set collision to false, if enpessant is true then
                        //it gets reset here
                        EnPessant ep = new EnPessant();
                        if(ep.moveIsEnPessantAttempt(board,moves) )
                            isValid=true;
                        
                        }

			if (isValid && board.getTile(p2).getExists())
				return Rule.VALID_MOVE;
			else
				return Rule.INVALID_MOVE;

		}

	}

	// Constant= horizontal point
	boolean CheckVertical(List<Move> pieces, int start, int end, int constant,
			Move currentMove) {
		// Checking Downward Direction +
		if (start < end) {
			// Check if a Piece exists between start and end
			for (int i = start; i <= end; i++) {
				// System.out.println("Checking Point:"+constant+","+i);
				for (int p = 0; p < pieces.size(); p++) {
					// Piece exists at this location
					if (pieces.get(p).getStart().x == constant
							&& pieces.get(p).getStart().y == i) {
						// This is the end of our Movement
						if (i == currentMove.getEnd().y)
							// The piece blocking is non-friendly
							if (pieces.get(p).getPiece().getTeam() != currentMove
									.getPiece().getTeam())
								return true;

						return false;
					}

				}
			}
			return true;
		} else {
			// Check if a Piece exists between start and end
			for (int i = start; i >= end; i--) {
				// System.out.println("Checking Point:"+constant+","+i);
				for (int p = 0; p < pieces.size(); p++) {
					// Piece exists at this location
					if (pieces.get(p).getStart().x == constant
							&& pieces.get(p).getStart().y == i) {
						// This is the end of our Movement
						if (i == currentMove.getEnd().y)
							// The piece blocking is non-friendly
							if ((pieces.get(p).getPiece().getTeam() != currentMove
									.getPiece().getTeam()) && currentMove.getPiece().getName()!="Pawn")
                                                        {
								return true;}

						return false;
					}

				}
			}
			return true;
		}

	}

	boolean CheckHorizontal(List<Move> pieces, int start, int end,
			int constant, Move currentMove) {
		if (start < end) {
			// Check if a Piece exists between start and end
			for (int i = start; i <= end; i++) {

				// System.out.println("Checking Point:"+i+","+constant);

				for (int p = 0; p < pieces.size(); p++) {
					// Piece exists at this location
					if (pieces.get(p).getStart().x == i
							&& pieces.get(p).getStart().y == constant) {
						// This is the end of our Movement
						if (i == currentMove.getEnd().x)
							// The piece blocking is non-friendly
							if (pieces.get(p).getPiece().getTeam() != currentMove
									.getPiece().getTeam())
								return true;

						return false;
					}

				}
			}
			return true;
		} else {
			// Check if a Piece exists between start and end
			for (int i = start; i >= end; i--) {

				// System.out.println("Checking Point:"+i+","+constant);

				for (int p = 0; p < pieces.size(); p++) {
					// Piece exists at this location
					if (pieces.get(p).getStart().x == i
							&& pieces.get(p).getStart().y == constant) {
						// This is the end of our Movement
						if (i == currentMove.getEnd().x)
							// The piece blocking is non-friendly
							if (pieces.get(p).getPiece().getTeam() != currentMove
									.getPiece().getTeam())
								return true;

						return false;
					}

				}
			}
			return true;
		}

	}

	boolean CheckDiagonal(List<Move> pieces, Point start, Point end,
			Move currentMove) {

		// Diagonal moves
		// Check if a Piece exists between start and end

		// ++
		if ((start.x < end.x) && (start.y < end.y)) {
			int difference = end.x - start.x;

			for (int i = 0; i <= difference; i++) {
				// System.out.println("Checking Point:"+i+","+i);
				for (int p = 0; p < pieces.size(); p++) {
					// Piece exists at this location
					if (pieces.get(p).getStart().x == start.x + i
							&& pieces.get(p).getStart().y == start.y + i) {
						// This is the end of our Movement
						if (start.x + i == currentMove.getEnd().x
								&& start.y + i == currentMove.getEnd().y)
							// The piece blocking is non-friendly
							if (pieces.get(p).getPiece().getTeam() != currentMove
									.getPiece().getTeam())
								return true;

                                                if(currentMove.getPiece().getName()!="Pawn")
						return false;
					}

				}
			}
                        if(currentMove.getPiece().getName()=="Pawn")
                            return false;
                        else
                            return true;
		}
		// +-
		else if ((start.x < end.x) && (start.y > end.y)) {
			int difference = end.x - start.x;

			for (int i = 0; i <= difference; i++) {
				// System.out.println("Checking Point:"+i+","+i);
				for (int p = 0; p < pieces.size(); p++) {
					// Piece exists at this location
					if (pieces.get(p).getStart().x == start.x + i
							&& pieces.get(p).getStart().y == start.y - i) {
						// This is the end of our Movement
						if (start.x + i == currentMove.getEnd().x
								&& start.y - i == currentMove.getEnd().y)
							// The piece blocking is non-friendly
							if (pieces.get(p).getPiece().getTeam() != currentMove
									.getPiece().getTeam())
								return true;

                                        
                                                if(currentMove.getPiece().getName()!="Pawn")
						return false;
					}

				}
			}
                        if(currentMove.getPiece().getName()=="Pawn")
                            return false;
                        else
                            return true;
		}
		// -+
		else if ((start.x > end.x) && (start.y < end.y)) {
			int difference = start.x - end.x;

			for (int i = 0; i <= difference; i++) {
				// System.out.println("Checking Point:"+i+","+i);
				for (int p = 0; p < pieces.size(); p++) {
					// Piece exists at this location
					if (pieces.get(p).getStart().x == start.x - i
							&& pieces.get(p).getStart().y == start.y + i) 
                                        {
						// This is the end of our Movement
						if (start.x - i == currentMove.getEnd().x
								&& start.y + i == currentMove.getEnd().y)
							// The piece blocking is non-friendly
							if (pieces.get(p).getPiece().getTeam() != currentMove
									.getPiece().getTeam())
								return true;
                                               // if(currentMove.getPiece().getName()!="Pawn")
						return false;
					}

				}
			}
                        if(currentMove.getPiece().getName()=="Pawn")
                            return false;
                        else
                            return true;
		}

		// --
		else if ((start.x > end.x) && (start.y > end.y)) {
			int difference = start.x - end.x;

			for (int i = 0; i <= difference; i++) 
                        {
				// System.out.println("Checking Point:"+i+","+i);
				for (int p = 0; p < pieces.size(); p++) {
					// Piece exists at this location
					if (pieces.get(p).getStart().x == start.x - i
							&& pieces.get(p).getStart().y == start.y - i) {
						// This is the end of our Movement
						if (start.x - i == currentMove.getEnd().x
								&& start.y - i == currentMove.getEnd().y)
                                                {
							// The piece blocking is non-friendly
							if (pieces.get(p).getPiece().getTeam() != currentMove
									.getPiece().getTeam())
								return true;
                                                     
                                                         
                                                }

                                                
						return false;
					}

				}
			}
                        //Enpassant Fix...
                        EnPessant ep = new EnPessant();
                        
                        if(currentMove.getPiece().getName()=="Pawn")
                            return false;
                        else
                            return true;
		}

		return true;
	}

}
