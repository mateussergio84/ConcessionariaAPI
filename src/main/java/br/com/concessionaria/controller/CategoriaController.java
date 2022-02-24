package br.com.concessionaria.controller;

import br.com.concessionaria.beans.Carro;
import br.com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoriaController {

    @Autowired
    CarroRepository cR;

    @GetMapping("/categoria/{categoria}")
    public ModelAndView listaCarrosNovos(@PathVariable("categoria") String categoria) {
        ModelAndView mv = new ModelAndView("categoria");
        List<Carro> carros = cR.findByVendidoFalseAndCategoria(categoria);
        mv.addObject("carros", carros);
        return mv;
    }
}
