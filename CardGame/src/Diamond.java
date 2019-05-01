
public class Diamond extends Card{
	
	public String suit;
	
	public Diamond(String pic, int number, String suit) { // constructor for club class that inherits from card class
		super(pic, number); //passes values as parameters to parent class
		this.suit = suit;
		// TODO Auto-generated constructor stub
	}
	//set suit 
	public void setSuit(String suit) {
		this.suit = suit;
	}
	//get suit
	public String getSuit() {
		return suit; 
	}
	
}
