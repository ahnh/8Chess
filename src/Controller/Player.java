package Controller;

import java.util.Scanner;

public class Player {

	Scanner scan = new Scanner(System.in);
	
	public String getInput() {
		
		String input = null;
		
		input = scan.nextLine();
		
		return input.trim();
	}
}
