package br.com.concessionaria.controller;

import br.com.concessionaria.beans.Carro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class EstoqueController {

    @Autowired
    APIController api;

    @GetMapping("/estoque")
    public ModelAndView listaCarros() {
        ModelAndView mv = new ModelAndView("estoque");
        List<Carro> carros = api.getListCarro();
        mv.addObject("carros", carros);
        return mv;
    }

    @GetMapping("excluir/{id_carro}")
    public String deletar(@PathVariable("id_carro") Long id) {
        Optional<Carro> found = api.getCarroById(id);
        api.deleteCarroById(found.get());
        return "redirect:/estoque/";
    }

}
