package model;

import jakarta.persistence.*;

@MappedSuperclass
public class Usuario{

    private Integer idade;

    private String nome;

    private Integer cpf;

    private Boolean ativo;

    public Integer getidade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getCpf() { return cpf; }
    public void setCpf(Integer cpf) { this.cpf = cpf; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public void mostrarInfo(){

    }
}