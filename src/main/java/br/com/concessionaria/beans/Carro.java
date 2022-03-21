package br.com.concessionaria.beans;

import lombok.Data;

import javax.persistence.*;

@Entity
@SequenceGenerator(allocationSize = 1, name = "carro", sequenceName = "sq_carro")
@Data
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String categoria;

    private int ano;
    private String modelo;
    private String marca;
    private double preco;
    private boolean vendido = false;

    @Lob
    private byte[] imagem;

}