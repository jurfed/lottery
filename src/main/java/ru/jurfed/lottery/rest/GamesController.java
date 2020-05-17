package ru.jurfed.lottery.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.jurfed.lottery.domain.Game;
import ru.jurfed.lottery.domain.GamesDto;
import ru.jurfed.lottery.repository.GamesRepository;

import java.util.List;

@Controller
public class GamesController {


    private final GamesRepository repository;

    @Autowired
    public GamesController(GamesRepository repository) {
        this.repository = repository;
    }

    /**
     * 1) receive json in post - request from postman (@RequestBody) and it to the repository
     * 2) return list of person to the postman (@ResponseBody)
     * Postman -> http://localhost:8080/create -> POST -> Body -> raw -> JSON ->
     * {
     * "games": [
     * {
     * "gameId": "1",
     * "gameName": "game 1"
     * },
     * {
     * "gameId": "2",
     * "gameName": "game 2"
     * }
     * ]
     * }
     * or Poatman -> http://localhost:8080/create -> POST -> Body -> select file ->
     *
     * @param newGames
     */
//    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = "application/xml")
    @RequestMapping(value = "/addGames", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    GamesDto saveGames(@RequestBody GamesDto newGames) {

        List<Game> games = newGames.getGames();

        games.forEach(repository::save);
        List<Game> all = repository.findAll();

        GamesDto gamesDto1 = new GamesDto(all);
        return gamesDto1;
    }

}
