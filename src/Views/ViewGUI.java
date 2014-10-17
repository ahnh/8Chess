package Views;

import Model.Board;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

public class ViewGUI extends ViewBase {
	
	JFrame gw, optw;
	
	// Main window items
	
	
	// Option window items
	private JLabel question;
	private ButtonGroup optionGroup;
	private JRadioButton[] option;
	
	public ViewGUI(){
		
		super();
		
		gw = new JFrame();
		gw.setName( "Chess" );
		gw.setSize( new Dimension(500, 500) );
		gw.setFocusable( true );
		gw.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		gw.setLayout( new FlowLayout() );
		
		
		
		gw.setVisible(true);
	}
	
	public void displayMessage( String msg ){
		
		
	}
	
	public void display( Board board ){
		
		
	}
	
	public void displayOptions( String msg, String[] options ){
		
		optw = new JFrame();
		
		gw = new JFrame();
		gw.setName( "Chess" );
		gw.setSize( new Dimension( 500, 100 + (5 * options.length) ) );
		gw.setFocusable( true );
		gw.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		gw.setLayout( new FlowLayout() );
		
		optionGroup = new ButtonGroup();
		option = new JRadioButton()[options.length];
		
		for ( int i = 0; i < options.length; i++ ){
			
			
		}
	}
	
	public void displayGameOver( int winner, Board board ){
		
		
		this.display( board );
	}
}
