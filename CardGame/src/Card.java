
public class Card {
	private int number;
	private String pic;
	
	 public Card(String pic, int number) {
	    this.pic = pic;
		this.number = number;
	}
	 
	 public void setSuit() {};
	 
	 public int getCardNum() {
		 return number;
	 }
	 
	 public String getPic() {
		 return pic;
	 }
}
