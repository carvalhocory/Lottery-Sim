/*
 * This is where the main information for an instance of the lottery
 * sim is held and some of it can be read/manipulated. Mainly where
 * information of this class is modified alongside the ticket objects.
 */
package lotterysim;

/**
 *
 * @author Cory
 */
public class TicketRecords {
    
	//Array housing our "roll" of 50 ticket objects.
    static Ticket[] ticketRoll = new Ticket[50];
    //Purchased ticket count that goes up with each sale.
    int soldTickets = 0;
    int maxTickets = 50;
    //The randomly-generated winning numbers.
    int[] winners = new int[3];
    //Due to splits change may need to be made, hence being a double.
    double moneyPot = 200;
    double moneySplit = 0;
    //Our prize payout modifiers.
    double[] prizes = {.75, .15, .10};
    String[] places = {"First", "Second", "Third"};
    boolean[] given = {false, false, false};
    
    public TicketRecords() {
        //Generate initial winners #s.
    	winners = TicketTools.generateWinners(winners.length, maxTickets);
    	System.out.println("\nMoney pot is now: $" + moneyPot);
    }
    
    Ticket[] storePurchase(Ticket[] roll, String name) {
    	//Base case of denying purchases over the limit.
    	if(soldTickets == maxTickets) {
            System.out.println("There are no more tickets available.");
            return roll;
        }
    	
    	//Update the sold ticket with an associated number and name.
        roll[soldTickets] = new Ticket();
        roll[soldTickets].setPlace(soldTickets + 1);
        roll[soldTickets].setName(name);
        //Increment sold ticket and add $10 to our money pot.
        soldTickets++;
        moneyPot += 10;
        System.out.println(name + " has purchased ticket no." 
                + soldTickets + "\nMoney pot is now: $" + moneyPot);
        return roll;
    }
    
    /*
     * Originally there was a separate function for drawing and
     * winners, but considering they were duplicate code aside
     * from one function, a option-changing boolean makes more sense.
     */
    void DrawEither(Ticket[] roll, boolean option) {
    	//Assign purchased winners their prize.
    	ticketRoll = assignWinners(ticketRoll);
    	
    	//true means draw all, false means only winners.
    	if(option == true) {
    		TicketTools.drawAll(roll, soldTickets, places);
    	} else {
    		TicketTools.drawWinners(roll, soldTickets, places);
    	}
       
    	//Pay out each winner's respective prize.
    	ticketRoll = payWinners(ticketRoll);
    	
    	//Show the range of unsold tickets, any unsold winners.
        TicketTools.showUnsold(soldTickets, maxTickets, given, winners);
        System.out.println("Money pot is now worth $" + moneyPot + "\n"
        		+ "Enter next to start the next session.");
    }
    
    void Reset() {
    	//Resetting our variables back to their original state, generate new winners.
    	soldTickets = 0;
    	ticketRoll = new Ticket[50];
        winners = TicketTools.generateWinners(winners.length, maxTickets);
        for(int i = 0; i < given.length; i++) {
        	given[i] = false;
        }
        System.out.println("\nMoney pot is now: $" + moneyPot);
        System.out.println("New set of tickets, new winning #s generated.");
    }
    
    Ticket[] assignWinners(Ticket[] roll) {
        //Any purchased ticket that is a winning # is assigned as such.
    	for(int i = 0; i < winners.length; i++) {
            for(int j = 0; j < soldTickets; j++) {
                if(roll[j].getPlace() == winners[i]) {
                    roll[j].setWinner(i + 1);
                    //Mark that we've given the prize in case not all are given.
                    given[i] = true;
                }
            }
        }
        return roll;
    }
    
    Ticket[] payWinners(Ticket[] roll) {
    	System.out.println("\n");
    	
    	//Lock the prize pool at half the money pot given at first winner.
        double moneySplit = (moneyPot/2);
        
        //Iterate through tickets to pay winners.
        for(int i = 0; i < soldTickets; i++) {
            //The place of this winner to avoid duplicate function calls.
            int win = roll[i].getWinner();
            //Pay if it hasn't been paid, flag that it has and the money value.
            if(win > 0 && (roll[i].getPaid() == false)) {
                    roll[i].setPaid(true);
                    double prize = ((moneySplit) * prizes[win - 1]);
                    roll[i].setPrize(prize);
                    moneyPot -= prize;
                    System.out.println("" + places[win - 1] + " place value $" 
                    + roll[i].getPrize() + " awarded to " + roll[i].getName() 
                            + ".");
                }
            }
        System.out.println("");
        return roll;
    }
    
}
