package br.com.concessionaria.beans;

import javax.persistence.*;

@Entity
@SequenceGenerator(allocationSize = 1, name = "cliente", sequenceName = "sq_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;

    private String CPF;

    private String email;

    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cliente() {
    }

    public Cliente(String nome, String CPF, String email, String senha) {
        this.nome = nome;
        this.CPF = CPF;
        this.email = email;
        this.senha = senha;
    }
}
