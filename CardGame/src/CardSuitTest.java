import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

//Test for Card Suit being returned
//This is a child class

public class CardSuitTest {
    
    Heart heartCard = new Heart("Cards\\Hearts\\10_of_Hearts.png", 10, "Heart");
    
    @Test
    public void testGetSuit(){
        System.out.print("Inside testGetCardNum");
        assertEquals("Heart", heartCard.getSuit(), "must equal Heart");
        
    }
}