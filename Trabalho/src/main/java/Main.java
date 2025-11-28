package main;

import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.ProdutoDAO;
import model.Cliente;
import model.Funcionario;
import model.Produto;
import model.Usuario; // Importa Usuario para demonstrar Polimorfismo
import util.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private static final ClienteDAO clienteDAO = new ClienteDAO();
    private static final ProdutoDAO produtoDAO = new ProdutoDAO();

    public static void main(String[] args) {
        menuPrincipal();
        HibernateUtil.shutdown();
    }

    private static void menuPrincipal() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- 💻 Menu de Gestão ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Consultar");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a linha
            } else {
                System.out.println("❌ Entrada inválida. Digite um número.");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1 -> menuCadastrar();
                case 2 -> menuConsultar();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    
    // --- Lógica de Cadastro (1) ---

    private static void menuCadastrar() {
        int opcao;
        System.out.println("\n--- ➕ Cadastrar ---");
        System.out.println("1. Funcionario");
        System.out.println("2. Cliente");
        System.out.println("3. Produto");
        System.out.print("Selecione o tipo para cadastro: ");

        if (scanner.hasNextInt()) {
            opcao = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("❌ Entrada inválida.");
            scanner.nextLine();
            return;
        }

        try {
            switch (opcao) {
                case 1 -> cadastrarFuncionario();
                case 2 -> cadastrarCliente();
                case 3 -> cadastrarProduto();
                default -> System.out.println("Opção de cadastro inválida.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao processar o cadastro: " + e.getMessage());
        }
    }
    
    private static void cadastrarFuncionario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Salário: R$ ");
        BigDecimal salario = scanner.nextBigDecimal();
        scanner.nextLine(); 

        Funcionario f = new Funcionario(nome, cpf, email, salario);
        funcionarioDAO.salvar(f);
    }
    
    private static void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Limite de Crédito: R$ ");
        BigDecimal limite = scanner.nextBigDecimal();
        scanner.nextLine(); 

        Cliente c = new Cliente(nome, cpf, email, limite);
        clienteDAO.salvar(c);
    }
    
    private static void cadastrarProduto() {
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.print("Preço: R$ ");
        BigDecimal preco = scanner.nextBigDecimal();
        System.out.print("Estoque: ");
        int estoque = scanner.nextInt();
        scanner.nextLine();

        Produto p = new Produto(nome, preco, estoque);
        produtoDAO.salvar(p);
    }

    // --- Lógica de Consulta (2) ---

    private static void menuConsultar() {
        int opcao;
        System.out.println("\n--- 🔍 Consultar ---");
        System.out.println("1. Funcionários");
        System.out.println("2. Clientes");
        System.out.println("3. Produtos");
        System.out.print("Selecione o tipo para consulta: ");

        if (scanner.hasNextInt()) {
            opcao = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("❌ Entrada inválida.");
            scanner.nextLine();
            return;
        }

        switch (opcao) {
            case 1 -> consultarFuncionarios();
            case 2 -> consultarClientes();
            case 3 -> consultarProdutos();
            default -> System.out.println("Opção de consulta inválida.");
        }
    }

    private static void consultarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioDAO.buscarTodos();
        System.out.println("\n--- Lista de Funcionários ---");
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        
        // Demonstração de Polimorfismo: 
        // Chama o método exibirDetalhes() que é específico para Funcionario.
        for (Funcionario f : funcionarios) {
            System.out.println(f.exibirDetalhes()); 
        }
        demonstrarPolimorfismo(funcionarios); // Chamada para demonstração adicional
    }
    
    private static void consultarClientes() {
        List<Cliente> clientes = clienteDAO.buscarTodos();
        System.out.println("\n--- Lista de Clientes ---");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        
        // Demonstração de Polimorfismo: 
        // Chama o método exibirDetalhes() que é específico para Cliente.
        for (Cliente c : clientes) {
            System.out.println(c.exibirDetalhes());
        }
        demonstrarPolimorfismo(clientes); // Chamada para demonstração adicional
    }

    private static void consultarProdutos() {
        List<Produto> produtos = produtoDAO.buscarTodos();
        System.out.println("\n--- Lista de Produtos ---");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (Produto p : produtos) {
            System.out.println(p.exibirDetalhes());
        }
    }

    // --- Demonstração de Polimorfismo Adicional ---
    
    /**
     * Demonstra Polimorfismo: 
     * Aceita uma Lista de objetos que herdam de Usuario.
     */
    private static void demonstrarPolimorfismo(List<? extends Usuario> usuarios) {
        if (!usuarios.isEmpty()) {
             System.out.println("\n--- DEMONSTRAÇÃO DE POLIMORFISMO (Através de List<Usuario>) ---");
             // O tipo da variável 'u' é Usuario, mas o método chamado é o da classe real (Funcionario ou Cliente).
             for (Usuario u : usuarios) {
                 System.out.println("  -> Chamada polimórfica: " + u.exibirDetalhes()); 
             }
        }
    }
}