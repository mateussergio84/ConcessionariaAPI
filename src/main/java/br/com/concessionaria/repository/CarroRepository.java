package br.com.concessionaria.repository;

import br.com.concessionaria.beans.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {


    List<Carro> findByCategoria(String categoria);


    List<Carro> findByVendidoFalseAndCategoria(String categoria);


    List<Carro> findByVendidoFalse();
}
