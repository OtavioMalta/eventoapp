package com.eventoapp.eventoapp;

//import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@ComponetScan diz ao Spring em quais pacotes você tem classes anotadas que devem ser gerenciadas pelo Spring.
public class EventoController {

    //Quando o usuário requerir "/cadastrarEvento" o controller irá redirecionar para formEvento
    @RequestMapping("/cadastrarEvento")
    public String form(){
        return "formEvento";
    }
}
