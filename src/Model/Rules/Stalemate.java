package Model.Rules;

import java.awt.Point;
import java.util.Stack;

import Model.*;

import Model.Board;
import Model.Rule;
import java.util.ArrayList;
import java.util.List;


public class Stalemate extends Rule {
	public Stalemate(){
		super("Stalemate state would be activated", null);
	}
	
        
        //Pulls The Team from the Move ontop of MoveStack
        //Checks to see if that TEAM is in checkmate
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
            
        boolean isT1_stalemate = IsStaleMate(board, 1);
        boolean isT2_stalemate = IsStaleMate(board, 2);       
         

        if( isT1_stalemate || isT2_stalemate)
        {
            return Rule.GAME_OVER_TIE;
        }
        else{
            return Rule.VALID_MOVE;
        }

        }
            
            
            
        //Two Team Assumption
        // 0 = No1 in checkmate
        // 1 = T1 in checkmate
        boolean IsStaleMate(Board board, int currentTeam) 
                {     



                //Find if a King is in Check
                


                //Grab a List of all Pieces on this Team
                // with all valid move's they can make
                List<Move> team_ValidMovePool = CreateValidMoveList(board,currentTeam);
                


                //System.out.println("[noCheck] Available moves by Team["+currentTeam+"]:"+ team_ValidMovePool.size());
                
                Check check = new Check();
                boolean isCheck = check.isKingInCheck(board, currentTeam);
                
                //System.out.println("KING CHECK = "+isCheck);
                //Cycle through list, if a move exists that does not leave the king in check
                // Then this rule passes Valid
                
                if(!isCheck)
                {
                    for(int x=0;x<team_ValidMovePool.size();x++)
                    {
                    //Create the Stack to Check on Check Rule
                        Stack moveStack = new Stack();
                        moveStack.add(team_ValidMovePool.get(x));

                        if( check.checkMove(board, moveStack)==Rule.VALID_MOVE)
                            return false; //A Move Exists to exit check


                    }

                    //If no valid move exists, and the king is not check, then this person is in stalemate
                    return true; //Checkmate    
                }
                else
                    return false;//In check so you can't be in stalemate
                
	}
        
        
        
        //Create list containing all pieces not in team with their moves set to destination 
        private List<Move> CreateValidMoveList(Board board, int team)
        {
            CollisionMove collMove = new CollisionMove();
            
            
            //Grab a List of All enemy pieces
            Piece comparePiece=null;
            List<Move> moveList = new ArrayList<Move>();
            for(int y=0;y<board.getHeight();y++)
                for(int x=0;x<board.getWidth();x++)
                {
                    Tile tile=null;// = board.getTile(point);
                    comparePiece=null;

                    Point point = new Point(x,y);

                    //Assign tile and Check for Null
                    if( (tile=board.getTile(point))!=null)
                        //Assign Piece and Check for Null
                        if( (comparePiece= tile.getPiece())!=null)   
                            //Check if pieces belong on same team
                            if(comparePiece.getTeam()==team)
                            {
                            //Grab a List of all enemy pieces and set Destination to the moving Piece's destination    
                            
                            //Cycle through the board's positions setting only valid moves into list
                               
                             
                                for(int y2=0;y2<board.getHeight();y2++)
                                    for(int x2=0;x2<board.getWidth();x2++)
                                    {
                                    Point p2 = new Point(x2,y2);
                                    Move move = new Move(point,p2,comparePiece);
                                        if((comparePiece.checkDestination(move)) ) // If the piece can physically make the move
                                        {
                                        //Check if the move can be made from the collisions point of view
                                        //Create the Stack to Check on Collision Rule
                                        Stack moveStack = new Stack();
                                        moveStack.add(move); 
                                            if(collMove.checkMove(board, moveStack) ==Rule.VALID_MOVE)
                                            {
                                            //Store it in the list if its valid
                                            moveList.add(move); 
                                            }
                                        }
                                    }
                            }                            
                }    
            return moveList;
        }
        
        

        
}
