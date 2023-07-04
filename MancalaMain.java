package Arrays;

import TurtleGraphics.KeyboardReader;

public class MancalaMain {

	public static void main(String[] args) 
	{
		// Objective: try to get most stones in mancala
		// Select piles only on your side, cannot select a pile with 0 stones
		// Move counterclockwise
		// If you end in your mancala pile, you get another turn
		// Can take stones directly across from opponent if you drop your last stone in an empty pile on your side
			// Move all stones in pile across and pile you dropped the final stone in - +1
			// Cannot do this if last stone ends up in opponent empty pile because you would take your own stones
			// If the pile across is empty, you get no stones
		// When moving stones around counterclockwise, skip opponent bin
		// If you have empty piles, remaining stones go to opposite person's mancala pile
		
		KeyboardReader reader = new KeyboardReader();
		MancalaMethods object = new MancalaMethods();

		int stonestoplay=0;
		String player1, player2;
		char instructions;
		
		System.out.println("Hello! Welcome to Mancala. Please enter player 1's name. ");
		player1=reader.readLine();

		System.out.println("Please enter player 2's name.");
		player2=reader.readLine();

		System.out.println("Enter '$' if you would like to read the rules of Mancala or '&' if you would like to skip to the game.");
		instructions=reader.readChar();

		if(instructions=='$')
		{
			System.out.println("Your objective is to get the most stones in your mancala, or home bin, pile.");
			System.out.println("Select a pile from your side of the board. You cannot select a pile with 0 stones.");
			System.out.println("You will drop one stone in each pile as you move counterclockwise until you have no stones left (you will skip the opponent's bin).");
			System.out.println("Landing in your mancala will give you another turn.");
			System.out.println("If you drop a stone in an empty pile on your side, you get all the stones in the pile across from it and the one stone you dropped for your mancala.");
			System.out.println("If you have all empty piles and can no longer play, all remaining stones in the board go to the opponent's mancala, and you lose.");
			System.out.println("Note: this is how the piles will be labeled numerically.");
			System.out.println("   13 12 11 10 9 8 ");
			System.out.println("14                 7");
			System.out.println("   1  2  3  4  5 6");
			System.out.println(" A B C D E F");
			System.out.println("Piles 1-6 belong to "+player1+" to choose. Piles 8-13 belong to "+player2+".");
			System.out.println(player1+"'s mancala is pile 7, and "+player2+"'s mancala is pile 14. You cannot choose these piles.");
			
			System.out.println("Enter how many stones you want to play with (2-4).");
			stonestoplay=reader.readInt();
			while((stonestoplay<2)||(stonestoplay>4))
			{
				System.out.println("That is an invalid number of stones to play with. Enter a different number (2-4).");
				stonestoplay=reader.readInt();
			}
			
			System.out.println("Ready to play? Press '&'.");
			instructions=reader.readChar();
			if(instructions=='&')
			{
				object.MancalaGame(player1, player2, stonestoplay);
			}
		}

		else if(instructions=='&')
		{
			System.out.println("Note: this is how the piles will be labeled numerically.");
			System.out.println("   13 12 11 10 9 8 ");
			System.out.println("14                 7");
			System.out.println("   1  2  3  4  5 6");
			System.out.println("   A  B  C  D  E F");
			System.out.println("Piles 1-6 belong to "+player1+" to choose. Piles 8-13 belong to "+player2+".");
			System.out.println(player1+"'s mancala is pile 7, and "+player2+"'s mancala is pile 14. You cannot choose these piles.");
			
			System.out.println("Enter how many stones you want to play with (2-4).");
			stonestoplay=reader.readInt();
			while((stonestoplay<2)||(stonestoplay>4))
			{
				System.out.println("That is an invalid number of stones to play with. Enter a different number (2-4).");
				stonestoplay=reader.readInt();
			}
			
			object.MancalaGame(player1, player2, stonestoplay);
		}

	}

}