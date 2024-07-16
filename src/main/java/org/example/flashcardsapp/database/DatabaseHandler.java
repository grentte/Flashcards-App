package org.example.flashcardsapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends Configs {
    private static Connection dbConnection;

    public static Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        // Используем правильное имя класса драйвера
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(User user) {
        // Исправляем SQL запрос: добавляем пробелы и правильный порядок
        String insert = "INSERT INTO " + Const.USER_TABLE + " (" + Const.USERS_UNAME + ", " + Const.USERS_NAME + ", " + Const.USERS_PASSWORD + ") VALUES (?, ?, ?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getName());
            prSt.setString(3, user.getPassword());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_UNAME + "=? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public static class DeckDAO {
        public void addDeck(Deck deck, int userId) {
            String sql = "INSERT INTO " + DeckTable.DECKS_TABLE + " (" + DeckTable.DECKS_UID + ", " + DeckTable.DECKS_NAME + ", " + DeckTable.DECKS_DESCRIPTION + ") VALUES (?, ?, ?)";

            try (Connection conn = DatabaseHandler.getDbConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, userId);
                pstmt.setString(2, deck.getName());
                pstmt.setString(3, deck.getDescription());

                pstmt.executeUpdate();

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public List<Deck> getDecksByUserId(int userId) {
            List<Deck> decks = new ArrayList<>();
            String sql = "SELECT * FROM " + DeckTable.DECKS_TABLE + " WHERE " + DeckTable.DECKS_UID + " = ?";

            try (Connection conn = DatabaseHandler.getDbConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                ResultSet resultSet = pstmt.executeQuery();

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    Deck deck = new Deck(name, description);
                    decks.add(deck);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            return decks;
        }
    }

    public static class CardDAO {
        public void addCard(Card card, int deckId) {
            String sql = "INSERT INTO " + CardsTable.CARDS_TABLE + " (" + CardsTable.CARDS_DID + ", " + CardsTable.CARDS_FRONTSIDE + ", " + CardsTable.CARDS_BACKSIDE + ") VALUES (?, ?, ?)";
            try (Connection conn = DatabaseHandler.getDbConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, deckId);
                pstmt.setString(2, card.getFrontSide());
                pstmt.setString(3, card.getBackSide());
                pstmt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
