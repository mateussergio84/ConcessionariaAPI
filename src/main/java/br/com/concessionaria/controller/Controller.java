package br.com.concessionaria.controller;

import br.com.concessionaria.beans.Carro;
import br.com.concessionaria.beans.Cliente;
import br.com.concessionaria.repository.CarroRepository;
import br.com.concessionaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.SequenceGenerator;
import java.io.IOException;
import java.util.List;

@org.springframework.stereotype.Controller
@SequenceGenerator(allocationSize = 1, name = "cliente", sequenceName = "sq_cliente")
public class Controller {

    @Autowired
    CarroRepository cR;

    @Autowired
    ClienteRepository clienteR;

    @RequestMapping(value = "/cadCarro", method = RequestMethod.GET)
    public String form() {
        return "cadCarro";
    }

//    @RequestMapping(value = "/home", method = RequestMethod.GET)
//    public String home() {
//        return "home";
//    }

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


    @RequestMapping(value = "/cadCliente", method = RequestMethod.GET)
    public String formCliente() {
        return "cadCliente";
    }

    @RequestMapping(value = "/cadCliente", method = RequestMethod.POST)
    public String formCliente(Cliente cliente) {
        clienteR.save(cliente);
        return "/cadCliente";
    }


    @GetMapping("/home")
    public ModelAndView listaC(){
        ModelAndView mv = new ModelAndView("home");
        List<Carro> carros = cR.findAll();
        mv.addObject("carros", carros);
        return mv;
    }

    @GetMapping("home/img/{id_carro}")
    @ResponseBody
    public byte[] exibe(@PathVariable("id_carro") Long id){
        Carro c = this.cR.getById(id);
        return c.getImagem();
    }
}
