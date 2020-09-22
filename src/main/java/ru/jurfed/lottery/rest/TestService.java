package ru.jurfed.lottery.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import ru.jurfed.lottery.test.Employee;
import ru.jurfed.lottery.test.HelloMessageGenerator;

import javax.annotation.Resource;

@Controller
public class TestService {

    //test hello message with scope = request
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
