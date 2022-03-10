package br.com.concessionaria.controller;

import br.com.concessionaria.beans.Carro;
import br.com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class UpdateController {

    @Autowired
    CarroRepository cR;

    @Autowired
    APIController api;


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Carro carro = cR.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("carro", carro);
        return "/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCarro(@PathVariable("id") long id, Carro carro, Model model) {
        Optional<Carro> found = api.getCarroById(id);
        found.get().setImagem(exibeImagem(id));
        found.get().setModelo(carro.getModelo());
        found.get().setMarca(carro.getMarca());
        found.get().setAno(carro.getAno());
        api.cadCarro(found.get());
        return "redirect:/estoque";
    }

    @ResponseBody
    public byte[] exibeImagem(@PathVariable("id_carro") Long id) {
        Carro c = this.cR.getById(id);
        return c.getImagem();
    }
}
