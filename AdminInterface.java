/*
 * This is the interface of the lottery that just utilizes
 * other classes to perform all the functions. It can also
 * run various tests for functionality.
 */
package lotterysim;

import java.util.Scanner;

/**
 *
 * @author Cory
 */
public class AdminInterface {
    //Scanner for user input.
    Scanner sc = new Scanner(System.in);
    
    //Modifies access to the commands depending on draw status.
    boolean locked = false;
    
    TicketRecords adminRecords = new TicketRecords();
    
    void openInterface() {
        
        System.out.println("\n\nWelcome to the Lottery Administrator Interface!"
                    + "\nThe available commands are purchase, draw, winners"
                    + " reset, next, and exit.\n*There are three test cases that can"
                    + " be ran by entering test1, test2, and test3 respectively.*\n"
                    + "\n*test1 is a standard 50 ticket purchase with a draw.*\n"
                    + "*test2 is selling 25 tickets, perhaps having less than 3 or"
                    + " no winners*\n*test3 is attempting to sell more than 50 tickets"
                    + " and it not being allowed*\n");
        
        while(true) {
            
            System.out.print("Enter a command: ");
            String command = sc.nextLine();
            

            /*
             * Series of switch cases, essentially everything is locked out after a draw
             * of either kind except for a reset/next, this includes the tests considering
             * they have draws built into them. Also some logic so that draws aren't possible
             * without selling at least one ticket.
             */
            switch (command) {
                case "purchase":
                    if(locked == false) {
                    	purchaseTicket();
                    } else {
                    	locked = Lock();
                    }
                    break;
                
                case "draw":
                    if(locked == false && (adminRecords.soldTickets > 0)) {
                    	adminRecords.DrawEither(TicketRecords.ticketRoll, true);
                    } else if(locked == false) {
                    	System.out.println("A ticket must be sold before drawing.");
                    	break;
                    }
                    locked = Lock();
                    break;
                
                case "winners":
                    if(locked == false && (adminRecords.soldTickets > 0)) {
                    	adminRecords.DrawEither(TicketRecords.ticketRoll, false);
                    } else if(locked == false) {
                    	System.out.println("A ticket must be sold before drawing.");
                    	break;
                    }
                    locked = Lock();
                    break;
                
                case "reset":
                	adminRecords.moneyPot = 200;
                	adminRecords.Reset();
                    locked = Unlock();
                    break;
                
                case "next":
                    adminRecords.Reset();
                    locked = Unlock();
                    break;
                
                case "test1":
                    if(locked == false) {
                    	testOne();
                    }
                    locked = Lock();
                    break;
                
                case "test2":
                    if(locked == false) {
                    	testTwo();
                    }
                    locked = Lock();
                    break;
                
                case "test3":
                    if(locked == false) {
                    	testThree();
                    }
                    locked = Lock();
                    break;
                
                case "exit":
                    System.exit(0);
                
                default:
                    System.out.println("Please enter an available command.");
                    break;
            }
        }
    }
    
    boolean Lock() {
    	boolean status = true;
    	System.out.println("System is locked until next session or reset.");
    	return status;
    }
    
    boolean Unlock() {
    	boolean status = false;
    	return status;
    }
    
    //Takes our 50 test names, purchases a ticket for each then draws all.
    void testOne() {
        //Quickly purchase 50 tickets.
    	for(int i = 0; i < TestData.testNames.length - 5; i++) {
            TicketRecords.ticketRoll = adminRecords.storePurchase(
                TicketRecords.ticketRoll, TestData.testNames[i]);  
        }
        adminRecords.DrawEither(TicketRecords.ticketRoll, true);
    }
    
   /*
    * Shows a case where only 25 tickets are purchased, possibly showing
    * what happens with any # of winners, including none.
    */
    void testTwo() {
    	//Quickly purchase 25 tickets.
        for(int i = 0; i < TestData.testNames.length - 30; i++) {
            TicketRecords.ticketRoll = adminRecords.storePurchase(
                TicketRecords.ticketRoll, TestData.testNames[i]);  
        }
        adminRecords.DrawEither(TicketRecords.ticketRoll, true);
    }
    
    /*
     * Shows a case where 50+ tickets are purchased, it'll work like the
     * first case but remove the tedium of manually entering 50+ purchases.
     */
     void testThree() {
    	//Quickly purchase 50+ tickets.
         for(int i = 0; i < TestData.testNames.length; i++) {
             TicketRecords.ticketRoll = adminRecords.storePurchase(
                 TicketRecords.ticketRoll, TestData.testNames[i]);  
         }
         adminRecords.DrawEither(TicketRecords.ticketRoll, true);
     }
    
    void purchaseTicket() {
        //Asks for and takes input, assigns the name to the ticket #.
        System.out.print("Enter a name:");
        String purchaseName = sc.nextLine();
        TicketRecords.ticketRoll = adminRecords.storePurchase(
                TicketRecords.ticketRoll, purchaseName);  
    }
    
}
