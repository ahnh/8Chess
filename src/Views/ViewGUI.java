package Views;

import Controller.Player;
import Model.Board;
import Model.Piece;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.*;

public class ViewGUI extends ViewBase implements ActionListener {
	
	Player input;
	boolean gridSetup;
	String movePt1, movePt2;
	
	JFrame gw, optw;
	
	// Main window items
	JLabel gameMessage;
	JButton[][] tiles;
	Panel boardArea;
	
	// Option window items
	private ButtonGroup optionGroup;
	private JRadioButton[] option;
	private JButton accept;
	
	public ViewGUI( Player player ){
		
		super();
		
		input = player;
		gridSetup = false;
		movePt1 = null;
		movePt2 = null;
		
		// Set up main game window
		gw = new JFrame();
		gw.setTitle( "Chess" );
		gw.setSize( new Dimension(600, 600) );
		gw.setPreferredSize( new Dimension(600, 600) );
		gw.setFocusable( true );
		gw.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		gw.setLayout( new FlowLayout() );
		
		gameMessage = new JLabel( "Game is ready to go.");
		
		boardArea = new Panel();
		
		Box horMsg = Box.createHorizontalBox();
		
		horMsg.add( Box.createHorizontalGlue() );
		horMsg.add( gameMessage );
		horMsg.add( Box.createHorizontalGlue() );
		
		Box vertMain = Box.createVerticalBox();
		
		vertMain.add( Box.createVerticalGlue() );
		vertMain.add( horMsg );
		vertMain.add( Box.createVerticalGlue() );
		vertMain.add( boardArea );
		vertMain.add( Box.createVerticalGlue() );
		
		Box masterBox = Box.createHorizontalBox();
		
		masterBox.add( Box.createHorizontalGlue() );
		masterBox.add( vertMain );
		masterBox.add( Box.createHorizontalGlue() );
		
		gw.add( masterBox );
		
		gw.pack();
		gw.setLocationRelativeTo( null );
		gw.setVisible(true);
		gw.toFront();
	}
	
	public void displayMessage( String msg ){
		
		gameMessage.setText( msg );
		gw.setVisible( true );
	}
	
	public void display( Board board ){
		
		Piece piece = null;
		
		if ( gridSetup == false ){
			
			String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			
			boardArea.setLayout( new GridLayout( 0, board.getWidth() + 1 ) );
			tiles = new JButton[board.getWidth()][board.getHeight()];
			
			// alpha index along top
			boardArea.add( new JLabel("") );
			
			for (int j = 0; j < board.getWidth(); j++){
                
				boardArea.add( new JLabel( "" + alpha.charAt(j), JLabel.CENTER ) );
			}
			
			// Put button pieces on the board area
			for (int i = 0; i < board.getHeight(); i++){
				
				boardArea.add( new JLabel( "" + (i + 1), JLabel.CENTER ) );
				
				for (int j = 0; j < board.getWidth(); j++){
					
					piece = board.getTile( new Point(j,i) ).getPiece();
					
					if ( piece == null ){
						
						tiles[j][i] = new JButton( "" );
						tiles[j][i].setPreferredSize( new Dimension( 50, 50 ) );
						tiles[j][i].setBorder( new LineBorder(Color.BLACK) );
						tiles[j][i].setActionCommand( "" + j + "" + i );
						tiles[j][i].addActionListener( this );
					}
					else {
						
						tiles[j][i] = new JButton( piece.getName() );
						tiles[j][i].setPreferredSize( new Dimension ( 50, 50 ) );
						tiles[j][i].setBorder( new LineBorder(Color.BLACK) );
						tiles[j][i].setActionCommand( "" + j + "" + i );
						tiles[j][i].addActionListener( this );
					}
					
					boardArea.add( tiles[j][i] );
				}
			}
			
			gridSetup = true;
		}
		
		// Update buttons to reflect the board
		for (int i = 0; i < board.getHeight(); i++){
			
			for (int j = 0; j < board.getWidth(); j++){
				
				piece = board.getTile( new Point(j,i) ).getPiece();
				
				if ( piece == null ){
					
					tiles[j][i].setText( "" );
				}
				else {
					
					tiles[j][i].setText( piece.getName() );
				}
			}
		}
		
		gw.setVisible( true );
	}
	
	public void displayOptions( String msg, String[] opts ){
		
		optw = new JFrame();
		
		// Set up option window
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
		
		accept.addActionListener( this );
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
		optw.setLocationRelativeTo( null );
		optw.toFront();
	}
	
	public void displayGameOver( int winner, Board board ){
		
		if ( winner != 0 ){
			
			this.displayMessage( "Congratulations player " + winner + ", you win!" );
		}
		else {
			
			this.displayMessage( "It's a draw!" );
		}
		this.display( board );
	}
	
	// Convert array index format into move input format for game controller
	private String convertToMove( String raw ){
		
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String move = "";
		
		move += alpha.charAt( Integer.parseInt( raw.charAt(0) + "" ) );
		move += ( Integer.parseInt( raw.charAt(1) + "" ) + 1 ) + "";
		
		return move;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(e.getActionCommand());
		// Addition input retrieval
		if ( e.getActionCommand().compareTo( "Check option selected" ) == 0 ){
			
			input.writeToBuffer( optionGroup.getSelection().getActionCommand() );
			optw.dispose();
			optw = null;
		}
		
		// Board movement retrieval
		else {
			
			if ( movePt1 == null ){
				
				movePt1 = convertToMove( e.getActionCommand() );
			}
			else if ( movePt2 == null ){
				
				movePt2 = convertToMove( e.getActionCommand() );
			}
		}
		
		if ( movePt1 != null && movePt2 != null ){
			
			String fullMove = movePt1 + "-" + movePt2;
			movePt1 = null;
			movePt2 = null;
			System.out.println(fullMove);
			input.writeToBuffer( fullMove );
		}
	}
}
