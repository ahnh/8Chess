package Model;

import java.awt.List;
import java.util.ArrayList;

public class Board {
	private ArrayList<Piece> listA;
	private ArrayList<Piece> listB;
	
	
	public Board() {
	}
	
	public ArrayList<Piece> getListA()
	{
		return listA;
	}
	
	public ArrayList<Piece> getListB()
	{
		return listB;
	}
	
	private void setListA(ArrayList<Piece> listA) {
		this.listA = listA;
	}
	
	private void setListB(ArrayList<Piece> listB) {
		this.listB = listB;
	}

	public void update(String whatisname){
		
	}
}
