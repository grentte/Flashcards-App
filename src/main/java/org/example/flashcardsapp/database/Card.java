package org.example.flashcardsapp.database;

public class Card {
    private String frontside;
    private String backside;

    public Card(String frontside, String backside) {
        this.frontside = frontside;
        this.backside = backside;
    }

    public String getFrontside() {
        return frontside;
    }

    public void setFrontside(String frontside) {
        this.frontside = frontside;
    }

    public String getBackside() {
        return backside;
    }

    public void setBackside(String backside) {
        this.backside = backside;
    }
}
