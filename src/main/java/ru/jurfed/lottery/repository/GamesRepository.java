package ru.jurfed.lottery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jurfed.lottery.domain.Config;
import ru.jurfed.lottery.domain.Game;

import java.util.List;

public interface GamesRepository extends JpaRepository<Game, Integer> {


    @Override
    <S extends Game> S saveAndFlush(S s);

}
