package Controller;

import java.awt.Point;
import Model.Game;
import View.*;

public class GameController {
	
	Player player;
	Game game;
	View view;
	
	public GameController() {
		
		player = new Player();
		game = new Game();
		view = new ViewText();
	}
	
	public static void main(String[] args) {
		
		GameController gameCont = new GameController();
		
		gameCont.start();
	}
	
	public void start(){
		
		gameloop();
	}
	
	public void gameloop(){
		
		String move = null;
		Point[] movePoint = null;
		boolean moveValid = false;
		boolean gameInProgress = true;
		
		while ( gameInProgress ){
			
			do {
				view.displayMessage( "Enter your move." );
				view.display( game.getBoards() );
				
				move = player.getInput();
				
				movePoint = convertMoveFormat( move );
			
			} while ( !moveValid );
		}
	}
	
	public static Point[] convertMoveFormat( String moveStr ){
		
		Point[] move = new Point[2];
		
		
		
		return move;
	}
}
