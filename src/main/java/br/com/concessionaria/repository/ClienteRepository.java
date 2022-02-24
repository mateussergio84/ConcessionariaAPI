package br.com.concessionaria.repository;

import br.com.concessionaria.beans.Carro;
import br.com.concessionaria.beans.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    Cliente findByEmail(String email);


    Cliente findByEmailAndSenha(String email, String senha);



}
