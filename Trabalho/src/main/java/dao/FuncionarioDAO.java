package dao;

import model.Funcionario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;

public class FuncionarioDAO {

    // Método para CADASTRAR/SALVAR (0.75 pts)
    public void salvar(Funcionario funcionario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(funcionario); // Persiste a entidade no BD
            transaction.commit();
            System.out.println("✅ Funcionário cadastrado com sucesso!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para CONSULTAR TODOS (0.75 pts)
    public List<Funcionario> buscarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // HQL (Hibernate Query Language) para buscar todos
            return session.createQuery("from Funcionario", Funcionario.class).list();
        }
    }
}
// Classes ClienteDAO e ProdutoDAO seriam implementadas de forma similar.