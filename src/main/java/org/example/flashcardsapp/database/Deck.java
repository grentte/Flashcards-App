package org.example.flashcardsapp.database;

public class Deck {
    private int user_id;
    private String name;
    private String description;

    public Deck(String name, String description) {
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

    public int getUser_id() { return user_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }
}
