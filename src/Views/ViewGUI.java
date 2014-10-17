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
	
	public void displayOptions( String msg, String[] opts ){
		
		optw = new JFrame();
		
		gw = new JFrame();
		gw.setName( "Chess" );
		gw.setSize( new Dimension( 500, 100 + (5 * opts.length) ) );
		gw.setFocusable( true );
		// gw.setDefaultCloseOperation( JFrame. ); // Unneeded possibly
		gw.setLayout( new FlowLayout() );
		
		question = new JLabel( msg );
		
		optionGroup = new ButtonGroup();
		option = new JRadioButton[opts.length];
		
		for ( int i = 0; i < opts.length; i++ ){
			
			option[i] = new JRadioButton( opts[i] );
			optionGroup.add( option[i] );
		}
		
		Box horiz = Box.createVerticalBox();
		
		horiz.add( Box.createVerticalGlue() );
		horiz.add( question );
		horiz.add( Box.createVerticalStrut( 5 ) );
		
		for ( int i = 0; i < opts.length; i++ ){
			
			horiz.add( Box.createVerticalStrut( 5 ) );
			horiz.add( option[i] );
		}
		
		horiz.add( Box.createVerticalGlue() );
		
		optw.add( Box.createHorizontalGlue() );
		optw.add( horiz );
		optw.add( Box.createHorizontalGlue() );
		
		// Add a button
		
		optw.setVisible( true );
	}
	
	public void displayGameOver( int winner, Board board ){
		
		this.displayMessage( "Congratulations player " + winner + ", you win!" );
		this.display( board );
	}
}
