package br.com.concessionaria.beans;

import javax.persistence.*;

@Entity
@SequenceGenerator(allocationSize = 1, name = "carro", sequenceName = "sq_carro")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public Carro(String categoria, int ano, String modelo, String marca, double preco, boolean vendido, byte[] imagem) {
        this.categoria = categoria;
        this.ano = ano;
        this.modelo = modelo;
        this.marca = marca;
        this.preco = preco;
        this.vendido = vendido;
        this.imagem = imagem;
    }

    public Carro(){}


}