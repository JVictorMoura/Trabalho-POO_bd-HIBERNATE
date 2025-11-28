package model;

import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Cliente extends Usuario { // Herança

    private BigDecimal limiteCredito;

    public Cliente() {}

    public Cliente(String nome, String cpf, String email, BigDecimal limiteCredito) {
        super(nome, cpf, email);
        this.limiteCredito = limiteCredito;
    }

    // Polimorfismo: Implementação do método abstrato
    @Override
    public String exibirDetalhes() {
        return String.format("CLIENTE [ID: %d, Nome: %s, CPF: %s, Limite de Crédito: R$ %.2f]",
                             this.id, this.nome, this.cpf, this.limiteCredito);
    }

    // Getters e Setters específicos
    public BigDecimal getLimiteCredito() { return limiteCredito; }
    public void setLimiteCredito(BigDecimal limiteCredito) { this.limiteCredito = limiteCredito; }
}