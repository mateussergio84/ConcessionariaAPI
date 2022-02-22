package br.com.concessionaria.repository;

import br.com.concessionaria.beans.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
