package Views;

import Model.Board;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

public class ViewGUI extends ViewBase {
	
	JFrame gw, optw;
	ActionListener playerIn;
	
	// Main window items
	
	
	
	// Option window items
	private ButtonGroup optionGroup;
	private JRadioButton[] option;
	private JButton accept;
	
	public ViewGUI( ActionListener myListen ){
		
		super();
		
		playerIn = myListen;
		
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
		
		JLabel question = new JLabel( msg );
		
		accept = new JButton( "Choose" );
		
		optionGroup = new ButtonGroup();
		option = new JRadioButton[opts.length];
		
		for ( int i = 0; i < opts.length; i++ ){
			
			option[i] = new JRadioButton( opts[i] );
			optionGroup.add( option[i] );
		}
		
		accept.addActionListener( playerIn );
		accept.setActionCommand( optionGroup.getSelection().toString() );
		accept.setSize( new Dimension( 30, 20 ) );
		accept.setText( "Accept" );
		
		Box horiz = Box.createVerticalBox();
		
		horiz.add( Box.createVerticalGlue() );
		horiz.add( question );
		horiz.add( Box.createVerticalStrut( 5 ) );
		
		for ( int i = 0; i < opts.length; i++ ){
			
			horiz.add( Box.createVerticalStrut( 5 ) );
			horiz.add( option[i] );
		}
		
		horiz.add( Box.createVerticalStrut( 10 ) );
		horiz.add( accept );
		horiz.add( Box.createVerticalGlue() );
		
		optw.add( Box.createHorizontalGlue() );
		optw.add( horiz );
		optw.add( Box.createHorizontalGlue() );
		
		optw.setVisible( true );
	}
	
	public void displayGameOver( int winner, Board board ){
		
		this.displayMessage( "Congratulations player " + winner + ", you win!" );
		this.display( board );
	}
}
