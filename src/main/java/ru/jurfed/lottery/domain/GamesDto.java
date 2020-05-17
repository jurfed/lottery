package ru.jurfed.lottery.domain;

import java.util.ArrayList;
import java.util.List;

public class GamesDto {

    public GamesDto() {
        games = new ArrayList<>();
    }

    public GamesDto(List<Game> games) {
        this.games = games;
    }

    private List<Game> games;

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void addGame(Game game){
        this.games.add(game);
    }
}
