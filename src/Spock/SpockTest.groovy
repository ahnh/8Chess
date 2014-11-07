package Spock
import Model.*;
import Model.Pieces.*;

import java.awt.Point
import java.util.Stack;

import org.testng.Assert;
import org.testng.annotations.Test;

import spock.lang.*

class SpockTest extends spock.lang.Specification {
	def board
	def moves
	def move
	def collisionMove
	def setup() {
		board = new Board();
		moves = new Stack();
		collisionMove = new Model.Rules.CollisionMove();
	}
	
	def "PawnPromotionTest"() {
		given:
			// Initialize a pawn in a position to move into promotion
			// team 1
			def pawn = new Pawn(0)
			board.getTile(new Point(0, board.getHeight() - 2)).setPiece(pawn);
		
		
		when: "Move Pawn into promotion"
			// Move the pawn into promotion
			move = new Move(new Point(0, board.getHeight() - 2), new Point(0, board.getHeight() - 1), pawn)
			board.move(move);
			moves.push(move);
			def promotionRule = new Model.Rules.Promotion()
		
		then: "Expect Pawn to be promoted"
			promotionRule.checkMove(board, moves) == Rule.PROMOTION
		
		when: "Promoting Pawn to a Queen"
			// Now try to promote to a queen
			moves.peek().action = Rule.PROMOTION;
			moves.peek().option = 1;
			moves.peek().optionSelected = true;
			promotionRule.checkMove(board, moves);
		
		then: "Expect Pawn to be a Queen"
			board.getTile( new Point(0, board.getHeight() - 1)).getPiece().getName() == "Queen"
	}
	
	def "CollisionTestKnightMoveOverPawn"() {
		given:
			// Initialize a rook
			board.getTile(new Point(3,3)).setPiece(new Knight(1));
			
			// Initialize a pawns
			board.getTile(new Point(2,2)).setPiece(new Pawn(1));
			board.getTile(new Point(3,2)).setPiece(new Pawn(1));
			board.getTile(new Point(2,3)).setPiece(new Pawn(1));
		when: "Knight collide"
			//Set the Move
			move = new Move(new Point(3, 3), new Point(1, 2), board.getTile(new Point(3, 3)).getPiece())
			moves.push(move);
			
		then: "Move is valid"
			collisionMove.checkMove(board, moves) == Model.Rule.VALID_MOVE
	}
	
	def "CollisionTestPawn"() {
		given:
			// Initialize a pawn
			board.getTile(new Point(0,0)).setPiece(new Pawn(1));
			
			//Set a Piece directly in front of pawn
			board.getTile(new Point(0,1)).setPiece(new Pawn(1));
			//Set the Move
		when: "Pawn collide"
			move = new Move(new Point(0, 0), new Point(0, 1), board.getTile(new Point(0, 0)).getPiece())
			moves.push(move);
			
		then: "Move is invalid"
			collisionMove.checkMove(board, moves) != Model.Rule.VALID_MOVE
	}
	
	def "CollisionTestKnight"() {
		given:
			// Initialize a rook
			board.getTile(new Point(3,3)).setPiece(new Knight(1));
			
			// Initialize a pawns
			board.getTile(new Point(1,2)).setPiece(new Pawn(1));
		when: "Knight collide"
			//Set the Move
			move = new Move(new Point(3, 3), new Point(1, 2), board.getTile(new Point(3,3)).getPiece())
			moves.push(move);
			
		then: "Move is valid"
			collisionMove.checkMove(board, moves) != Model.Rule.VALID_MOVE
	}
	
	def "CollisionTestBishop"() {
		given:
			// Initialize a bishop
			board.getTile(new Point(0,0)).setPiece(new Bishop(1));
	
			// Initialize a pawn
			board.getTile(new Point(2,2)).setPiece(new Pawn(1));
		
		when: "Bishop collide"
			//Set the Move
			move = new Move(new Point(0, 0), new Point(5, 5), board.getTile(new Point(0, 0)).getPiece())
			moves.push(move);
			
		then: "Move is invalid"
			collisionMove.checkMove(board, moves) != Model.Rule.VALID_MOVE
	}
	
