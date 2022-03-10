package br.com.concessionaria.controller;

import br.com.concessionaria.beans.Carro;
import br.com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class CadCarroController {

    @Autowired
    CarroRepository cR;

    @RequestMapping(value = "/cadCarro", method = RequestMethod.GET)
    public String form() {
        return "cadCarro";
    }


    @PostMapping("cadCarro")
    public String salvar(Carro carro, @RequestParam("fileProduto") MultipartFile file) {
        try {
            carro.setImagem(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        cR.save(carro);
        return "/cadCarro";
    }


}
