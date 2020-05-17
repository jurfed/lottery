package ru.jurfed.lottery.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GAMES")
public class Game {

    public Game() {
    }

    public Game(int gameId, String gameName) {
        this.gameId = gameId;
        this.gameName = gameName;
    }



    @Id
    @Column(name = "game_id")
    private Integer gameId;

    @Column(name = "game_name")
    private String gameName;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
