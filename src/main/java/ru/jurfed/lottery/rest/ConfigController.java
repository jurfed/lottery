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

    /**
     * show parameters list
     * @param model
     * @return
     */
    @GetMapping("/")
    public String listPage(Model model) {
        List<Config> parameters = repository.findAll();
        model.addAttribute("parametersList", parameters);
        return "config";
    }


    /**
     * form for edit all parameters
     * @param model
     * @return
     */
    @GetMapping("/edit")
    public String showCreateForm(Model model) {
        List<Config> parameters = repository.findAll();

        EditConfigDto configsForm = new EditConfigDto(parameters);
        model.addAttribute("configsForm", configsForm);
        return "editConfigsForm";
    }

    /**
     * show all parameters after edit
     * @param configCreationDto
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute EditConfigDto configCreationDto, Model model) {
        repository.saveAll(configCreationDto.getConfigs());

        List<Config> parameters = repository.findAll();
        model.addAttribute("parametersList", parameters);
        return "config";
    }

    @GetMapping("/addParameter")
    public String addParameter(Model model) {
        Config config = new Config();

        model.addAttribute("config", config);
        return "addParameter";
    }

    @PostMapping("/saveParameter")
    public String saveParameter(@ModelAttribute Config config, Model model) {
        if(!config.getName().isEmpty()){
            repository.save(config);
        }
        List<Config> parameters = repository.findAll();
        model.addAttribute("parametersList", parameters);
        return "config";
    }

}
