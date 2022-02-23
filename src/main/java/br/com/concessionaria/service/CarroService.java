package br.com.concessionaria.service;

import br.com.concessionaria.beans.Carro;
import br.com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    CarroRepository cR;

}
