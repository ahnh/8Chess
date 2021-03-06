package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import Controller.Player;
import Model.Board;
import Model.Piece;
import Model.Tile;

public class ViewGUI extends ViewBase implements ActionListener {

	Player input;
	boolean gridSetup;
	String movePt1, movePt2;
	int[] movePt1but;
	Color boardColor = Color.getHSBColor(23, 21, 36); // default easy on eyes hue 23, 21, 36
	
	private final int butSize = 75; // Size of button width/height, scales everything to match
	
	JFrame gw, optw;

	// Main window items
	JLabel gameMessage;
	JButton[][] tiles;
	Panel boardArea;

	// Option window items
	private ButtonGroup optionGroup;
	private JRadioButton[] option;
	private JButton accept;

	public ViewGUI(Player player) {

		super();

		input = player;
		gridSetup = false;
		movePt1 = null;
		movePt2 = null;
		movePt1but = new int[2];
		
		// Set up main game window
		gw = new JFrame();
		gw.setTitle("Chess");
		gw.setSize(new Dimension(600, 600));
		gw.setPreferredSize(new Dimension(600, 600));
		gw.setFocusable(true);
		gw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gw.setLayout(new FlowLayout());

		gameMessage = new JLabel("Game is ready to go.");
		
		gameMessage.setFont( new Font( "Ariel", Font.BOLD, 16 ) );
		gameMessage.setAlignmentX( JLabel.CENTER_ALIGNMENT );
		
		boardArea = new Panel();

		Box horMsg = Box.createHorizontalBox();

		horMsg.add(Box.createHorizontalGlue());
		horMsg.add(gameMessage);
		horMsg.add(Box.createHorizontalGlue());

		Box vertMain = Box.createVerticalBox();

		vertMain.add(Box.createVerticalGlue());
		vertMain.add(Box.createVerticalStrut(5));
		vertMain.add(horMsg);
		vertMain.add(Box.createVerticalGlue());
		vertMain.add(boardArea);
		vertMain.add(Box.createVerticalGlue());

		Box masterBox = Box.createHorizontalBox();

		masterBox.add(Box.createHorizontalGlue());
		masterBox.add(vertMain);
		masterBox.add(Box.createHorizontalStrut(15));
		masterBox.add(Box.createHorizontalGlue());

		gw.add(masterBox);

		gw.pack();
		gw.setLocationRelativeTo(null);
		gw.toFront();
	}

	public boolean displayMessage(String msg) {

		gameMessage.setText(msg);
		gw.revalidate();

		return true;
	}

	public boolean display(Board board) {

		Piece piece = null;
		Tile tile = null;
		
		if (gridSetup == false) {

			String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			
			gw.setSize(new Dimension( butSize + (board.getWidth()+1)*butSize, 80 + (board.getHeight()+1)*butSize ) );
			gw.setPreferredSize(new Dimension( butSize + (board.getWidth()+1)*butSize, 80 + (board.getHeight()+1)*butSize ) );
			
			boardArea.setLayout(new GridLayout(0, board.getWidth() + 1));
			tiles = new JButton[board.getWidth()][board.getHeight()];

			// alpha index along top
			boardArea.add(new JLabel(""));

			for (int j = 0; j < board.getWidth(); j++) {

				boardArea.add(new JLabel("" + alpha.charAt(j), JLabel.CENTER));
			}

			// Put button pieces on the board area
			for (int i = 0; i < board.getHeight(); i++) {

				boardArea.add(new JLabel("" + (i + 1), JLabel.CENTER));

				for (int j = 0; j < board.getWidth(); j++) {

					piece = board.getTile(new Point(j, i)).getPiece();

					if (piece == null) {

						tiles[j][i] = new JButton("");
						tiles[j][i].setIcon( null );
					} else {

						tiles[j][i] = new JButton(piece.getName());
						tiles[j][i].setIcon( getIcon( piece.getName(), piece.getTeam() ) );
						
						if ( piece.getTeam() == 2 ){
							tiles[j][i].setForeground( Color.magenta );
						}
						else {
							tiles[j][i].setForeground( Color.blue );
						}
					}
					
					tiles[j][i].setPreferredSize( new Dimension( butSize, butSize) );
					tiles[j][i].setBorder( new LineBorder(Color.BLACK) );
					tiles[j][i].setBackground( boardColor );
					tiles[j][i].setHorizontalTextPosition(JButton.CENTER);
					tiles[j][i].setVerticalTextPosition(JButton.CENTER);
					tiles[j][i].setActionCommand( "" + j + "" + i );
					tiles[j][i].addActionListener( this );
					
					boardArea.add( tiles[j][i] );
				}
			}

			gridSetup = true;
			gw.setVisible(true);

			return true;
		}

		// Update buttons to reflect the board
		for (int i = 0; i < board.getHeight(); i++) {

			for (int j = 0; j < board.getWidth(); j++) {
				
				tile = board.getTile(new Point(j, i));
				piece = tile.getPiece();

				if (piece == null) {

					tiles[j][i].setText("");
					tiles[j][i].setIcon( null );
					
					if ( tile.getExists() == false ){
						
						tiles[j][i].setBackground( Color.BLACK );
					}
					
				} else {

					tiles[j][i].setText(piece.getName());
					tiles[j][i].setIcon( getIcon( piece.getName(), piece.getTeam() ) );
					
					if ( piece.getTeam() == 2 ){
						tiles[j][i].setForeground( Color.magenta );
					}
					else {
						tiles[j][i].setForeground( Color.blue );
					}
				}
			}
		}

		gw.revalidate();

		return true;
	}

