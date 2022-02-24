package br.com.concessionaria.controller;

import br.com.concessionaria.beans.Vendas;
import br.com.concessionaria.repository.VendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class VendasController {

    @Autowired
    VendasRepository vR;

    @GetMapping("/vendas")
    public ModelAndView Vendas() {
        ModelAndView mv = new ModelAndView("vendas");
        List<Vendas> venda = vR.findAll();
        mv.addObject("vendas", venda);
        return mv;
    }


}
