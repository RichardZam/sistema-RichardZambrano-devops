package com.richardzambrano.configserver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("home");
        // Puedes agregar datos dinámicos si lo necesitas
        mav.addObject("serverPort", "8888");
        return mav;
    }
}