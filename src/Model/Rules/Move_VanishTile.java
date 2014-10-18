package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Rule;

public class Move_VanishTile extends Rule {
	public Move_VanishTile() {
		super( ":3 Can't move onto a missing Tile :3 ", null);
	}
	
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		//Grab CurrentMove
		Move currentMove = moves.peek();
                
                //Check if the Tile Destination is 
                if(board.getTile(currentMove.getEnd()).getExists()==false)
                return Rule.INVALID_MOVE;    
                else
		return Rule.VALID_MOVE;
	}

}
