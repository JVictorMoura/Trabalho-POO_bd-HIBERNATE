package dao;

import model.Produto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;

public class ProdutoDAO {

    /**
     * Método para CADASTRAR/SALVAR um Produto no banco de dados.
     * @param produto O objeto Produto a ser persistido.
     */
    public void salvar(Produto produto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(produto); // Persiste o produto no BD
            transaction.commit();
            System.out.println("✅ Produto cadastrado com sucesso!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("❌ Erro ao salvar o produto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método para CONSULTAR todos os Produtos cadastrados.
     * @return Uma lista de objetos Produto.
     */
    public List<Produto> buscarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // HQL (Hibernate Query Language) para buscar todos os Produtos
            return session.createQuery("from Produto", Produto.class).list();
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar produtos: " + e.getMessage());
            e.printStackTrace();
            return List.of(); // Retorna lista vazia em caso de erro
        }
    }
}