import org.example.flashcardsapp.controllers.CardController;
import org.junit.Before;
import org.junit.Test;

public class CardControllerTest {

    private CardController cardController;

    @Before
    public void setUp() {
        cardController = new CardController();
        cardController.setCurrentDeckId(1); // Установите ID колоды
    }

    @Test
    public void testAddNewCard() {
        // Установите необходимые данные
        cardController.frontSideField = new javafx.scene.control.TextField();
        cardController.backSideField = new javafx.scene.control.TextField();
        cardController.addCardButton = new javafx.scene.control.Button();

        // Имитируйте ввод данных
        cardController.frontSideField.setText("Front Side Test");
        cardController.backSideField.setText("Back Side Test");

        // Вызовите метод, который должен быть протестирован
        cardController.addNewCard();

        // Здесь вы можете добавить проверки для проверки состояния
        // Например, проверить, что карта была добавлена
    }
}
