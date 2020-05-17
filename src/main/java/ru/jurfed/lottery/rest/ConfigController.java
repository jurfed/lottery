package ru.jurfed.lottery.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.jurfed.lottery.domain.Config;
import ru.jurfed.lottery.domain.ConfigsDto;
import ru.jurfed.lottery.domain.Game;
import ru.jurfed.lottery.domain.GamesDto;
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

        ConfigsDto configsForm = new ConfigsDto(parameters);
        model.addAttribute("configsForm", configsForm);
        return "editConfigsForm";
    }

    /**
     * save all parameters after edit
     * @param configCreationDto
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute ConfigsDto configCreationDto, Model model) {

        repository.deleteAll();
        repository.saveAll(configCreationDto.getConfigs());

        List<Config> parameters = repository.findAll();
        model.addAttribute("parametersList", parameters);
        return "config";
    }

    /**
     * add new parameter
     * @param model
     * @return
     */
    @GetMapping("/addParameter")
    public String addParameter(Model model) {
        Config config = new Config();

        model.addAttribute("config", config);
        return "addParameter";
    }

    /**
     * save new parameter
     * @param config
     * @param model
     * @return
     */
    @PostMapping("/saveParameter")
    public String saveParameter(@ModelAttribute Config config, Model model) {
        if(!config.getName().isEmpty()){
            repository.save(config);
        }
        List<Config> parameters = repository.findAll();
        model.addAttribute("parametersList", parameters);
        return "config";
    }

    /**
     * delete parameter
     * @param parameterName
     * @param model
     * @return
     */
    @GetMapping("/delete")
    public String delete(@RequestParam("id") String parameterName, Model model) {
        repository.deleteById(parameterName);

        List<Config> parameters = repository.findAll();
        ConfigsDto configsForm = new ConfigsDto(parameters);
        model.addAttribute("configsForm", configsForm);
        return "editConfigsForm";
    }

    /**
     * save parameters from json
     * 1) receive json in post - request from postman (@RequestBody) and it to the repository
     * 2) return list of person to the postman (@ResponseBody)
     * Postman -> http://localhost:8080/create -> POST -> Body -> raw -> JSON ->
     * {
     *   "configs": [
     *     {
     *       "name": "1",
     *       "parameterValue": "game 1"
     *     },
     *     {
     *       "name": "2",
     *       "parameterValue": "game 2"
     *     }
     *   ]
     * }
     * or Poatman -> http://localhost:8080/create -> POST -> Body -> select file ->
     * @param configsDto
     */
    @RequestMapping(value = "/addParameters", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ConfigsDto saveConfigs(@RequestBody ConfigsDto configsDto) {

        List<Config> configs = configsDto.getConfigs();

        configs.forEach(repository::save);
        List<Config> savedConfigs = repository.findAll();

        ConfigsDto savedConfigsDto = new ConfigsDto(savedConfigs);
        return savedConfigsDto;
    }

}
