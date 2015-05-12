Capstone overview:

This capstone is a chess game. The computer plays white and the human plays black. For every
turn the computer makes a completely random move and sits there silently while the human mercilessly 
crushes the computer. The game ends when the King is captured (since I wasn't sure how to design it
so it's checkmate). 

Synthesizing concepts:

This synthesizes loops, 2D arrays, randomizing, and lots of debugging. The logic for each of my methods
was relatively straightforward for someone who plays chess however, the amount of errors that could have 
occurred were enourmous. This ended up with me having to use a try{ } catch{Exception e} in order to 
account for all of the errors that could occur. This also included setting many possibilities for every 
possible move in the position. In the end, I couldn't figure out castling and en passant.

Pseudocode moves:

For pawn moves, I needed to set a variety of conditions. While the pawn was still on the 2nd rank, I needed
to make the move one square and 2 squares possible. In addition, when a piece was diagonal to the pawn, the
pawn captures differently than it normally moves so I had to find an exception for that. In addition, pawn 
promotion was extremely difficult to code as the pawn needed to be taken off and replaced by another piece.

Running the Code:

Just run the main method in the ChessBoard class and type in the square the piece starts on, and the square
the piece ends on (in 2D array form). The computer will automatically make a reply until the game is over.
