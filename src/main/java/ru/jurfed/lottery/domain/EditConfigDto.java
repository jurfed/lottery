package ru.jurfed.lottery.domain;

import java.util.ArrayList;
import java.util.List;

public class EditConfigDto {
    private List<Config> configs;

    public EditConfigDto(List<Config> configs) {
        this.configs = configs;
    }

    public EditConfigDto() {
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
