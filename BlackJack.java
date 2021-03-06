import java.util.Random;   // imports utilities
import java.util.Scanner;

public class PlayTwentyOne {
	public static Random rand = new Random();   // new variables for utilities
	public static Scanner inp = new Scanner(System.in);
	
	public int getCard(int card, boolean isDealer) {   // generates cards from 2 to 10 or ace
		if (rand.nextInt(13) == 0 && isDealer == false) {   // accurate probability for ace
			System.out.print("You received an Ace card! 1 or 11? ");
			
			while (true) {   // error handling
				switch (inp.nextInt()) {   // returns the user's choice
				case 1:
					return 1;
					
				case 11:
					return 11;
					
				default:
					System.out.print("\nTry again! ");
				}
			}
			
		} else if (rand.nextInt(13) == 0 && isDealer == true) {
			if (rand.nextInt(2) == 0) {   // 50% probability
				return 1;
				
			} else {
				return 11;
			}
		}
		
		return rand.nextInt(9) + 2;
	}
	
	public static void main(String args[]) { 
		PlayTwentyOne cardInst = new PlayTwentyOne();   // instances the getCard method
		
		int card1 = cardInst.getCard(0, false), card2 = cardInst.getCard(0, false);   // assign cards
		int total = card1 + card2; // total in-hand
		
		int card3 = cardInst.getCard(0, true), card4 = cardInst.getCard(0, true);   // assigns cards for the dealer
		int dealerTotal = card3 + card4;
		
		System.out.printf("You received: %s and %s\n", card1, card2);   // prompts the user (string concatenation)
		
		while (total <= 21) {   // loop only if total cards in hand are 21 or under
			if (dealerTotal > 21) {
				System.out.printf("\nYou vs. Dealer: %s - %s\n", total, dealerTotal);   // (string concatenation)
				System.out.println("\nYou win!");
				System.exit(0);   // closes program
			}
			
			System.out.print("\nReceive another card? (Y/N) ");
			
			if (inp.next().toLowerCase().equals("y")) {   // error handling
				int newCard = cardInst.getCard(0, false);   // gets new card
				
				total += newCard;   // adds new card to hand
				dealerTotal += cardInst.getCard(0, true);   // adds new card for dealer
				
				System.out.println("\nYou received: " + newCard);
				System.out.println("Total: " + total);   // displays total
				
			} else {
				System.out.println("\nTotal: " + total);
				System.out.print("\nWould you like to stop? (Y/N) ");   // prompts user
				
				if (inp.next().toLowerCase().equals("y")) {
					if (dealerTotal <= 15) {   // pseudo-AI for dealer
						dealerTotal += cardInst.getCard(0, true);   
					}
					
					System.out.printf("\nYou vs. Dealer: %s - %s\n", total, dealerTotal);   // (string concatenation)
					
					if (total < dealerTotal) {
						break;   // breaks the loop to display loss
						
					} else if (total == dealerTotal) {   // tie
						System.out.println("\nYou Tie!");
						System.exit(0);   // closes program
						
					} else {
						System.out.println("\nYou win!");   // win
						System.exit(0);   // closes program
					}
				}
			}
		}
		
		System.out.println("\nYou have lost! ");
		inp.close();
	}
}
