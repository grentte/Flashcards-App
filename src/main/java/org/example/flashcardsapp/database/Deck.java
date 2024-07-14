package org.example.flashcardsapp.database;

public class Deck {
    private int deck_id;
    private String name;
    private String description;

    public Deck(String name, String description) {
        this.deck_id = 0;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDeck_id() { return getDeck_id(); }

    public void setDeck_id(int deck_id) { this.deck_id = deck_id; }
}
