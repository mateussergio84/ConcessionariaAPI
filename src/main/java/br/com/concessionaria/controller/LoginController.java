package br.com.concessionaria.controller;

import br.com.concessionaria.beans.Cliente;
import br.com.concessionaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    ClienteRepository cR;

    public static String email_logado;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @PostMapping("login")
    public String login(Cliente cliente, Model model ) {
        if(cR.findByEmailAndSenha(cliente.getEmail(), cliente.getSenha()) == null){
            cliente.setEmail("");
            return "/login";
        }
        else{
            email_logado = cliente.getEmail();
            return "redirect:/home";
        }
    }
}
