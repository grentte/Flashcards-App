package org.example.flashcardsapp.database;

public class Card {
    private String frontSide;
    private String backSide;

    public Card(String frontSide, String backSide) {
        this.frontSide = frontSide;
        this.backSide = backSide;
    }

    public String getFrontSide() {
        return frontSide;
    }

    public void setFrontSide(String frontSide) {
        this.frontSide = frontSide;
    }

    public String getBackSide() {
        return backSide;
    }

    public void setBackSide(String backSide) {
        this.backSide = backSide;
    }

    @Override
    public String toString() {
        return "Вопрос: " + frontSide + ", Ответ: " + backSide;
    }
}
