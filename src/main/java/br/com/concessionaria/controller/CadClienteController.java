package br.com.concessionaria.controller;

import br.com.concessionaria.beans.Cliente;
import br.com.concessionaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CadClienteController {

    @Autowired
    ClienteRepository cR;

    @Autowired
    APIController apiCad;


    @RequestMapping(value = "/cadCliente", method = RequestMethod.GET)
    public String formCliente() {
        return "cadCliente";
    }

    @RequestMapping(value = "/cadCliente", method = RequestMethod.POST)
    public String formCliente(Cliente cliente) {
        apiCad.cadCliente(cliente);
        return "/home";
    }
    
}
