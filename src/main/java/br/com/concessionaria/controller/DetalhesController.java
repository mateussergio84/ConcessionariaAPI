package br.com.concessionaria.controller;

import br.com.concessionaria.beans.Carro;
import br.com.concessionaria.beans.Cliente;
import br.com.concessionaria.beans.Vendas;
import br.com.concessionaria.repository.CarroRepository;
import br.com.concessionaria.repository.ClienteRepository;
import br.com.concessionaria.repository.VendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class DetalhesController {

    @Autowired
    CarroRepository cR;

    @Autowired
    ClienteRepository clienteR;

    @Autowired
    VendasRepository vR;

    @GetMapping("/detalhes/{id_carro}")
    public ModelAndView detalhes(@PathVariable("id_carro") Long id) {
        ModelAndView mv = new ModelAndView("detalhes");
        Carro carro = cR.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        mv.addObject("carros", carro);
        return mv;
    }


    @GetMapping("/comprar/{id_carro}")
    public String Comprar(@PathVariable("id_carro") Long id) {
        Cliente cliente = clienteR.findByEmail(LoginController.email_logado);
        if (cliente != null) {
            if (vR.findByCliente(cliente) == null) {
                Carro carro = cR.getById(id);
                double valor = carro.getPreco() -(carro.getPreco() * 0.001);
                LocalDate data = LocalDate.now();
                Vendas vendas = new Vendas(data, carro, valor, cliente);
                vR.save(vendas);
                carro.setVendido(true);
                cR.save(carro);
                return "redirect:/home";
            } else {
                Carro carro = cR.getById(id);
                double valor = carro.getPreco();
                LocalDate data = LocalDate.now();
                Vendas vendas = new Vendas(data, carro, valor, cliente);
                vR.save(vendas);
                carro.setVendido(true);
                cR.save(carro);
                return "redirect:/home";
            }
        } else {
            return "redirect:/login";
        }
    }
}
