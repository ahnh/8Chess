package Views;

import Controller.Player;
import Model.Board;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

public class ViewGUI extends ViewBase implements ActionListener {
	
	Player input;
	
	JFrame gw, optw;
	ActionListener playerIn;
	
	// Main window items
	
	
	
	// Option window items
	private ButtonGroup optionGroup;
	private JRadioButton[] option;
	private JButton accept;
	
	public ViewGUI( Player player ){
		
		super();
		
		input = player;
		
		// Set up main game window
		gw = new JFrame();
		gw.setTitle( "Chess" );
		gw.setSize( new Dimension(500, 500) );
		gw.setFocusable( true );
		gw.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		gw.setLayout( new FlowLayout() );
		gw.setAutoRequestFocus( true );
		
		
		
		gw.setVisible(true);
	}
	
	public void displayMessage( String msg ){
		
		
	}
	
	public void display( Board board ){
		
		
	}
	
	public void displayOptions( String msg, String[] opts ){
		
		optw = new JFrame();
		
		// Set up option window
		optw.setAutoRequestFocus( true );
		optw.setTitle( "Choose an Option" );
		optw.setSize( new Dimension( 300, 140 + (25 * opts.length) ) );
		optw.setPreferredSize( new Dimension( 300, 140 + (25 * opts.length) ) );
		optw.setFocusable( true );
		optw.setResizable( false );
		optw.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		optw.setLayout( new FlowLayout() );
		
		// Create component for the option window
		JLabel question = new JLabel( msg );
		
		accept = new JButton( "Choose" );
		
		optionGroup = new ButtonGroup();
		option = new JRadioButton[opts.length];
		
		for ( int i = 0; i < opts.length; i++ ){
			
			option[i] = new JRadioButton( opts[i] );
			option[i].getModel().setActionCommand( "" + (i+1) );
			optionGroup.add( option[i] );
		}
		optionGroup.setSelected( option[0].getModel(), true);
		
		accept.addActionListener( playerIn );
		accept.setActionCommand( "Check option selected" );
		accept.setSize( new Dimension( 30, 20 ) );
		accept.setText( "Accept" );
		
		// Create box organization for parts
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
		
		optw.pack();
		optw.setVisible( true );
	}
	
	public void displayGameOver( int winner, Board board ){
		
		this.displayMessage( "Congratulations player " + winner + ", you win!" );
		this.display( board );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if ( e.getActionCommand().compareTo( "Check option selected" ) == 0 ){
			
			input.writeToBuffer( optionGroup.getSelection().getActionCommand() );
			optw.dispose();
			optw = null;
		}
	}
}
