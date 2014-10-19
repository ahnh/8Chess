package Views;

import java.awt.Point;

import Model.Board;

public class ViewText extends ViewBase {

	public ViewText() {

		super();
	}

	public boolean displayMessage(String msg) {

		System.out.println(msg);

		return true;
	}

	public boolean display(Board board) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String header = "  ";
		for (int j = 0; j < board.getWidth(); j++) {
			header += " ";
			header += alphabet.charAt(j);
		}
		System.out.println(header);

		for (int i = 0; i < board.getHeight(); i++) {
			// Print Row
			System.out.print(i + 1 + " ");
			for (int j = 0; j < board.getWidth(); j++) {
				System.out.print(" ");
				System.out.print(board.getTile(new Point(j, i)));
			}
			System.out.println();
		}

		return true;
	}

	public boolean displayOptions(String msg, String[] options) {

		System.out.println(msg);

		for (int i = 0; i < options.length; i++) {

			System.out.println(options[i]);
		}

		return true;
	}

	public boolean displayGameOver(int winner, Board board) {

		if (winner != 0) {

			System.out.println("Congratulations player " + winner
					+ ", you win!");
		} else {

			System.out.println("It's a draw!");
		}
		this.display(board);

		System.out.println("GAME OVER");

		return true;
	}
}
