package ru.jurfed.lottery.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import ru.jurfed.lottery.domain.Config;
import ru.jurfed.lottery.domain.ConfigsDto;
import ru.jurfed.lottery.repository.ConfigRepository;
import ru.jurfed.lottery.test.Employee;
import ru.jurfed.lottery.test.HelloMessageGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

//@RestController
//https://www.baeldung.com/spring-bean-scopes
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
    @GetMapping("/configs")
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
    @RequestMapping(value = "/configs", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute ConfigsDto configCreationDto, Model model, HttpSession session) {

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
        return "addConfigs";
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
     * Postman -> http://localhost:8080/addParameters -> POST -> Body -> raw -> JSON ->
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
     * or Poatman -> ccc-> POST -> Body -> binary -> select file (configs.json)
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


    @RequestMapping(value = "/allparameters", method = RequestMethod.GET)
    public @ResponseBody List<Config> savePerson() {
        List<Config> all = repository.findAll();
        return all;
    }

    //test hello message with scope request
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public HelloMessageGenerator requestScopedBean() {
        return new HelloMessageGenerator();
    }

    @Resource(name = "requestScopedBean")
    HelloMessageGenerator requestScopedBean;

    @RequestMapping("/scopes/request")
    public String getRequestScopeMessage(final Model model) {
        model.addAttribute("previousMessage", requestScopedBean.getMessage());
        requestScopedBean.setMessage("Good morning!");
        model.addAttribute("currentMessage", requestScopedBean.getMessage());
        return "scopesExample";
    }

    //test model method attribute -добавляет в модель атрибут при каждом вызове любого метода в контроллере
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "Welcome to the Netherlands!");
    }

    //test model method argument attribute - создает аттрибут при первом обращении ??? и помещает его в модель
    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String submit(@ModelAttribute("employee2") Employee employee, Model model) {
        // Code that uses the employee object
        model.addAttribute("employee2", new Employee("ttttt"));

        return "employeeView";
    }

}
