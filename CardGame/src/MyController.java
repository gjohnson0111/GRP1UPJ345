import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MyController {
	
	Card [] deck = loadCards();

	Card card1 = null;
	Card card2 = null;
	Card card3 = null;
	Card card4 = null;
	int cardNum = 0;
	int cardNum2 = 0;
	int cardNum3 = 0;
	int cardNum4 = 0;
	long start = 0;
	long end = 0;
	boolean pressed = false;
	boolean foundSol = false;
	String operators = "+-/*";
	String thinking = "Thinking...";
	ArrayList <String> Solutions = new ArrayList<>();
	ArrayList <Integer> entered = new ArrayList<>();

	

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button butSolution;

    @FXML
    private Button butRefresh;

    @FXML
    private Button butVerify;

    @FXML
    private Label expLabel;

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    @FXML
    private ImageView imageView4;

    @FXML
    private TextField tfSolution;
    

    @FXML
    private TextField tfExpression;
    
    @FXML
    private Text mText;

   

    @FXML
    void findSolution(ActionEvent event) throws ScriptException {
    	tfSolution.setText("Thinking....");
       //modified solution found 
       //https://stackoverflow.com/questions/2392379/computing-target-number-from-numbers-in-a-set
       expression(card1.getCardNum(), card2.getCardNum(), card3.getCardNum(), card4.getCardNum());
    	 
        if(!foundSol) {
        	tfSolution.setText("No Solution!");
        }
    }

    public MyController() { //entered by professor?
    	//refreshCards(new ActionEvent());
    }

    @FXML
    void refreshCards(ActionEvent event) {
    	// start of function 
    	
    	foundSol = false;
        mText.setText("");
    	tfSolution.clear();
    	tfExpression.clear();
    	Solutions.clear();
    	butRefresh.setText("Refresh");
    	
    	//card1
    	Random rand = new Random(); // Makes a new random number using the Random class
        cardNum = rand.nextInt(51) + 0; // Stores random number to an int variable to use for the 'deck' array 
        card1 = deck[cardNum];
        //card1
        Image img1 = new Image(card1.getPic()); // Grabs the image directory of the card in the 'deck' array chosen by the random number
        imageView1.setImage(img1);
        
		
		//card2
        Random rand2 = new Random();
		cardNum2 = rand2.nextInt(51) + 0;
		card2 = deck[cardNum2];
		//card2		
		Image img2 = new Image(card2.getPic());
		imageView2.setImage(img2);
		
		
		//card3
		Random rand3 = new Random();
		cardNum3 = rand3.nextInt(51) + 0;	
		card3 = deck[cardNum3];
		//card3				
		Image img3 = new Image(card3.getPic());
		imageView3.setImage(img3);
		
		
		//card4
		Random rand4 = new Random();
		cardNum4 = rand4.nextInt(51) + 0;	
		card4 = deck[cardNum4];
		//card4
		Image img4 = new Image(card4.getPic());
		imageView4.setImage(img4);
		
		
		//end time
		

    }
    
    
    

    @FXML
    void verifyCards(ActionEvent event) throws ScriptException {
    	
    	boolean same = true; //create flag to check arrays
    	
    	
    	String exp = tfExpression.getText(); //retrieve text from text field
  
    	char ch;
    	
    	for(int i = 0; i < exp.length(); i++) {
    		ch = exp.charAt(i);
    		
    		if(Character.isDigit(ch)) {
    			entered.add(ch-'0');
    		}
    		
    	}
    	
    	
    	Collections.sort(entered);
    	 
    	 //store card numbers in an array called hand to compare with the numbers taken in from expression
    	 int [] hand = {card1.getCardNum(), card2.getCardNum(), card3.getCardNum(), card4.getCardNum()};
    	 Arrays.sort(hand); //sort array
    	 
    	 //check every index to see if they hold the same value (sorted), if not set flag to false
    	 for(int i = 0; i < 4; i++) {
    		 
    		 if(entered.get(i) != hand[i]) {
    			 same = false;
    			 break;
    		 }
    		
    	 }
    	 
    	// System.out.println(same);
    	 
    	 //if flag is false and the arrays are not the same tell user that they must use the numbers shown
    	 if(same == false) {
    		 tfExpression.setText("Numbers must match cards!");
    	 }
    	 else {
    		 
    		 //create and instantiate script engine object to evaluate text field expression
    		 ScriptEngineManager mgr = new ScriptEngineManager();
    		 ScriptEngine engine = mgr.getEngineByName("JavaScript");
    		 String result = (engine.eval(exp)).toString();
    		 tfExpression.clear();
    		 tfExpression.setText(result);
    		 
    		 //if expression equals 24 end the record time and tell user they won
    		 if(Integer.parseInt(result) == 24) {
    			 end = System.currentTimeMillis();
    			 long millis = (end - start);
    			 long minutes = (millis/1000) / 60;
    			 int seconds = (int)((millis/1000) % 60);
    			 System.out.println("Time elapsed: " + minutes + "m " + seconds + "s " );
    			 pressed = false;
    			 
    			 tfExpression.setText("24!");
    			 mText.setText("WINNER!");
    			 mText.setFont(Font.font("Courier", FontWeight.BOLD, 96));
    			 
    			
    			 mText.setFill(Color.RED);
    			 	
    		 }
    		 else {
    			 //if expression does not equal 24 tell user to try again
    			 tfExpression.setText("Try Again!");
    		 }
    	 }
    }

    public Card[] loadCards() {
    	
    	//load cards using polymorphism, parent class used to instantiate child class
		 
    	//Load Heart Cards
		Card aceOfHearts = new Heart("Cards\\Hearts\\1_of_hearts.png", 1, "Heart");
	    Card twoOfHearts = new Heart("Cards\\Hearts\\2_of_hearts.png", 2, "Heart");
	    Card threeOfHearts = new Heart("Cards\\Hearts\\3_of_hearts.png", 3, "Heart");
	    Card fourOfHearts = new Heart("Cards\\Hearts\\4_of_hearts.png", 4, "Heart");
	    Card fiveOfHearts = new Heart("Cards\\Hearts\\5_of_hearts.png", 5, "Heart");
	    Card sixOfHearts = new Heart("Cards\\Hearts\\6_of_hearts.png", 6, "Heart");
	    Card sevenOfHearts = new Heart("Cards\\Hearts\\7_of_hearts.png", 7, "Heart");
	    Card eightOfHearts = new Heart("Cards\\Hearts\\8_of_hearts.png", 8, "Heart");
	    Card nineOfHearts = new Heart("Cards\\Hearts\\9_of_hearts.png", 9, "Heart");
	    Card tenOfHearts = new Heart("Cards\\Hearts\\10_of_hearts.png", 10, "Heart");
	    Card jackOfHearts = new Heart("Cards\\Hearts\\11_of_hearts.png", 11, "Heart");
	    Card queenOfHearts = new Heart("Cards\\Hearts\\12_of_hearts.png", 12, "Heart");
	    Card kingOfHearts = new Heart("Cards\\Hearts\\13_of_hearts.png", 13, "Heart");
	    
	    //Load Club Cards
	    Card aceOfClubs = new Club("Cards\\Clubs\\1_of_clubs.png", 1, "Club");
	    Card twoOfClubs = new Club("Cards\\Clubs\\2_of_clubs.png", 2, "Club");
	    Card threeOfClubs = new Club("Cards\\Clubs\\3_of_clubs.png", 3, "Club");
	    Card fourOfClubs = new Club("Cards\\Clubs\\4_of_clubs.png", 4, "Club");
	    Card fiveOfClubs = new Club("Cards\\Clubs\\5_of_clubs.png", 5, "Club");
	    Card sixOfClubs = new Club("Cards\\Clubs\\6_of_clubs.png", 6, "Club");
	    Card sevenOfClubs = new Club("Cards\\Clubs\\7_of_clubs.png", 7, "Club");
	    Card eightOfClubs = new Club("Cards\\Clubs\\8_of_clubs.png", 8, "Club");
	    Card nineOfClubs = new Club("Cards\\Clubs\\9_of_clubs.png", 9, "Club");
	    Card tenOfClubs = new Club("Cards\\Clubs\\10_of_clubs.png", 10, "Club");
	    Card jackOfClubs = new Club("Cards\\Clubs\\11_of_clubs.png", 11, "Club");
	    Card queenOfClubs = new Club("Cards\\Clubs\\12_of_clubs.png", 12, "Club");
	    Card kingOfClubs = new Club("Cards\\Clubs\\13_of_clubs.png", 13, "Club");
	    
	    //Load Spade Cards
	    Card aceOfSpades = new Spade("Cards\\Spades\\1_of_spades.png", 1, "Spade");
	    Card twoOfSpades = new Spade("Cards\\Spades\\2_of_spades.png", 2, "Spade");
	    Card threeOfSpades = new Spade("Cards\\Spades\\3_of_spades.png", 3, "Spade");
	    Card fourOfSpades = new Spade("Cards\\Spades\\4_of_spades.png", 4, "Spade");
	    Card fiveOfSpades = new Spade("Cards\\Spades\\5_of_spades.png", 5, "Spade");
	    Card sixOfSpades = new Spade("Cards\\Spades\\6_of_spades.png", 6, "Spade");
	    Card sevenOfSpades = new Spade("Cards\\Spades\\7_of_spades.png", 7, "Spade");
	    Card eightOfSpades = new Spade("Cards\\Spades\\8_of_spades.png", 8, "Spade");
	    Card nineOfSpades = new Spade("Cards\\Spades\\9_of_spades.png", 9, "Spade");
	    Card tenOfSpades = new Spade("Cards\\Spades\\10_of_spades.png", 10, "Spade");
	    Card jackOfSpades = new Spade("Cards\\Spades\\11_of_spades.png", 11, "Spade");
	    Card queenOfSpades = new Spade("Cards\\Spades\\12_of_spades.png", 12, "Spade");	    
	    Card kingOfSpades = new Spade("Cards\\Spades\\13_of_spades.png", 13, "Spade");
	    

	    //Load Diamond Cards
	    Card aceOfDiamonds = new Diamond("Cards\\Diamonds\\1_of_diamonds.png", 1, "Diamond");
	    Card twoOfDiamonds = new Diamond("Cards\\Diamonds\\2_of_diamonds.png", 2, "Diamond");
	    Card threeOfDiamonds = new Diamond("Cards\\Diamonds\\3_of_diamonds.png", 3, "Diamond");
	    Card fourOfDiamonds = new Diamond("Cards\\Diamonds\\4_of_diamonds.png", 4, "Diamond");
	    Card fiveOfDiamonds = new Diamond("Cards\\Diamonds\\5_of_diamonds.png", 5, "Diamond");
	    Card sixOfDiamonds = new Diamond("Cards\\Diamonds\\6_of_diamonds.png", 6, "Diamond");
	    Card sevenOfDiamonds = new Diamond("Cards\\Diamonds\\7_of_diamonds.png", 7, "Diamond");
	    Card eightOfDiamonds = new Diamond("Cards\\Diamonds\\8_of_diamonds.png", 8, "Diamond");
	    Card nineOfDiamonds = new Diamond("Cards\\Diamonds\\9_of_diamonds.png", 9, "Diamond");
	    Card tenOfDiamonds = new Diamond("Cards\\Diamonds\\10_of_diamonds.png", 10, "Diamond");
	    Card jackOfDiamonds = new Diamond("Cards\\Diamonds\\11_of_diamonds.png", 11, "Diamond");
	    Card queenOfDiamonds = new Diamond("Cards\\Diamonds\\12_of_diamonds.png", 12, "Diamond");
	    Card kingOfDiamonds = new Diamond("Cards\\Diamonds\\13_of_diamonds.png", 13, "Diamond");
	
	    //Store Diamonds, Clubs, Hearts, Spades Cards
	    Card [] deck =
            {
              aceOfHearts, twoOfHearts, threeOfHearts, fourOfHearts, fiveOfHearts, sixOfHearts, sevenOfHearts, eightOfHearts, nineOfHearts, tenOfHearts, jackOfHearts, queenOfHearts, kingOfHearts,
                    aceOfClubs, twoOfClubs, threeOfClubs, fourOfClubs, fiveOfClubs, sixOfClubs, sevenOfClubs, eightOfClubs, nineOfClubs, tenOfClubs, jackOfClubs, queenOfClubs, kingOfClubs,
                    aceOfSpades, twoOfSpades, threeOfSpades, fourOfSpades, fiveOfSpades, sixOfSpades, sevenOfSpades, eightOfSpades, nineOfSpades, tenOfSpades, jackOfSpades, queenOfSpades, kingOfSpades,
                    aceOfDiamonds, twoOfDiamonds, threeOfDiamonds, fourOfDiamonds, fiveOfDiamonds, sixOfDiamonds, sevenOfDiamonds, eightOfDiamonds, nineOfDiamonds, tenOfDiamonds, jackOfDiamonds, queenOfDiamonds, kingOfDiamonds 
            };	
	    
	    return deck;
    }
   
 
 String translate(String postfix) {
     Stack<String> expr = new Stack<String>();
     Scanner sc = new Scanner(postfix);
     while (sc.hasNext()) {
         String t = sc.next();
         if (operators.indexOf(t) == -1) {
             expr.push(t);
         } else {
             expr.push("(" + expr.pop() + t + expr.pop() + ")");
         }
     }
     return expr.pop();
 }

  void brute(Integer[] numbers, int stackHeight, String eq) throws ScriptException {
     if (stackHeight >= 2) {
         for (char op : operators.toCharArray()) {
             brute(numbers, stackHeight - 1, eq + " " + op);
         }
     }
     boolean allUsedUp = true;
     for (int i = 0; i < numbers.length; i++) {
         if (numbers[i] != null) {
             allUsedUp = false;
             Integer n = numbers[i];
             numbers[i] = null;
             brute(numbers, stackHeight + 1, eq + " " + n);
             numbers[i] = n;
         }
     }
     if (allUsedUp && stackHeight == 1) {
    	 
    	 ScriptEngineManager mgr = new ScriptEngineManager();
		 ScriptEngine engine = mgr.getEngineByName("JavaScript");
		 String result = (engine.eval(translate(eq))).toString();
		 
		 if(Double.parseDouble(result) == 24) {
			 tfSolution.setText(translate(eq));
			 foundSol = true;
			 //System.out.println(eq + " === " + translate(eq));
		 }
     }
 }
 
  void expression(Integer... numbers) throws ScriptException {
	 tfSolution.setText(thinking);
	 
     brute(numbers, 0, "");
 }
     
}


	