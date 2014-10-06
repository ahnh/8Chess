package Controller;

import java.util.Scanner;

public class Player {
	String userInput;

	public void getInput() {
		Scanner in = new Scanner(System.in);
		userInput = in.nextLine();
		System.out.println(userInput);
	}

}
