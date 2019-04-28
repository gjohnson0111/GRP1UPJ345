import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

// Test for Card number being returned

public class CardNumTest {
    
    Card myCard = new Card("Cards\\Hearts\\10_of_Hearts.png", 10);
    
    @Test
    public void testGetCardNum(){
        System.out.print("Inside testGetCardNum");
        assertEquals(10, myCard.getCardNum(), "must equal 10");
        
    }
}