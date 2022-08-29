package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "my_db";
        String userName = "bestuser";
        String password = "bestuser";

        return getMyConnection(hostName, dbName, userName, password);
    }

    private static Connection getMyConnection(String hostName, String dbName, String userName, String password) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection connection = DriverManager.getConnection(connectionURL, userName, password);

        return connection;
    }

    // Подключение к БД с помощью Hibernate

    public static SessionFactory getSessionFactory() {
        String hostName = "localhost";
        String dbName = "my_db";
        String userName = "bestuser";
        String password = "bestuser";
        String port = "3306";
        return getMySession(hostName, port, dbName, userName, password);
    }

    private static SessionFactory getMySession(String hostName, String port, String dbName, String userName, String password) {
        Properties properties = new Properties();

        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://" + hostName + ":" + port + "/" + dbName);
        properties.put(Environment.USER, userName);
        properties.put(Environment.PASS, password);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class)
                .setProperties(properties);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        SessionFactory sessionFactory = configuration
                .buildSessionFactory(serviceRegistry);

        return sessionFactory;

    }

}
