package dao;

import model.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;

public class ClienteDAO {

    /**
     * Método para CADASTRAR/SALVAR um Cliente no banco de dados.
     * @param cliente O objeto Cliente a ser persistido.
     */
    public void salvar(Cliente cliente) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(cliente); // Persiste o cliente no BD
            transaction.commit();
            System.out.println("✅ Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("❌ Erro ao salvar o cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método para CONSULTAR todos os Clientes cadastrados.
     * @return Uma lista de objetos Cliente.
     */
    public List<Cliente> buscarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // HQL (Hibernate Query Language) para buscar todos os Clientes
            return session.createQuery("from Cliente", Cliente.class).list();
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar clientes: " + e.getMessage());
            e.printStackTrace();
            return List.of(); // Retorna lista vazia em caso de erro
        }
    }
}