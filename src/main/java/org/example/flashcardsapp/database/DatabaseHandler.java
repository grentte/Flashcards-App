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
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(User user) {
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

    public boolean isUserExists(String login) {
        String query = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_UNAME + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(query);
            prSt.setString(1, login);
            ResultSet resSet = prSt.executeQuery();
            return resSet.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
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
                    Deck deck = new Deck(name, description); // Ensure Deck has a constructor with id
                    decks.add(deck);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return decks;
        }

        public Deck getDeckByName(String deckName) {
            Deck deck = null;
            String sql = "SELECT * FROM " + DeckTable.DECKS_TABLE + " WHERE " + DeckTable.DECKS_NAME + " = ?";
            try (Connection conn = DatabaseHandler.getDbConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, deckName);
                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("deck_id"); // Убедитесь, что поле называется "deck_id"
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");

                    deck = new Deck(name, description);
                    deck.setDeck_id(id); // Устанавливаем deck_id после создания объекта
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return deck;
        }


    }

    public static class CardDAO {
        public void addCard(Card card, int deckId) {
            String insertCardSQL = "INSERT INTO cards (frontSide, backSide, deck_id) VALUES (?, ?, ?)";

            try (Connection connection = DatabaseHandler.getDbConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(insertCardSQL)) {

                preparedStatement.setString(1, card.getFrontSide());
                preparedStatement.setString(2, card.getBackSide());
                preparedStatement.setInt(3, deckId);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Card added successfully.");
                } else {
                    System.out.println("Failed to add card. No rows affected.");
                }

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public List<Card> getCardsByDeckId(int deckId) {
            List<Card> cards = new ArrayList<>();
            String sql = "SELECT * FROM " + CardsTable.CARDS_TABLE + " WHERE " + CardsTable.CARDS_DID + " = ?";
            try (Connection conn = DatabaseHandler.getDbConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, deckId);
                ResultSet resultSet = pstmt.executeQuery();
                while (resultSet.next()) {
                    String frontSide = resultSet.getString("frontSide");
                    String backSide = resultSet.getString("backSide");
                    Card card = new Card(frontSide, backSide);
                    cards.add(card);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return cards;
        }
    }

    public static void updateLogin(int userId, String newLogin) {
        String update = "UPDATE " + Const.USER_TABLE + " SET " + Const.USERS_UNAME + "=? WHERE " + Const.USERS_ID + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1, newLogin);
            prSt.setInt(2, userId);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserName(int userId, String newName) {
        String update = "UPDATE " + Const.USER_TABLE + " SET " + Const.USERS_NAME + "=? WHERE " + Const.USERS_ID + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1, newName);
            prSt.setInt(2, userId);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserPassword(int userId, String newPassword) {
        String update = "UPDATE " + Const.USER_TABLE + " SET " + Const.USERS_PASSWORD + "=? WHERE " + Const.USERS_ID + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1, newPassword);
            prSt.setInt(2, userId);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int userId) {
        String query = "DELETE FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_ID + " = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(query);
            prSt.setInt(1, userId);
            int rowsAffected = prSt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Пользователь успешно удален");
            } else {
                System.out.println("Ошибка при удалении пользователя");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUserDecks(int userId) {
        String query = "DELETE FROM " + DeckTable.DECKS_TABLE + " WHERE " + DeckTable.DECKS_UID + " = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(query);
            prSt.setInt(1, userId);
            int rowsAffected = prSt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Все колоды пользователя удалены");
            } else {
                System.out.println("Не найдено колод для удаления");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
