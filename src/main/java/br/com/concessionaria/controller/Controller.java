package br.com.concessionaria.controller;

import br.com.concessionaria.beans.Carro;
import br.com.concessionaria.beans.Cliente;
import br.com.concessionaria.beans.Vendas;
import br.com.concessionaria.repository.CarroRepository;
import br.com.concessionaria.repository.ClienteRepository;
import br.com.concessionaria.repository.VendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.SequenceGenerator;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
@SequenceGenerator(allocationSize = 1, name = "cliente", sequenceName = "sq_cliente")
public class Controller {

    @Autowired
    CarroRepository cR;

    @Autowired
    ClienteRepository clienteR;

    @Autowired
    VendasRepository vR;

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
        return "/home";
    }


    @GetMapping("/home")
    public ModelAndView listaC() {
        ModelAndView mv = new ModelAndView("home");
        List<Carro> carros = cR.findByVendidoFalse();
        mv.addObject("carros", carros);
        return mv;
    }

    @GetMapping("/estoque")
    public ModelAndView listaCarros() {
        ModelAndView mv = new ModelAndView("estoque");
        List<Carro> carros = cR.findByVendidoFalse();
        mv.addObject("carros", carros);
        return mv;
    }


    @GetMapping("home/img/{id_carro}")
    @ResponseBody
    public byte[] exibe(@PathVariable("id_carro") Long id) {
        Carro c = this.cR.getById(id);
        return c.getImagem();
    }

    @GetMapping("exluir/{id_carro}")
    public String deletar(@PathVariable("id_carro") Long id) {
        Optional<Carro> found = cR.findById(id);
        cR.delete(found.get());
        return "redirect:/estoque/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Carro carro = cR.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("carro", carro);
        return "/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCarro(@PathVariable("id") long id, Carro carro, Model model) {
        Optional<Carro> found = cR.findById(id);
        found.get().setImagem(exibe(id));
        found.get().setModelo(carro.getModelo());
        found.get().setMarca(carro.getMarca());
        found.get().setAno(carro.getAno());
        cR.save(found.get());
        return "redirect:/estoque";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @PostMapping("login")
    public String login(Cliente cliente, Model model ) {
        if(clienteR.findByEmail(cliente.getEmail()) == null){
            return "/cadCarro";
        }
        else{
            return "/home";
        }
    }


    @GetMapping("/categoria/{categoria}")
    public ModelAndView listaCarrosNovos(@PathVariable("categoria") String categoria) {
        ModelAndView mv = new ModelAndView("categoria");
        List<Carro> carros = cR.findByVendidoFalseAndCategoria(categoria);
        mv.addObject("carros", carros);
        return mv;
    }

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
        if (vR.findByClienteId(1L) == null) {
            Cliente cliente = clienteR.getById(1L);
            Carro carro = cR.getById(id);
            double valor = carro.getPreco() * 1.001;
            LocalDate data = LocalDate.now();
            Vendas vendas = new Vendas(data, carro, valor, cliente);
            vR.save(vendas);
            carro.setVendido(true);
            cR.save(carro);
            return "redirect:/cadCliente";
        } else {
            Cliente cliente = clienteR.getById(1L);
            Carro carro = cR.getById(id);
            double valor = carro.getPreco();
            LocalDate data = LocalDate.now();
            Vendas vendas = new Vendas(data, carro, valor, cliente);
            vR.save(vendas);
            carro.setVendido(true);
            cR.save(carro);
            return "redirect:/home";
        }
    }


    @GetMapping("/vendas")
    public ModelAndView Vendas() {
        ModelAndView mv = new ModelAndView("vendas");
        List<Vendas> venda = vR.findAll();
        mv.addObject("vendas", venda);
        return mv;
    }




}







