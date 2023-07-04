package Arrays;

import TurtleGraphics.KeyboardReader;

public class MancalaMethods 

{
	public void MancalaGame(String player1, String player2, int stonestoplay)
	{
		KeyboardReader reader = new KeyboardReader();
		
		int[] playerpiles = new int[15];
		int player1sum=0, player2sum=0, pileselectp1=0, pileselectp2=0, stonesdrop=0, finalpile=0, finalpilequantity=0, pileacross=0;
		char gameover='f';

		// place stones in each pile for initial board
		for(int a=1; a<7; a++)
		{
			playerpiles[a]=stonestoplay;
			playerpiles[a+7]=stonestoplay;
		}

		playerpiles[7]=0; // player 1 mancala
		playerpiles[14]=0; // player 2 mancala

		// draw the initial board
		CreateBoard(playerpiles);
		
		while(gameover!='t')
		{
			// check the sum of player 1's piles
			for(int x=1; x<7; x++)
			{
				player1sum=player1sum+playerpiles[x];
			}
			
			// end game if sum is 0
			if(player1sum==0)
			{
				// sum up opponent's stones and add to their mancala
				for(int y=8; y<14; y++)
				{
					playerpiles[14]=playerpiles[14]+playerpiles[y];
					playerpiles[y]=0;
				}
				
				if(playerpiles[7]>playerpiles[14])
				{
					System.out.println(player1+" won the game! "+player2+" lost.");
				}
				
				else if(playerpiles[14]>playerpiles[7])
				{
					System.out.println(player2+" won the game! "+player1+" lost.");
				}
				CreateBoard(playerpiles);
				break;
			}
			
			// take player 1's selection
			System.out.println(player1+", please select one of your piles (1-6).");
			pileselectp1=reader.readInt();
			
			// make sure player 1 selects in the correct range
			while((pileselectp1<1)||(pileselectp1>6))
			{
				System.out.println("Please reselect in the valid range of piles (1-6).");
				pileselectp1=reader.readInt();
			}
			
			// if pile has no stones, pick a different pile
			while(playerpiles[pileselectp1]==0)
			{
				System.out.println("This pile has no stones. Please pick another pile.");
				pileselectp1=reader.readInt();
				
				while((pileselectp1<1)||(pileselectp1>6))
				{
					System.out.println("Please reselect in the valid range of piles (1-6).");
					pileselectp1=reader.readInt();
				}
			}
			
			// begin dropping/distributing stones
			stonesdrop=playerpiles[pileselectp1];
			
			// set the pile selected to 0
			playerpiles[pileselectp1]=0;
			
			for(int i=(pileselectp1+1); stonesdrop>0; i++,stonesdrop--)
			{
				// if you reach the opponent's mancala, skip it and go back to your first pile
				if(i==14)
				{
					i=1;
				}
				
				playerpiles[i]=playerpiles[i]+1;
				// find final pile
				finalpile=i;
				// find original quantity in final pile
				finalpilequantity=playerpiles[i];
			}
			
			// draw new board
			CreateBoard(playerpiles);
			
			player1sum=0;
			
			if(finalpile!=7)
			{
				for(int x=1; x<7; x++)
				{
					player1sum=player1sum+playerpiles[x];
				}
				
				if(player1sum==0)
				{
					for(int y=8; y<14; y++)
					{
						playerpiles[14]=playerpiles[14]+playerpiles[y];
						playerpiles[y]=0;
					}
					
					if(playerpiles[7]>playerpiles[14])
					{
						System.out.println(player1+" won the game! "+player2+" lost.");
					}
					
					else if(playerpiles[14]>playerpiles[7])
					{
						System.out.println(player2+" won the game! "+player1+" lost.");
					}
					
					CreateBoard(playerpiles);
					break;
				}
				else
				{
					player1sum=0;
				}
			}
						
			// if final pile is mancala, repeat turn
			if(finalpile==7)
			{
				while(finalpile==7)
				{
					for(int x=1; x<7; x++)
					{
						player1sum=player1sum+playerpiles[x];
					}
					
					if(player1sum==0)
					{
						for(int y=8; y<14; y++)
						{
							playerpiles[14]=playerpiles[14]+playerpiles[y];
							playerpiles[y]=0;
						}
						
						gameover='t';
					}
					
					if(gameover=='t')
					{
						if(playerpiles[7]>playerpiles[14])
						{
							System.out.println(player1+" won the game! "+player2+" lost.");
						}
						
						else if(playerpiles[14]>playerpiles[7])
						{
							System.out.println(player2+" won the game! "+player1+" lost.");
						}
						
						CreateBoard(playerpiles);
						break;
					}
					
					System.out.println(player1+", you landed in your mancala. You get another turn.");
					System.out.println("Please select one of your piles (1-6).");
					pileselectp1=reader.readInt();
				
					while((pileselectp1<1)||(pileselectp1>6))
					{
						System.out.println("Please reselect in the valid range of piles (1-6).");
						pileselectp1=reader.readInt();
					}
				
					while(playerpiles[pileselectp1]==0)
					{
						System.out.println("This pile has no stones. Please pick another pile.");
						pileselectp1=reader.readInt();
						
						while((pileselectp1<1)||(pileselectp1>6))
						{
							System.out.println("Please reselect in the valid range of piles (1-6).");
							pileselectp1=reader.readInt();
						}
					}
				
					stonesdrop=playerpiles[pileselectp1];
				
					playerpiles[pileselectp1]=0;
				
					for(int i=(pileselectp1+1); stonesdrop>0; i++,stonesdrop--)
					{
						if(i==14)
						{
							i=1;
						}
						
						playerpiles[i]=playerpiles[i]+1;
						finalpile=i;
						finalpilequantity=playerpiles[i];
					}
				
					CreateBoard(playerpiles);
				}
			}
			
			if(gameover=='t')
			{
				break;
			}
			
			// if final pile is across an empty pile, take all the stones
			if(finalpile!=7)
			{
				// determine which pile is across
				pileacross=14-finalpile;
				
				// make sure pile across has stones in it
				if(playerpiles[pileacross]!=0)
				{
					// make sure empty pile was on your side
					if((finalpile>0)&&(finalpile<7))
					{	
							// if there is now only 1 stone in the pile, it had 0 stones before
							if(finalpilequantity==1)
							{
								// add the 1 stone you dropped and the stones in the pile across to your mancala
								playerpiles[7]=playerpiles[7]+playerpiles[pileacross]+1;
								System.out.println("You dropped a stone in an empty pile! You get two piles of stones in your mancala.");
					
								// empty the 2 piles that were across from each other
								playerpiles[finalpile]=0;
								playerpiles[pileacross]=0;
								CreateBoard(playerpiles);
							}
					}
				
					player1sum=0;
					
					
					for(int x=1; x<7; x++)
					{
						player1sum=player1sum+playerpiles[x];
					}
						
					if(player1sum==0)
					{
						for(int y=8; y<14; y++)
						{
							playerpiles[14]=playerpiles[14]+playerpiles[y];
							playerpiles[y]=0;
						}
							
						if(playerpiles[7]>playerpiles[14])
						{
							System.out.println(player1+" won the game! "+player2+" lost.");
						}
							
						else if(playerpiles[14]>playerpiles[7])
						{
							System.out.println(player2+" won the game! "+player1+" lost.");
						}
							
						CreateBoard(playerpiles);
						break;
					}
					else
					{
						player1sum=0;
					}
				}
			}
			
			// reset player 1's variables
			player1sum=0;
			finalpile=0;
			finalpilequantity=0;
			pileacross=0;
			
			// check the sum of player 2's piles
			for(int x=8; x<14; x++)
			{
				player2sum=player2sum+playerpiles[x];
			}
						
			// end game if sum is 0
			if(player2sum==0)
			{
				// sum up opponent's stones and add to their mancala
				for(int y=1; y<7; y++)
				{
					playerpiles[7]=playerpiles[7]+playerpiles[y];
					playerpiles[y]=0;
				}

				if(playerpiles[7]>playerpiles[14])
				{
					System.out.println(player1+" won the game! "+player2+" lost.");
				}
				
				else if(playerpiles[14]>playerpiles[7])
				{
					System.out.println(player2+" won the game! "+player1+" lost.");
				}

				CreateBoard(playerpiles);
				break;
			}
						
			// take player 2's selection
			System.out.println(player2+", please select one of your piles (8-13).");
			pileselectp2=reader.readInt();
						
			// make sure player 2 selects in the correct range
			while((pileselectp2<8)||(pileselectp2>13))
			{
				System.out.println("Please reselect in the valid range of piles (8-13).");
				pileselectp2=reader.readInt();
			}
						
			// if pile has no stones, pick a different pile
			while(playerpiles[pileselectp2]==0)
			{
				System.out.println("This pile has no stones. Please pick another pile.");
				pileselectp2=reader.readInt();
				
				while((pileselectp2<8)||(pileselectp2>13))
				{
					System.out.println("Please reselect in the valid range of piles (8-13).");
					pileselectp2=reader.readInt();
				}
			}
						
			// begin dropping/distributing stones
			stonesdrop=playerpiles[pileselectp2];
						
			// set the pile selected to 0
			playerpiles[pileselectp2]=0;
						
			for(int i=(pileselectp2+1); stonesdrop>0; i++,stonesdrop--)
			{
				// if you reach the opponent's mancala, skip it and go back to your first pile
				if(i==7)
				{
					i=8;
				}
				
				// to prevent index from going out of bounds
				if(i==15)
				{
					i=1;
				}
							
				playerpiles[i]=playerpiles[i]+1;
				// find final pile
				finalpile=i;
				// find original quantity in final pile
				finalpilequantity=playerpiles[i];
			}
						
			// draw new board
			CreateBoard(playerpiles);
			
			player2sum=0;
			
			if(finalpile!=14)
			{
				for(int x=8; x<14; x++)
				{
					player2sum=player2sum+playerpiles[x];
				}
				
				if(player2sum==0)
				{
					for(int y=1; y<7; y++)
					{
						playerpiles[7]=playerpiles[7]+playerpiles[y];
						playerpiles[y]=0;
					}
					
					if(playerpiles[7]>playerpiles[14])
					{
						System.out.println(player1+" won the game! "+player2+" lost.");
					}
					
					else if(playerpiles[14]>playerpiles[7])
					{
						System.out.println(player2+" won the game! "+player1+" lost.");
					}
					
					CreateBoard(playerpiles);
					break;
				}
				else
				{
					player2sum=0;
				}
			}
									
			// if final pile is mancala, repeat turn
			if(finalpile==14)
			{
				while(finalpile==14)
				{
					for(int x=8; x<14; x++)
					{
						player2sum=player2sum+playerpiles[x];
					}
								
					if(player2sum==0)
					{
						for(int y=1; y<7; y++)
						{
							playerpiles[7]=playerpiles[7]+playerpiles[y];
							playerpiles[y]=0;
						}
						
						gameover='t';
					}
					
					if(gameover=='t')
					{
						if(playerpiles[7]>playerpiles[14])
						{
							System.out.println(player1+" won the game! "+player2+" lost.");
						}
						
						else if(playerpiles[14]>playerpiles[7])
						{
							System.out.println(player2+" won the game! "+player1+" lost.");
						}
						
						CreateBoard(playerpiles);
						break;
					}
								
					System.out.println(player2+", you landed in your mancala. You get another turn.");
					System.out.println("Please select one of your piles (8-13).");
					pileselectp2=reader.readInt();
							
					while((pileselectp2<8)||(pileselectp2>13))
					{
						System.out.println("Please reselect in the valid range of piles (8-13).");
						pileselectp2=reader.readInt();
					}
							
					while(playerpiles[pileselectp2]==0)
					{
						System.out.println("This pile has no stones. Please pick another pile.");
						pileselectp2=reader.readInt();
						
						while((pileselectp2<8)||(pileselectp2>13))
						{
							System.out.println("Please reselect in the valid range of piles (8-13).");
							pileselectp2=reader.readInt();
						}
					}
							
					stonesdrop=playerpiles[pileselectp2];
							
					playerpiles[pileselectp2]=0;
							
					for(int i=(pileselectp2+1); stonesdrop>0; i++,stonesdrop--)
					{
						if(i==7)
						{
							i=8;
						}
						
						if(i==15)
						{
							i=1;
						}
									
						playerpiles[i]=playerpiles[i]+1;
						finalpile=i;
						finalpilequantity=playerpiles[i];
					}
							
					CreateBoard(playerpiles);
					}
				}
			
			if(gameover=='t')
			{
				break;
			}
						
				// if final pile is across an empty pile, take all the stones
				if(finalpile!=14)
				{
					// determine which pile is across
					pileacross=14-finalpile;
					
					// make sure pile across has stones in it
					if(playerpiles[pileacross]!=0)
					{
						// make sure empty pile was on your side
						if((finalpile>7)&&(finalpile<14))
						{
							// if there is now only 1 stone in the pile, it had 0 stones before
							if(finalpilequantity==1)
							{
								// add the 1 stone you dropped and the stones in the pile across to your mancala
								playerpiles[14]=playerpiles[14]+playerpiles[pileacross]+1;
								System.out.println("You dropped a stone in an empty pile! You get two piles of stones in your mancala.");
										
								// empty the 2 piles that were across from each other
								playerpiles[finalpile]=0;
								playerpiles[pileacross]=0;
								CreateBoard(playerpiles);
							}
							
						}
					}
					
					player2sum=0;
					
					for(int x=8; x<14; x++)
					{
						player2sum=player2sum+playerpiles[x];
					}
						
					if(player2sum==0)
					{
						for(int y=1; y<7; y++)
						{
							playerpiles[7]=playerpiles[7]+playerpiles[y];
							playerpiles[y]=0;
						}
						
						if(playerpiles[7]>playerpiles[14])
						{
							System.out.println(player1+" won the game! "+player2+" lost.");
						}
							
						else if(playerpiles[14]>playerpiles[7])
						{
							System.out.println(player2+" won the game! "+player1+" lost.");
						}
							
						CreateBoard(playerpiles);
						break;
					}
					else
					{
						player2sum=0;
					}
				}
				
			// reset player 2's variables
			player2sum=0;
			finalpile=0;
			finalpilequantity=0;
			pileacross=0;
		}
		
	}
	
	public void CreateBoard(int[] playerpiles)
	{
		System.out.print(" ");
		// print piles 7-12 (they are player 2's piles)
		for(int a=13; a>=8; a--)
		{
			System.out.print(playerpiles[a]+" ");
		}
		System.out.println("");
		
		// print the mancala's of the 2 players
		System.out.print(playerpiles[14]);
		System.out.print("           "+playerpiles[7]);
		System.out.println("");

		System.out.print(" ");
		// print piles 1-6 (they are player 1's piles)
		for(int b=1; b<=6; b++)
		{
			System.out.print(playerpiles[b]+" ");
		}
		System.out.println("");
		
		// print the characters to label the piles
		System.out.println(" A B C D E F");
	}
}