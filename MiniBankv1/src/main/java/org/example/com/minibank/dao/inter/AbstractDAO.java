package org.example.com.minibank.dao.inter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;

public class AbstractDAO {

    public static Connection connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "JDBC:mysql://localhost:3306/minibank";
        String username = "root";
        String password = "12345";
        return DriverManager.getConnection(url, username, password);
    }
    ;
    private static EntityManagerFactory emF = null;

    public static EntityManager em() {
        if (emF == null) {
            emF = Persistence.createEntityManagerFactory("minibankPU");
        }
        EntityManager entityManager = emF.createEntityManager();

        return entityManager;
    }

    public void close() {
        emF.close();
    }
}
