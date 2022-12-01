package com.example.tennis4us.Class;

import java.io.Serializable;

public class FourPlayers  implements Serializable {
    private String Player1Name, Player2Name, Player3Name, Player4Name, Game;
    public FourPlayers() { }
    public FourPlayers(String player1Name, String player2Name, String player3Name, String player4Name, String game) {
        Player1Name = player1Name;
        Player2Name = player2Name;
        Player3Name = player3Name;
        Player4Name = player4Name;
        Game = game;
    }
    public String getPlayer1Name() {
        return Player1Name;
    }
    public void setPlayer1Name(String player1Name) {
        Player1Name = player1Name;
    }
    public String getTeam1Names() {
        return Player1Name + ", " + Player2Name;
    }
    public void setTeam1Names(String player1Name, String player2Name) {
        Player1Name = player1Name;
        Player2Name = player2Name;
    }
    public String getTeam2Names() {
        return Player3Name + ", " + Player4Name;
    }
    public void setTeam2Names(String player3Name, String player4Name) {
        Player3Name = player3Name;
        Player4Name = player4Name;
    }
    public String getGame() {
        return Game;
    }
    public void setGame(String game) {
        Game = game;
    }
}
