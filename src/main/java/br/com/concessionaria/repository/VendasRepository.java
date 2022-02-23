package br.com.concessionaria.repository;

import br.com.concessionaria.beans.Carro;
import br.com.concessionaria.beans.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendasRepository extends JpaRepository<Vendas, Long> {

    Vendas findByClienteId(Long id);


}
