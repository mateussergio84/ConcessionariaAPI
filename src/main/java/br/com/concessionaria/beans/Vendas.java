package br.com.concessionaria.beans;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@SequenceGenerator(allocationSize = 1, name = "vendas", sequenceName = "sq_vendas")
public class Vendas {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "carro_id")
    private Carro carro;

    private double valor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public Carro getCarro() {
        return carro;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
