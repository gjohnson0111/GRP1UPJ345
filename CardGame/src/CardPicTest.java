import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

// Test for Card picture string being returned
public class CardPicTest {
    
    Card myCard = new Card("Cards\\Hearts\\10_of_Hearts.png", 10);
    
    @Test
    public void testGetPic(){
        System.out.print("Inside testGetPic");
        assertEquals("Cards\\Hearts\\10_of_Hearts.png", myCard.getPic(), "must equal Cards\\Heats\\10_of_Heats.png");
        
    }
}