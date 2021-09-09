package com.eventoapp.controllers;

import com.eventoapp.repository.EventoRepository;
import com.eventoapp.repository.ConvidadoRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;

@Controller
public class EventoController {

    @Autowired // Toda vez que for preciso utilizar essa interface, será feito a injeção, criando uma nova instância automaticamente
    private EventoRepository er;
    
    @Autowired
    private ConvidadoRepository cr;

    //@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
    //          OU
    @GetMapping("/cadastrarEvento") //Quando o usuário requerir "/cadastrarEvento" o controller irá redirecionar para formEvento
    public String form(){
        return "evento/formEvento";
    }

    //@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
    //          OU
    @PostMapping("/cadastrarEvento") // Método para cadastrar um eventos
    public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){ // Se houver erros (EX: Rg em branco)
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");// Método que redireciona uma mensagem para o usuário
            return "redirect:/cadastrarEvento";
        }else{
            er.save(evento); // Salva convidado no repositorio CR
            attributes.addFlashAttribute("mensagem", "Dados salvos com sucesso!");
            return "redirect:/cadastrarEvento";
        }
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

    @GetMapping("/{id}")
    public ModelAndView detalhesEvento(@PathVariable("id") long id){
		Evento evento = er.findById(id);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);

        Iterable<Convidado> convidados = cr.findByEvento(evento); // Busca uma lista de convidados pelo evento
        mv.addObject("convidados", convidados); // Manda para a view

        return mv;
    }

    // Foi preciso deletar todos os convidados antes de deletar o evento
    @RequestMapping("/deletarEvento")
    public String deletarEvento(long id){
        Evento evento = er.findById(id); // Busca o evento
        Iterable<Convidado> convidados= cr.findByEvento(evento); // Busca os convidados do evento
        cr.deleteAll(convidados); // Deleta todos os convidados
        er.delete(evento); // Deleta o evento
        return "redirect:/eventos";
    }

    @PostMapping("/{id}")
    public String detalhesEventoPost(@PathVariable("id") long id, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){ // Se houver erros (EX: Rg vazio)
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");// Método que redireciona uma mensagem para o usuário
            return "redirect:/{id}";
        
        }else{
            Evento evento = er.findById(id);
            convidado.setEvento(evento); // Diz que este convidado esta relacionado a lista de convidados deste evento
		    cr.save(convidado); // Salva convidado no repositorio CR
            attributes.addFlashAttribute("mensagem", "Dados salvos com sucesso!");

            return "redirect:/{id}";
        }
    }

    @RequestMapping("/deletarConvidado")
	public String deletarConvidado(String rg){
        Convidado convidado = cr.findByRg(rg); // Busca o convidado
        cr.delete(convidado); // Deleta o convidado

        // RETORNAR A LISTA DE CONVIDADOS ATUALIZADA
        Evento evento = convidado.getEvento(); // Pega o evento que esse convidado pertence
        long idLong = evento.getId(); // Pega o id
        String id = "" + idLong; // Transforma o idLong em String
        return "redirect:/" + id;
    }
}
