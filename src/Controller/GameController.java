package Controller;

import Model.Game;

public class GameController {
	public GameController() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.Start();
		Player player = new Player();
		System.out.println("Enter:");
		player.getInput();

	}

}