	def "CollisionTestRook"() {
		given:
			// Initialize a rook
			board.getTile(new Point(0,0)).setPiece(new Rook(1));
	
			//Set a Piece directly in front of Rook
			board.getTile(new Point(0,2)).setPiece(new Pawn(1));
		when: "Rook collide"
			//Set the Move
			move = new Move(new Point(0, 0), new Point(0, 5), board.getTile(new Point(0, 0)).getPiece())
			moves.push(move);
			
		then: "Move is invalid"
			collisionMove.checkMove(board, moves) != Model.Rule.VALID_MOVE
	}
	
	def "CollisionTestQueen"() {
		given:
			// Initialize a queen
			board.getTile(new Point(0,0)).setPiece(new Queen(1));
	
			//Set a Piece directly in front of Rook
			board.getTile(new Point(0,2)).setPiece(new Pawn(1));
			board.getTile(new Point(2,2)).setPiece(new Pawn(1));
			
			
		when: "Queen collide"
			//Set the vertical Move
			move = new Move(new Point(0, 0), new Point(0, 5), board.getTile(new Point(0, 0)).getPiece())
			moves.push(move);
			
		then: "Move is invalid"
			
			collisionMove.checkMove(board, moves) != Model.Rule.VALID_MOVE
		
		when: "Queen collide"
			//Set the diagonal Move
			move = new Move(new Point(0, 0), new Point(5, 5), board.getTile(new Point(0, 0)).getPiece())
			moves.push(move);
		then: "Move is invalid"
			collisionMove.checkMove(board, moves) != Model.Rule.VALID_MOVE
	}
	
	
	def "CollisionTestKing"() {
		given:
			// Initialize a king
			board.getTile(new Point(0,0)).setPiece(new King(1));
	
			//Set a Piece directly in front of Rook
			board.getTile(new Point(0,1)).setPiece(new Pawn(1));
			board.getTile(new Point(1,1)).setPiece(new Pawn(1));
			
			
		when: "King collide"
			//Set the vertical Move
			move = new Move(new Point(0, 0), new Point(0, 1), board.getTile(new Point(0, 0)).getPiece())
			moves.push(move);
			
		then: "Move is invalid"
			collisionMove.checkMove(board, moves) != Model.Rule.VALID_MOVE
		
		when: "King collide"
			//Set the diagonal Move
			move = new Move(new Point(0, 0), new Point(1, 1), board.getTile(new Point(0, 0)).getPiece())
			moves.push(move);
		then: "Move is invalid"
			collisionMove.checkMove(board, moves) != Model.Rule.VALID_MOVE
	}
	
	def "CaptureTestPawn"() {
		given:
			// Initialize a pawn
			board.getTile(new Point(0,0)).setPiece(new Pawn(1));
			
			// Initialize a opponent¡¯s pawn
			board.getTile(new Point(1,1)).setPiece(new Pawn(0));
			//Set the Move
		when: "Pawn collide"
			move = new Move(new Point(0, 0), new Point(1, 1), board.getTile(new Point(0, 0)).getPiece())
			moves.push(move);
			
		then: "Pawn Captured"
			collisionMove.checkMove(board, moves) == Model.Rule.VALID_MOVE
	}
	
	def "CaptureTestKnight"() {
		given:
			// Initialize a knight
			board.getTile(new Point(3,3)).setPiece(new Knight(1));
			
			// Initialize a opponent¡¯s pawn
			board.getTile(new Point(1,2)).setPiece(new Pawn(0));
		when: "Knight collide"
			//Set the Move
			move = new Move(new Point(3, 3), new Point(1, 2), board.getTile(new Point(3, 3)).getPiece())
			moves.push(move);
			
		then: "Pawn Captured"
			collisionMove.checkMove(board, moves) == Model.Rule.VALID_MOVE
	}
	
	def "CaptureTestBishop"() {
		given:
			// Initialize a bishop
			board.getTile(new Point(0,0)).setPiece(new Bishop(1));
	
			// Initialize a opponent¡¯s pawn
			board.getTile(new Point(2,2)).setPiece(new Pawn(0));
		
		when: "Bishop collide"
			//Set the Move
			move = new Move(new Point(0, 0), new Point(2, 2), board.getTile(new Point(0, 0)).getPiece())
			moves.push(move);
			
		then: "Pawn Captured"
			collisionMove.checkMove(board, moves) == Model.Rule.VALID_MOVE
	}
	
