package com.eventoapp.controllers;

import com.eventoapp.repository.EventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.eventoapp.models.Evento;

@Controller
public class EventoController {

    @Autowired // Toda vez que for preciso utilizar essa interface, será feito a injeção, criando uma nova instância automaticamente
    private EventoRepository er;
    
    //@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
    //          OU
    @GetMapping("/cadastrarEvento") //Quando o usuário requerir "/cadastrarEvento" o controller irá redirecionar para formEvento
    public String form(){
        return "evento/formEvento";
    }

    //@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
    //          OU
    @PostMapping("/cadastrarEvento") // Método para cadastrar um eventos
    public String form(Evento evento){

        // Salva o evento 
        er.save(evento); 

        // Retorna a página zerada para um novo cadastro
        return "redirect:/cadastrarEvento";
    }

    @RequestMapping("/eventos") // Método para retornar a lista de eventos
    public ModelAndView listaEventos(){

        // Especifíca a página que será mostrado
        ModelAndView mv = new ModelAndView("index");

        // Monta/Pega a lista
        Iterable<Evento> eventosList = er.findAll(); // Tipo Iterable pois é uma lista de eventos (<Evento>); findAll -> método para buscar todos os elementos
        
        // Manda a lista para a View
        mv.addObject("eventos", eventosList); // addObject(nome que esta na View ${eventos}, lista de eventos)

        //Retorna a 'vizualização' da lista
        return mv;
    } 

    @RequestMapping("/{id}")
    public ModelAndView detalhesEvento(@PathVariable("id") long id){
		Evento evento = er.findById(id);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
        return mv;
    }
}
