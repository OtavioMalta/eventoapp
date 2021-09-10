package com.eventoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    //Quando o usuário requerir "/" o controller irá redirecionar para index
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping(value="")
    public String hello(ModelMap Model) {
    return "index";
}
}
