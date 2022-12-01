package com.example.tennis4us.Class;

public class Player {
    private String Name, Game, Score;
    public int ID = -1;
    public Player() { }
    public Player(String name, String game, String score) {
        this.Name = name;
        this.Game = game;
        this.Score = score;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getGame() {
        return Game;
    }
    public void setGame(String game) {
        Game = game;
    }
    public String getScore() {
        return Score;
    }
    public void setScore(String score) {
        Score = score;
    }
}
