8Chess

How to Setup:

	IDE: 
		NetBeans: 
			-Create new project in netbeans (Named 8Chess). 
			-Delete all initialized class files and packaged in Source Packages (Netbeans autogenerates)
			 -Drag and drop the Folders: Controller,Model, and View (found within src folder), into Source Packages (located within Netbeans, left hand side)
			-Click on Controller-> GameController, to allow Netbeans to find the Main function.
			-Under File->Project Properties, in the Libraries tab, select Add Jar/Folder, and 
			  choose the image folder labelled res inside 8Chess.


		Eclipse:
			-Create new project if (none exists)
			-Choose File->Import project. 
				-Select General -> File System, choose 8Chess
					-yes to all


	NON-IDE:
		Please don't ask us how to javac this...

How to Run:
	Enter 1 for Text view, 2 for Gui.

	Choose chess variant via input number.
	
	Hot to Move:
		Text:
			Enter Start-End via A7-A8 format.
		Gui:
			Click on starting chess Piece, click on destination




Variants Chosen:

	Classic
		-Castling		= Special King - Rook move 
		-En Pessant		= Special Initial Pawn Move
		-CheckMate		= Win condition for classic Chess
		-StaleMate		= Tie condition for classic chess
		-Promotion		= When Pawn reaches end, promote to any Piece
		-Check 			= A players move is restricted to not allow placing a king in check, or ending a turn in check if possible.

	Absorption
		-Absorb			= All pieces excluding Kings and pawns, gain the movement abilities of captured pieces
						ie. Rook takes bishop results in rook becoming a queen (in regards to movement)
		-Classic Chess Rules

	Cheshire Cat
		-Vanishing Tiles = When a tile is vacated, the tile is marked as "vanished" and a piece may not end it's turn on it.
		-Special King 	 = The king may move like a queen on its initial turn 
		-Classic rules  excluding castling

	Suicide
		-Piece Capture = if a move exists where you can capture an enemy piece, you must do so
		-Suicide Win 	= You win by having zero pieces on the board.
		-Classic rules excluding check, checkmate,stalemate

	Jedi Knight
		-Knight movement = Knight move vertically and horizontally up to 3 spaces in addition to regular move
		-Classic Rules 	 

	Atomic
		-Boom/Explode 	= During a capture move all non pawns in the surrounding radius get removed from the game
		-Atomic win 	= Game ends when a team no longer has a king
		-Classic Rules excluding checkmate, stalemate 

	Hobbit
		-Pawn movement 	= Pawns can now move Vertically and horizontally (including backwards), but may not use en pessant 
		 				nor promotion. (Still must capture diagonally)
		-Classic Rules excluding promotion, enpessant

	Stationary King		
		-Special King 	= King may not move during the game
		-Classic Rules excluding castling

	



Testing:

	Abbot:
		To Run Abbot:
						Navigate to 8Chess/abbot-1.2.0/abbot-1.2.0/ and run abbot.jnlp. Say yes to any security questions regarding java. Select File->Open, and navigate 1 up and select any of the following files located there:
										Abbot_TestGUI
										Abbot_TestGUI_Classic
										Abbot_TestGUI_Classic_PieceMovement
										Abbot_TestGUI_Classic_Promotion
										Abbot_TestGUI_Classic_Collision
										Abbot_TestGUI_Classic_Collision_MoreInDepth
	
	Aruba:
		Environment setup:
						Aruba requires Ruby to be installed on your machine, to install, follow these instructions: https://www.ruby-lang.org/en/installation/
					 	Once you have Ruby installed, run the command `gem install bundler`
		To Run Aruba:
						Navigate to 8Chess on the command line
						Run `bundle install`
						Then run `cucumber` to run through the test cases	