package br.com.concessionaria.controller;

import br.com.concessionaria.beans.Carro;
import br.com.concessionaria.beans.Cliente;
import br.com.concessionaria.beans.Vendas;
import br.com.concessionaria.repository.CarroRepository;
import br.com.concessionaria.repository.ClienteRepository;
import br.com.concessionaria.repository.VendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class APIController {

    @Autowired
    ClienteRepository cR;

    @Autowired
    CarroRepository carroR;

    @Autowired
    VendasRepository vR;

//    @GetMapping
//    public List<Cliente> listar() {
//        return cR.findAll();
//    }

    @RequestMapping(value = "/Cliente", method = RequestMethod.POST)
    @PostMapping
    public void cadCliente(Cliente cliente) {
        cR.save(cliente);
    }

    @RequestMapping(value = "/Carros", method = RequestMethod.POST)
    @PostMapping
    public void cadCarro(Carro carro) {
        carroR.save(carro);
    }

    @RequestMapping(value = "/Carros", method = RequestMethod.GET)
    @GetMapping
    public List<Carro> getListCarro() {
        return carroR.findByVendidoFalse();
    }

    @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    public Optional<Carro> getCarroById(Long id) {
        return carroR.findById(id);
    }

    @RequestMapping(value = "/carro", method = RequestMethod.POST)
    public void deleteCarroById(Carro carro) {
        carroR.delete(carro);
    }

    public List<Vendas> vendas() {
        return vR.findAll();
    }
}