package model;

import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Funcionario extends Usuario { // Herança
    
    private BigDecimal salario;

    public Funcionario() {}

    public Funcionario(String nome, String cpf, String email, BigDecimal salario) {
        super(nome, cpf, email);
        this.salario = salario;
    }

    // Polimorfismo: Implementação do método abstrato
    @Override
    public String exibirDetalhes() {
        return String.format("FUNCIONÁRIO [ID: %d, Nome: %s, CPF: %s, Salário: R$ %.2f]",
                             this.id, this.nome, this.cpf, this.salario);
    }
    
    // Getters e Setters específicos
    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }
}