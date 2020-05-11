package ru.jurfed.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.jurfed.lottery.domain.Config;
import ru.jurfed.lottery.repository.ConfigRepository;

import java.util.List;

public class ConfigSrvImpl implements ConfigSrv{

    @Autowired
    ConfigRepository configRepository;

    @Override
    public List<Config> getAllConfigs() {
        return configRepository.findAll();
    }
}
