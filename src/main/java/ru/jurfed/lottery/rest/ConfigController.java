package ru.jurfed.lottery.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.jurfed.lottery.domain.Config;
import ru.jurfed.lottery.domain.EditConfigDto;
import ru.jurfed.lottery.repository.ConfigRepository;

import java.util.List;

//@RestController
@Controller
public class ConfigController {

    private final ConfigRepository repository;

    @Autowired
    public ConfigController(ConfigRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/")
    public String listPage(Model model) {
        List<Config> parameters = repository.findAll();
        model.addAttribute("parametersList", parameters);
        return "config";
    }


    @GetMapping("/edit")
    public String showCreateForm(Model model) {
        List<Config> parameters = repository.findAll();

        EditConfigDto configsForm = new EditConfigDto(parameters);
        model.addAttribute("form", configsForm);
        return "createConfigsForm";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute EditConfigDto configCreationDto, Model model) {
        repository.saveAll(configCreationDto.getConfigs());

        List<Config> parameters = repository.findAll();
        model.addAttribute("parametersList", parameters);
        return "config";
    }

}
