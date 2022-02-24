package br.com.concessionaria.repository;

import br.com.concessionaria.beans.Cliente;
import br.com.concessionaria.beans.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendasRepository extends JpaRepository<Vendas, Long> {

    Vendas findByCliente(Cliente cliente);


}
