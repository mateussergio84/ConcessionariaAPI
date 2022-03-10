package br.com.concessionaria.controller;

import br.com.concessionaria.beans.Carro;
import br.com.concessionaria.repository.CarroRepository;
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
    CarroRepository cR;

    @Autowired
    APIController api;

    @GetMapping("/estoque")
    public ModelAndView listaCarros() {
        ModelAndView mv = new ModelAndView("estoque");
        List<Carro> carros = cR.findByVendidoFalse();
        mv.addObject("carros", carros);
        return mv;
    }

    @GetMapping("exluir/{id_carro}")
    public String deletar(@PathVariable("id_carro") Long id) {
        Optional<Carro> found = cR.findById(id);
        api.deleteCarroById(found.get());
        return "redirect:/estoque/";
    }

}
