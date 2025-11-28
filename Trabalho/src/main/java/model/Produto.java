package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Produto { // Entidade independente

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;
    private Integer estoque;

    public Produto() {}

    public Produto(String nome, BigDecimal preco, Integer estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String exibirDetalhes() {
        return String.format("PRODUTO [ID: %d, Nome: %s, Preço: R$ %.2f, Estoque: %d]",
                             this.id, this.nome, this.preco, this.estoque);
    }
    
    // Getters e Setters (Encapsulamento)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    // ... outros getters e setters
}