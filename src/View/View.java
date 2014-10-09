package View;

import java.util.List;
import Model.Board;

public abstract class View {
	
	public View(){
		
		
	}
	
	public abstract void displayMessage( String msg );
	
	public abstract void display( List<Board> board );
}
