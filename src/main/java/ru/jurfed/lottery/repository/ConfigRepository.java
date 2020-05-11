package ru.jurfed.lottery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jurfed.lottery.domain.Config;

import java.util.List;

public interface ConfigRepository extends JpaRepository<Config, String> {

    public List<Config> findAll();

    @Override
    <S extends Config> S saveAndFlush(S s);

}
