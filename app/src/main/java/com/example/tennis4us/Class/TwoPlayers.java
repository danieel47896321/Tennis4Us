package com.example.tennis4us.Class;

import java.io.Serializable;

public class TwoPlayers  implements Serializable {
    private String Player1Name, Player2Name, Game;
    public TwoPlayers() { }
    public TwoPlayers(String player1Name, String player2Name, String game) {
        this.Player1Name = player1Name;
        this.Player2Name = player2Name;
        this.Game = game;
    }
    public String getPlayer1Name() {
        return Player1Name;
    }
    public void setPlayer1Name(String player1Name) {
        Player1Name = player1Name;
    }
    public String getPlayer2Name() {
        return Player2Name;
    }
    public void setPlayer2Name(String player2Name) {
        Player2Name = player2Name;
    }
    public String getGame() {
        return Game;
    }
    public void setGame(String game) {
        Game = game;
    }
}
