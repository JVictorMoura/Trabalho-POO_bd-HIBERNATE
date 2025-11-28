package model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

// @MappedSuperclass - Indica ao Hibernate que esta classe não é uma tabela, 
// mas seus atributos devem ser herdados pelas classes filhas persistíveis.
@MappedSuperclass 
public abstract class Usuario {
    
    // Encapsulamento: O ID é gerado internamente.
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    // Encapsulamento: Atributos protegidos para acesso pelas classes filhas.
    protected String nome;
    protected String cpf;
    protected String email;

    // Construtor
    public Usuario() {}
    
    public Usuario(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    // Abstração: Método que deve ser implementado nas classes filhas para exibir detalhes.
    public abstract String exibirDetalhes();

    // Getters e Setters (Encapsulamento)
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    // ... outros getters e setters
}