package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.Usuario;

public class UsuarioDAO {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Configurando a sessão do Hibernate a partir do arquivo hibernate.cfg.xml
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void close() {
        sessionFactory.close();
    }

    public void salvarPessoa(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Usuario getUsuarioById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Usuario.class, id);
        }
    }

    public void atualizarPessoa(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(pessoa);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletarPessoa(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Retorna todos os registros de Pessoa como um ArrayList.
     * @return ArrayList de Pessoas
     */
    public ArrayList<Usuario> getTodasUsuarios() {
        try (Session session = sessionFactory.openSession()) {
            Query<Usuario> query = session.createQuery("FROM Pessoa", Usuario.class);
            List<Usuario> usuariosList = query.list();
            
            // Converte a List para ArrayList explicitamente, se necessário
            return new ArrayList<>(usuariosList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
        }
    }

}