	def "CaptureTestRook"() {
		given:
			// Initialize a rook
			board.getTile(new Point(0,0)).setPiece(new Rook(1));
	
			// Initialize a opponent¡¯s pawn
			board.getTile(new Point(0,2)).setPiece(new Pawn(0));
		when: "Rook collide"
			//Set the Move
			move = new Move(new Point(0, 0), new Point(0, 2), board.getTile(new Point(0, 0)).getPiece())
			moves.push(move);
			
		then: "Pawn Captured"
			collisionMove.checkMove(board, moves) == Model.Rule.VALID_MOVE
	}
	
	def "CaptureTestQueen"() {
		given:
			// Initialize a queen
			board.getTile(new Point(0,0)).setPiece(new Queen(1));
	
			// Initialize a opponent¡¯s pawns
			board.getTile(new Point(0,2)).setPiece(new Pawn(0));
			board.getTile(new Point(2,2)).setPiece(new Pawn(0));
			
			
		when: "Queen collide"
			//Set the vertical Move
			move = new Move(new Point(0, 0), new Point(0, 2), board.getTile(new Point(0, 0)).getPiece())
			moves.push(move);
			
		then: "Pawn Captured"
			collisionMove.checkMove(board, moves) == Model.Rule.VALID_MOVE
		
		when: "Queen collide"
			//Set the diagonal Move
			move = new Move(new Point(0, 0), new Point(2, 2), board.getTile(new Point(0, 0)).getPiece())
			moves.push(move);
		then: "Pawn Captured"
			collisionMove.checkMove(board, moves) == Model.Rule.VALID_MOVE
	}
	
	
	def "CaptureTestKing"() {
		given:
			// Initialize a king
			board.getTile(new Point(0,0)).setPiece(new King(1));

			// Initialize a opponent¡¯s pawn
			board.getTile(new Point(0,1)).setPiece(new Pawn(0));
			board.getTile(new Point(1,1)).setPiece(new Pawn(0));
			
			
		when: "King collide"
			//Set the vertical Move
			move = new Move(new Point(0, 0), new Point(0, 1), board.getTile(new Point(0,0)).getPiece())
			moves.push(move);
			
		then: "Pawn Captured"
			collisionMove.checkMove(board, moves) == Model.Rule.VALID_MOVE
		
		when: "King collide"
			//Set the diagonal Move
			move = new Move(new Point(0, 1), new Point(1, 1), board.getTile(new Point(0,0)).getPiece())
			moves.push(move);
		then: "Pawn Captured"
			collisionMove.checkMove(board, moves) == Model.Rule.VALID_MOVE
	}
	
	def "CheckMateTest"() {
		given: "King is in check"
			// Initialize Rooks
			board.getTile(new Point(0,0)).setPiece(new Rook(1));
			board.getTile(new Point(0,1)).setPiece(new Rook(1));
			board.getTile(new Point(0,2)).setPiece(new Rook(1));

			//Set an opposing King
			board.getTile(new Point(1,1)).setPiece(new King(2));

		when: "Try move"
			move = new Move(new Point(1, 7), new Point(0, 7), board.getTile(new Point(0,0)).getPiece());
			moves.push(move);
		
		then: "King is in check"
			def checkmate = new Model.Rules.CheckMate()
		checkmate.checkMove(board, moves) == Model.Rule.GAME_OVER_T2
	}
	
	def "StaleMateTest"() {
		given:
			// Initialize Rooks
			board.getTile(new Point(0,0)).setPiece(new Rook(1));
			board.getTile(new Point(2,0)).setPiece(new Rook(1));
			board.getTile(new Point(0,2)).setPiece(new Rook(1));
	
			//Set an opposing King
			board.getTile(new Point(1,1)).setPiece(new King(2));
		when:	
			//Set King's AfterMove so it isn't allowed to castle ie escape this tight hold of rooks
			board.getTile(new Point(1,1)).getPiece().afterMove();
		then:
			def stalemate = new Model.Rules.Stalemate()
			stalemate.checkMove(board, moves) == Model.Rule.GAME_OVER_TIE
	}
	
	def "CastlingTest"() {
		given:
			// Initialize Rooks
			board.getTile(new Point(0,7)).setPiece(new Rook(1));
			board.getTile(new Point(7,7)).setPiece(new Rook(1));
			// Initialize King
			board.getTile(new Point(4,7)).setPiece(new King(1));
		when: "King is moving toward Rook by two space"
			move = new Move(new Point(4,7), new Point(2, 7), board.getTile(new Point(4,7)).getPiece())
			moves.push(move)
		then:
			def castling = new Model.Rules.Castling()
			castling.checkMove(board, moves) == Model.Rule.VALID_MOVE
	}
}
