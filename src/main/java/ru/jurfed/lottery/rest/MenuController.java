package ru.jurfed.lottery.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.jurfed.lottery.domain.Config;
import ru.jurfed.lottery.domain.ConfigsDto;
import ru.jurfed.lottery.repository.ConfigRepository;

import java.util.List;

//@RestController
@Controller
public class MenuController {

    /**
     * show parameters list
     * @param model
     * @return
     */
    @GetMapping("/")
    public String listPage(Model model) {
        return "menu";
    }

}
