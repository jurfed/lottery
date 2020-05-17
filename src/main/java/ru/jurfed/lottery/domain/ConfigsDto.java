package ru.jurfed.lottery.domain;

import java.util.ArrayList;
import java.util.List;

public class ConfigsDto {
    private List<Config> configs;

    public ConfigsDto(List<Config> configs) {
        this.configs = configs;
    }

    public ConfigsDto() {
        configs = new ArrayList<>();
    }

    public void addConfig(Config config) {
        this.configs.add(config);
    }


    public List<Config> getConfigs() {
        return configs;
    }

    public void setConfigs(List<Config> configs) {
        this.configs = configs;
    }
}
