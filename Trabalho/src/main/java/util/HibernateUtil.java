package util;

import model.Cliente;
import model.Funcionario;
import model.Produto;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Cria a SessionFactory a partir do hibernate.cfg.xml (ou configurações embutidas)
            Configuration configuration = new Configuration();

            // Configuração do SQLite e Mapeamentos
            configuration.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
            configuration.setProperty("hibernate.connection.url", "jdbc:sqlite:gestao.db");
            configuration.setProperty("hibernate.dialect", "org.hibernate.community.dialect.SQLiteDialect");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update"); // Cria/atualiza o schema

            // Mapeamento das classes que serão persistidas (Herdeiras de Usuario + Produto)
            configuration.addAnnotatedClass(Funcionario.class);
            configuration.addAnnotatedClass(Cliente.class);
            configuration.addAnnotatedClass(Produto.class);

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Falha na criação da SessionFactory inicial." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}