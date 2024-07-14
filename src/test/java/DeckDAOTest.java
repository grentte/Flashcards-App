import org.example.flashcardsapp.database.DatabaseHandler;
import org.example.flashcardsapp.database.Deck;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class DeckDAOTest {

    private DatabaseHandler.DeckDAO deckDAO;

    @Before
    public void setUp() {
        deckDAO = new DatabaseHandler.DeckDAO();
    }

    @Test
    public void testAddDeck() {
        Deck deck = new Deck("Test Deck", "Test Description");
        int userId = 1; // Замените на реальный userId для теста

        try {
            deckDAO.addDeck(deck, userId);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Deck addition failed: " + e.getMessage());
        }
    }
}
