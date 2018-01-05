//This is the Ticket object class, along with a ticket's various properties.
package lotterysim;

/**
 *
 * @author Cory
 */
public class Ticket {
    private String name;
    //Ticket # in the set/sold position.
    private int place = 0;
    //0 means it's not a winner, it'll get assigned 1-3 respectively if it is.
    private int winner = 0;
    //If it is a winner it'll get flagged true so it doesn't get paid out twice.
    private boolean paid = false;
    //If this is a winner and money is paid this is the value of the prize.
    private double prize = 0;

    public Ticket() {
        this.name = null;
    }
    
    //Standard object setters and getters instead of direct variable access.
    void setName(String entry) {
        this.name = entry;
    }
    
    String getName() {
        return this.name;
    }
    
    void setPlace(int entry) {
        this.place = entry;
    }
    
    int getPlace() {
        return this.place;
    }
    
    void setWinner(int entry) {
        this.winner = entry;
    }
    
    int getWinner() {
        return this.winner;
    }
    
    void setPaid(boolean entry) {
        this.paid = entry;
    }
    
    boolean getPaid() {
        return this.paid;
    }
    
    void setPrize(double entry) {
        this.prize = entry;
    }
    
    double getPrize() {
        return this.prize;
    }
    
}
