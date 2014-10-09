package Controller;

import java.awt.Point;
import java.util.StringTokenizer;

import Model.Game;
import View.*;

public class GameController {
	
	Player player;
	Game game;
	View view;
	
	public GameController() {
		
		player = new Player();
		game = new Game();
		view = null;
	}
	
	public static void main(String[] args) {
		
		GameController gameCont = new GameController();
		
		gameCont.start();
	}
	
	public void start(){
		
		String input = null;
		boolean valid = false;
		
		// Determine view mode before starting
		System.out.println( "Enter the view mode you wish to use by the number:" );
		System.out.println( "1. Text view." );
		System.out.println( "2. GUI view." );
		
		do {
			
			input = player.getInput();
			
			if ( input.compareTo( "1" ) == 0 ){
				
				view = new ViewText();
				valid = true;
			}
			else if ( input.compareTo( "2" ) == 0 ){
				
				view = new ViewGUI();
				valid = true;
			}
			else {
				
				valid = false;
			}
		} while ( !valid );
		
		gameloop();
	}
	
	public void gameloop(){
		
		String move = null;
		Point[] movePoint = null;
		int moveValid = 0;
		boolean gameInProgress = true;
		
		while ( gameInProgress ){
			
			view.displayMessage( "Enter your move." );
			
			// Wait for valid move
			do {
				view.display( game.getBoards() );
				
				move = player.getInput();
				
				// Game exit condition to get out of a game in progress
				if ( move.toLowerCase().compareTo("exit") == 0 ){
					
					System.exit(0);
				}
				
				movePoint = convertMoveFormat( move );
				
				moveValid = game.move( movePoint[0], movePoint[1] ); // Double check what return values
				
				if ( moveValid == 0 ){
					
					view.displayMessage( "Invalid move, try again." );
				}
			
			} while ( moveValid == 0 );
			
			// Win condition triggered
			if ( moveValid == 2 ){
				
				gameInProgress = false;
			}
		}
		
		view.displayGameOver( winner ); // How do I know who won the game?
	}
	
	public static Point[] convertMoveFormat( String moveStr ){
		
		Point[] move = new Point[2];
		StringTokenizer tokens = new StringTokenizer( moveStr, " \t\n-" );
		
		for ( int i = 0; i < 2; i ++){
			
			if ( tokens.hasMoreTokens() ){
				
				String token = tokens.nextToken();
				
				move[i].x = alphaToIndex( token.charAt(0) );
				move[i].y = Integer.parseInt( token.charAt(1) + "" );
			}
		}
		
		return move;
	}
	
	public static int alphaToIndex( char c ){
		
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String letter = c + "";
		
		letter = letter.toUpperCase();
		
		return alpha.indexOf( letter ) + 1;
	}
}
