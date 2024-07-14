import org.example.flashcardsapp.database.User;
import org.junit.Before;
import org.junit.Test;

public class DeckControllerTest {

    private DeckController deckController;

    @Before
    public void setUp() {
        deckController = new DeckController();
        deckController.setCurrentUser(new User(1, "testLogin", "testName", "testPassword"));
    }

    @Test
    public void testAddNewDeck() {
        // Установите необходимые данные
        deckController.deckNameField = new javafx.scene.control.TextField();
        deckController.deckDescriptionField = new javafx.scene.control.TextField();
        deckController.addDeckButton = new javafx.scene.control.Button();

        // Имитируйте ввод данных
        deckController.deckNameField.setText("Test Deck");
        deckController.deckDescriptionField.setText("Test Description");

        // Вызовите метод, который должен быть протестирован
        deckController.addNewDeck();

        // Здесь вы можете добавить проверки для проверки состояния
        // Например, проверить, что колода была добавлена
    }
}
