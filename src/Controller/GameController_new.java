package Controller;

import java.awt.Point;
import java.util.StringTokenizer;

import Model.Game;
import Model.Rule;
import Views.*;

public class GameController {
	
	Player player;
	Game game;
	ViewBase view;
	
	public GameController() {
		
		player = null;
		game = null;
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
			
			input = Player.getTextInput();
			
			if ( input.compareTo( "1" ) == 0 ){
				
				view = new ViewText();
				player = new Player( 0 );
				valid = true;
			}
			else if ( input.compareTo( "2" ) == 0 ){
				
				player = new Player( 1 );
				view = new ViewGUI( player );
				valid = true;
			}
			
		} while ( !valid );
		
		valid = false;
		
		// Switch the view mode selected at this point.
		// Determine game variant before starting
		view.displayOptions( "Enter the chess variant to play:",
				new String[] {
				  "1. Classic.",
				  "2. Absorption.",
				  "3. Cheshire Cat.",
				  "4. Suicide.",
				  "5. Jedi Knight.",
                                  "6. TestCheck.",
                                  "7. Atomic.",
                                  "8. Hobbit."
				}
				);
		
		do {
			
			input = player.getInput();
			
			if ( input.compareTo( "1" ) == 0 ){
				
				game = new Game( 2 );
				valid = true;
			}
			else if ( input.compareTo( "2" ) == 0 ){
				
				game = new Game( 0 );
				valid = true;
			}
			else if ( input.compareTo( "3" ) == 0 ){
				
				game = new Game( 1 );
				valid = true;
			}
			else if ( input.compareTo( "4" ) == 0 ){
				
				game = new Game( 4 );
				valid = true;
			}
			else if ( input.compareTo( "5" ) == 0 ){
				
				game = new Game( 3 );
				valid = true;
			}
			else if ( input.compareTo( "6" ) == 0 ){
				
				game = new Game( 5 );
				valid = true;
			}			
			else if ( input.compareTo( "7" ) == 0 ){
				
				game = new Game( 6 );
				valid = true;
			}
			else if ( input.compareTo( "8" ) == 0 ){
				
				game = new Game( 6 );
				valid = true;
			}
                        
		} while ( !valid );
		
		gameloop();
	}
	
	public void gameloop(){
		
		String move = null;
		String action = null;
		Point[] movePoint = null;
		int moveValid = 0;
		int winner = 0;
		boolean gameInProgress = true;
		
		view.displayMessage( "Game started. Use \"exit\" to quit at any time.");
		
		while ( gameInProgress )
                {
			
			view.displayMessage( "Enter your move, player " + game.getCurrentTeam() + "." );
            
			// Wait for valid move
			do {
				view.display( game.getActiveBoard() );
				// Loop for valid input
				boolean inputValid = false;
				do {
					move = player.getInput();
					
					// Game exit condition to get out of a game in progress
					if ( move.toLowerCase().compareTo("exit") == 0 ){
						
						System.exit(0);
					}
					
					movePoint = convertMoveFormat( move );
					if (movePoint != null)
						inputValid = true;
					else
						view.displayMessage( "You have entered invalid input, please enter your move in the form 'A2-A3'.");
				} while (!inputValid);
				
				moveValid = game.move( movePoint[0], movePoint[1] ); // Double check what return values
				
				if ( moveValid == Rule.INVALID_MOVE ){
					
					view.displayMessage( "Invalid move: " + game.getError() + "." );
				}
				
			
			} while ( moveValid == Rule.INVALID_MOVE || moveValid == Rule.INVALID_MOVE_CHECK);
			 
			// Check if valid move needs other options
			if ( moveValid >= Rule.NEEDS_INPUT ){
				
				String[][] opts = game.getRuleOptions();
				boolean goodInput = false;
				
				view.displayOptions(opts[0][0], opts[1]);
				
				do {
					action = player.getInput();
					if ( Integer.parseInt(action) > 0 && Integer.parseInt(action) <= opts[1].length ){
						
						goodInput = true;
					}
				} while( goodInput == false );
				
				game.completeAction( Integer.parseInt( action ), moveValid );
			}
			
			// Win condition triggered
			if ( moveValid == Rule.GAME_OVER_TIE || 
                 moveValid == Rule.GAME_OVER_T1 ||
                 moveValid == Rule.GAME_OVER_T2
                 ) {
				gameInProgress = false;
			}
			else {
				
				game.nextTurn();
			}
                        
		}
		if ( moveValid == Rule.GAME_OVER_TIE ){
			
			winner = 0;
		}
		if ( moveValid == Rule.GAME_OVER_T2 ){
			
			winner = 1;
		}
		if ( moveValid == Rule.GAME_OVER_T1 ){
			
			winner = 2;
		}
		
		view.displayGameOver( winner, game.getActiveBoard() );
	}
	
	public static Point[] convertMoveFormat( String moveStr ){
		
		Point[] move = new Point[2];
		StringTokenizer tokens = new StringTokenizer( moveStr, " \t\n-" );
		
		move[0] = new Point(-1,-1);
		move[1] = new Point(-1,-1);
		
		for ( int i = 0; i < 2; i ++){
			
			if ( tokens.hasMoreTokens() ){
				
				String token = tokens.nextToken();
				
				try {
					// -1 to convert to array coordinates
					move[i].x = alphaToIndex( token.charAt(0) ) - 1;
					move[i].y = Integer.parseInt( token.charAt(1) + "" ) - 1;
				} catch (Exception e) {
					return null;
				}
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
