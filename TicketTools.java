/*
* This class holds any functions that work with the TicketRecords
* class and Ticket objects that do not change information within
* TicketRecords it self, and can be outside of the class.
*/
package lotterysim;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Cory
 */
public class TicketTools {
    
    //Generates a set of random winners with no duplicates to assign to tickets.
    public static int[] generateWinners(int totalWins, int tickets) {
        Random rand = new Random();
        //Blank array of winners the size of how many specified in the records.
        int[] picks = new int[totalWins];
        //Initial winner variable.
        int n = rand.nextInt(tickets) + 1;
        picks[0] = n;
        //Assumption is we have no duplicate winner.
        boolean duplicate = false;
        
        //Checks all previous winners for duplicates.
        int i = 1;
        while(i != picks.length) {
            //Random # from 1 to our max ticket count.
            n = rand.nextInt(tickets) + 1;
           
            //If we find a duplicate in the winners list we do nothing with it.
            for(int j = 0; j < picks.length; j++) {
                if(picks[j] == n) {
                    duplicate = true;
                }
            }
            
            //If it is not a duplicate winner we add it to the list and move on.
            if(duplicate == false) {
                picks[i] = n;
                i++;
            }
        }
        
        //Using a built in sort to make sure the prizes are given in order.
        Arrays.sort(picks);
        
        //List the winning #s for development purposes only.
        System.out.print("Winning numbers are now: ");
        for(int c = 0; c < picks.length; c++) {
            System.out.print(picks[c] + " ");
        }
        return picks;
    }
    
    /*
    * Draws all purchased tickets using the array of ticket objects, the
    * variable displaying sold tickets, and how many winning tiers you have.
    */
    public static void drawAll(Ticket[] roll, int tickets, String[] place) {
        System.out.println("\nDrawing all...");
    	for(int i = 0; i < tickets; i++) {
            if(roll[i].getPlace() > 0) {
                System.out.print("\nTicket no." + roll[i].getPlace()
                        + " was sold to " + roll[i].getName() + ".");
                if(roll[i].getWinner() > 0) {
                    System.out.print(" " + place[roll[i].getWinner() - 1]
                            + " place winner!");
                }
            }
        }
    }
    
    /*
    * Draws/shows all winning tickets using the array of ticket objects, the
    * variable displaying sold tickets, and how many winning tiers you have.
    */
    public static void drawWinners(Ticket[] roll, int tickets, String[] place) {
        //Used to notify the user if there are no winners for a draw at this time.
    	boolean anyWinners = false;
    	
        System.out.println("\nDrawing winners...");
    	for(int i = 0; i < tickets; i++) {
                if(roll[i].getWinner() > 0) {
                    System.out.print("\nTicket no." + roll[i].getPlace()
                        + " was sold to " + roll[i].getName() + ".");
                    System.out.print(" " + place[roll[i].getWinner() - 1]
                            + " place winner!");
                    anyWinners = true;
                }
        }
    	if(anyWinners == false) {
    		System.out.println("There are no winners at this time.");
    	}
    }
    
    //Tell the user which tickets were not sold, and which ones were winners.
    public static void showUnsold(int min, int max, boolean[] givenPrizes, int[] winningNumbers) {
    	if(min != max) {
    		System.out.println("Tickets no." + (min + 1) + "-" + max + " were not sold.");
    		for(int i = 0; i < givenPrizes.length; i++) {
        		if(givenPrizes[i] == false) {
        			System.out.println(winningNumbers[i] + " was an unsold winner.");
        		}
        	}
    		System.out.println("");
    	}
    }
    
}