	public boolean displayOptions(String msg, String[] opts) {

		optw = new JFrame();

		// Set up option window
		optw.setTitle("Choose an Option");
		optw.setSize(new Dimension(300, 140 + (25 * opts.length)));
		optw.setPreferredSize(new Dimension(300, 140 + (25 * opts.length)));
		optw.setFocusable(true);
		optw.setResizable(false);
		optw.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		optw.setLayout(new FlowLayout());

		// Create component for the option window
		JLabel question = new JLabel(msg);

		accept = new JButton("Choose");

		optionGroup = new ButtonGroup();
		option = new JRadioButton[opts.length];

		for (int i = 0; i < opts.length; i++) {

			String temp = "" + (i + 1);

			option[i] = new JRadioButton(opts[i]);
			option[i].getModel().setActionCommand(temp);
			optionGroup.add(option[i]);
		}
		optionGroup.setSelected(option[0].getModel(), true);

		accept.addActionListener(this);
		accept.setActionCommand("Check option selected");
		accept.setSize(new Dimension(30, 20));
		accept.setText("Accept");

		// Create box organization for parts
		Box horiz = Box.createVerticalBox();

		horiz.add(Box.createVerticalGlue());
		horiz.add(question);
		horiz.add(Box.createVerticalStrut(5));

		for (int i = 0; i < opts.length; i++) {

			horiz.add(Box.createVerticalStrut(5));
			horiz.add(option[i]);
		}

		horiz.add(Box.createVerticalStrut(10));
		horiz.add(accept);
		horiz.add(Box.createVerticalGlue());

		optw.add(Box.createHorizontalGlue());
		optw.add(horiz);
		optw.add(Box.createHorizontalGlue());

		optw.pack();
		optw.setVisible(true);
		optw.setLocationRelativeTo(null);
		optw.toFront();

		return true;
	}

	public boolean displayGameOver(int winner, Board board) {

		if (winner != 0) {

			this.displayMessage("Congratulations player " + winner
					+ ", you win!");
		} else {

			this.displayMessage("It's a draw!");
		}
		this.display(board);

		return true;
	}

	// Convert array index format into move input format for game controller
	private String convertToMove(String raw) {

		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String move = "";

		move += alpha.charAt(Integer.parseInt(raw.charAt(0) + ""));
		move += (Integer.parseInt(raw.charAt(1) + "") + 1) + "";

		return move;
	}
	
	private ImageIcon getIcon( String fullname, int team ){
		
		ImageIcon newImg;
		StringTokenizer tok = new StringTokenizer( fullname, "-");
		String name = tok.nextToken();
		
		if ( team == 1 ){
			try {
				newImg = new ImageIcon( getClass().getResource("/white/" + name + ".png") );
			} catch ( Exception e ){
				
				return null;
			}
			return new ImageIcon( newImg.getImage().getScaledInstance( (int)(butSize * 0.75), (int)(butSize* 0.75),  Image.SCALE_SMOOTH ) );
		}
		
		try {
			newImg = new ImageIcon( getClass().getResource("/black/" + name + ".png") );
		} catch ( Exception e ){
			
			return null;
		}
		return new ImageIcon( newImg.getImage().getScaledInstance( (int)(butSize* 0.75), (int)(butSize * 0.75),  Image.SCALE_SMOOTH ) );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Addition input retrieval
		if (e.getActionCommand().compareTo("Check option selected") == 0) {

			input.writeToBuffer(optionGroup.getSelection().getActionCommand());
			optw.dispose();
			optw = null;
		}

		// Board movement retrieval
		else {

			if (movePt1 == null) {
				
				movePt1but[0] = Integer.parseInt( e.getActionCommand().charAt(0) + "" );
				movePt1but[1] = Integer.parseInt( e.getActionCommand().charAt(1) + "" );
				
				movePt1 = convertToMove(e.getActionCommand());
				
				tiles[movePt1but[0]][movePt1but[1]].setBackground( Color.RED );
				
			} else if (movePt2 == null) {
				
				movePt2 = convertToMove(e.getActionCommand());
			}
		}

		if (movePt1 != null && movePt2 != null) {

			String fullMove = movePt1 + "-" + movePt2;
			
			tiles[movePt1but[0]][movePt1but[1]].setBackground( boardColor );
			
			movePt1but[0] = -1;
			movePt1but[1] = -1;
			
			movePt1 = null;
			movePt2 = null;
			
			input.writeToBuffer(fullMove);
		}
	}
}
