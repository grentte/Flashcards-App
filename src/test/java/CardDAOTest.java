import org.example.flashcardsapp.database.Card;
import org.example.flashcardsapp.database.CardsTable;
import org.example.flashcardsapp.database.DatabaseHandler;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class CardDAOTest {

    private DatabaseHandler.CardDAO cardDAO;
    private Connection connection;

    @Before
    public void setUp() {
        cardDAO = new DatabaseHandler.CardDAO();
        try {
            connection = DatabaseHandler.getDbConnection();
            connection.setAutoCommit(false); // Start transaction
            clearCardsTable(); // Clear the table before each test
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            if (connection != null) {
                connection.rollback(); // Rollback transaction after each test
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddCard() {
        Card card = new Card("Front Side Test", "Back Side Test");
        int deckId = 1; // Use a valid deck ID or set up a test deck in the database

        try {
            cardDAO.addCard(card, deckId);

            // Verify if the card was added
            String query = "SELECT COUNT(*) FROM " + CardsTable.CARDS_TABLE + " WHERE " + CardsTable.CARDS_FRONTSIDE + "=?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, card.getFrontside());
                var resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    assertTrue("Card was not added to the database", count > 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearCardsTable() throws SQLException {
        String deleteSQL = "DELETE FROM " + CardsTable.CARDS_TABLE;
        try (PreparedStatement stmt = connection.prepareStatement(deleteSQL)) {
            stmt.executeUpdate();
        }
    }
}
