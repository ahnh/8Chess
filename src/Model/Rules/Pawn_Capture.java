package Model.Rules;

import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Rule;

public class Pawn_Capture extends Rule {
	public Pawn_Capture() {
		super("Pawn captured?", null);
	}
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
            
            
		Move currentMove = moves.peek();
                int currentTeam = currentMove.getPiece().getTeam();
                //This rule Only Applies to Pawns, and when they capture
		if (currentMove.getPiece().getName().equalsIgnoreCase("pawn"))
                {
                 //If the Pawn is moving diagonally then we need to check, otherwise we don't care
                 if((currentMove.getDistanceX()==1) && (currentMove.getDistanceY()==1) )    
                 {
                  //Can't Move Diagonally onto empty space
                  if(board.getTile(currentMove.getEnd()).getPiece()==null)
                    {
                    return Rule.INVALID_MOVE;
                    }
                  //Can't capture piece belonging to same team
                  else if((board.getTile(currentMove.getEnd())!=null) && (board.getTile(currentMove.getEnd()).getPiece().getTeam()==currentTeam))
                    {
                    return Rule.INVALID_MOVE;
                    }
                  
                  
                 }
                 
                }

                return Rule.VALID_MOVE;
		
                
                
                
                
	}
}
