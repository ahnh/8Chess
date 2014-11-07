package Controller;

import java.awt.Point;
import java.util.StringTokenizer;

import Model.Game;
import Model.Rule;
import Views.ViewBase;
import Views.ViewGUI;
import Views.ViewText;

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
		int val=0;
		
		if(args.length>0)
			{val= Integer.parseInt(args[0]);}
		else
			{val=0;}
		

		gameCont.start(val);
	}

	public void start(int val) {

		String input = null;
		boolean valid = false;
		boolean ready = false;



		
		player = new Player(1);
		view = new ViewGUI(player);

		valid = false;


		game = new Game(val);
		gameloop();
	}

	public void gameloop() {

		String move = null;
		String action = null;
		Point[] movePoint = null;
		int moveValid = 0;
		int winner = 0;
		boolean gameInProgress = true;
		boolean ready = false;

		ready = view
				.displayMessage("Game started. Use \"exit\" to quit at any time.");

		while (gameInProgress) {

			ready = view.displayMessage("Enter your move, player "
					+ game.getCurrentTeam() + ".");

			// Wait for valid move
			do {
				ready = view.display(game.getActiveBoard());
				// Loop for valid input
				boolean inputValid = false;
				do {
					move = player.getInput();

					// Game exit condition to get out of a game in progress
					if (move.toLowerCase().compareTo("exit") == 0) {

						System.exit(0);
					}

					movePoint = convertMoveFormat(move);
					
					if (movePoint != null)
						inputValid = true;
					
					else {
						ready = view.displayMessage("Please enter your move in the form 'A2-A3'.");
						ready = view.display(game.getActiveBoard());
					}
				} while (!inputValid);

				// Double check what return values
				moveValid = game.move(movePoint[0], movePoint[1]); 

				if (moveValid == Rule.INVALID_MOVE) {

					ready = view.displayMessage("Invalid move: "
							+ game.getError() + ".");
				}

			} while (moveValid == Rule.INVALID_MOVE
					|| moveValid == Rule.INVALID_MOVE_CHECK);

			// Check if valid move needs other options
			if (moveValid >= Rule.NEEDS_INPUT) {

				String[][] opts = game.getRuleOptions();
				boolean goodInput = false;

				ready = view.displayOptions(opts[0][0], opts[1]);

				do {
					action = player.getInput();
					if (Integer.parseInt(action) > 0
							&& Integer.parseInt(action) <= opts[1].length) {

						goodInput = true;
					}
				} while (goodInput == false);

				game.completeAction(Integer.parseInt(action), moveValid);
			}

			// Win condition triggered
			if (moveValid == Rule.GAME_OVER_TIE
					|| moveValid == Rule.GAME_OVER_T1
					|| moveValid == Rule.GAME_OVER_T2) {
				gameInProgress = false;
			} else {

				game.nextTurn();
			}

		}
		if (moveValid == Rule.GAME_OVER_TIE) {

			winner = 0;
		}
		if (moveValid == Rule.GAME_OVER_T2) {

			winner = 1;
		}
		if (moveValid == Rule.GAME_OVER_T1) {

			winner = 2;
		}

		ready = view.displayGameOver(winner, game.getActiveBoard());
	}

	public static Point[] convertMoveFormat(String moveStr) {

		Point[] move = new Point[2];
		StringTokenizer tokens = new StringTokenizer(moveStr, " \t\n-");

		move[0] = new Point(-1, -1);
		move[1] = new Point(-1, -1);

		for (int i = 0; i < 2; i++) {

			if (tokens.hasMoreTokens()) {

				String token = tokens.nextToken();

				try {
					// -1 to convert to array coordinates
					move[i].x = alphaToIndex(token.charAt(0)) - 1;
					move[i].y = Integer.parseInt(token.charAt(1) + "") - 1;
				} catch (Exception e) {
					return null;
				}
			}
		}

		return move;
	}

	public static int alphaToIndex(char c) {

		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String letter = c + "";

		letter = letter.toUpperCase();

		return alpha.indexOf(letter) + 1;
	}
}
