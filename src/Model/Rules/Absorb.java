package Model.Rules;

import java.util.Stack;

import Model.Board;
import Model.Move;
import Model.Rule;
import Model.Pieces.*;
public class Absorb extends Rule {
	public Absorb() {
		super("We are Absorbing", null);
	}
	@Override
	public int checkMove(Board board, Stack<Move> moves) {
		Move currentMove = moves.peek();
		if (currentMove.getPiece().getName()!="Pawn" && currentMove.getPiece().getName()!="King" )
                {
                //Change Board State
                    //Update Piece on Capture
                    if(board.getTile(currentMove.getEnd()).getPiece()!=null)
                    {
                        if(board.getTile(currentMove.getStart()).getPiece().getName()=="Rook")
                        {
                        // Rook Take Knight = Rook + Knight
                            if(board.getTile(currentMove.getEnd()).getPiece().getName()=="Knight" || board.getTile(currentMove.getEnd()).getPiece().getName()=="RookKnight")
                            {                      
                                board.getTile(currentMove.getStart()).setPiece(new RookKnight(currentMove.getPiece().getTeam()));
                            }
                        // Rook Take Knight = Rook + Knight
                            if(board.getTile(currentMove.getEnd()).getPiece().getName()=="Bishop" || board.getTile(currentMove.getEnd()).getPiece().getName()=="Queen")
                            {
                                board.getTile(currentMove.getStart()).setPiece(new Queen(currentMove.getPiece().getTeam()));
                            }  
                            
                            if(board.getTile(currentMove.getEnd()).getPiece().getName()=="BishopKnight" || board.getTile(currentMove.getEnd()).getPiece().getName()=="QueenKnight")
                            {
                                board.getTile(currentMove.getStart()).setPiece(new QueenKnight(currentMove.getPiece().getTeam()));
                            } 
                        }
                    
                        else if(board.getTile(currentMove.getStart()).getPiece().getName()=="Bishop")
                        {
                        // Rook Take Knight = Rook + Knight
                            if(board.getTile(currentMove.getEnd()).getPiece().getName()=="Knight" || board.getTile(currentMove.getEnd()).getPiece().getName()=="BishopKnight")
                            {                      
                                board.getTile(currentMove.getStart()).setPiece(new BishopKnight(currentMove.getPiece().getTeam()));
                            }
                        // Rook Take Knight = Rook + Knight
                            if(board.getTile(currentMove.getEnd()).getPiece().getName()=="Rook" || board.getTile(currentMove.getEnd()).getPiece().getName()=="Queen")
                            {
                                board.getTile(currentMove.getStart()).setPiece(new Queen(currentMove.getPiece().getTeam()));
                            }  
                            
                            if(board.getTile(currentMove.getEnd()).getPiece().getName()=="RookKnight" || board.getTile(currentMove.getEnd()).getPiece().getName()=="QueenKnight")
                            {
                                board.getTile(currentMove.getStart()).setPiece(new QueenKnight(currentMove.getPiece().getTeam()));
                            } 
                        }

                        else if(board.getTile(currentMove.getStart()).getPiece().getName()=="Queen")
                        {
                        // Queen gain knight powers                         
                            if(board.getTile(currentMove.getEnd()).getPiece().getName()=="RookKnight" || 
                               board.getTile(currentMove.getEnd()).getPiece().getName()=="QueenKnight" ||
                               board.getTile(currentMove.getEnd()).getPiece().getName()=="BishopKnight"  ||   
                               board.getTile(currentMove.getEnd()).getPiece().getName()=="Knight")
                            {
                                board.getTile(currentMove.getStart()).setPiece(new QueenKnight(currentMove.getPiece().getTeam()));
                            } 
                        }                       
                        
                        else if(board.getTile(currentMove.getStart()).getPiece().getName()=="RookKnight")
                        {
                        // Queen gain knight powers                         
                            if(board.getTile(currentMove.getEnd()).getPiece().getName()=="Queen" || 
                               board.getTile(currentMove.getEnd()).getPiece().getName()=="Bishop")
                            {
                                board.getTile(currentMove.getStart()).setPiece(new QueenKnight(currentMove.getPiece().getTeam()));
                            } 
                        }
                        
                        else if(board.getTile(currentMove.getStart()).getPiece().getName()=="BishopKnight")
                        {
                        // Queen gain knight powers                         
                            if(board.getTile(currentMove.getEnd()).getPiece().getName()=="Queen" || 
                               board.getTile(currentMove.getEnd()).getPiece().getName()=="Rook")
                            {
                                board.getTile(currentMove.getStart()).setPiece(new QueenKnight(currentMove.getPiece().getTeam()));
                            } 
                        }                        
                        
                    }
                    
                
                
                
                }
		

                
                
		
		return Rule.VALID_MOVE;
	}
}
