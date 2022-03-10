package br.com.concessionaria.controller;

import br.com.concessionaria.beans.Carro;
import br.com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CarroRepository cR;

    @Autowired
    APIController api;

    @GetMapping("/home")
    public ModelAndView listaC() {
        ModelAndView mv = new ModelAndView("home");
        //List<Carro> carros = cR.findByVendidoFalse();
        List<Carro> carros = api.getListCarro();
        mv.addObject("carros", carros);
        return mv;
    }

    @GetMapping("home/img/{id_carro}")
    @ResponseBody
    public byte[] exibe(@PathVariable("id_carro") Long id) {
        Carro c = this.cR.getById(id);
        return c.getImagem();
    }

}
