package org.example.com.minibank.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.sql.SQLException;
import java.util.List;
import static org.example.com.minibank.dao.inter.AbstractDAO.em;
import org.example.com.minibank.dao.inter.AccountDaoInter;
import org.example.com.minibank.entity.Accounts;

public class AccountDaoImpl implements AccountDaoInter {

    @Override
    public List<Accounts> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAccount(Accounts account) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean deleteAccount(String id) {
        EntityManager em = em();
        Accounts account = em.find(Accounts.class, id);
        em.getTransaction().begin();
        em.remove(account);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean updateAccount(Accounts account) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.merge(account);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public Accounts getAccountByCustomerId(String id) {

        EntityManager em = em();
        Query query = em.createQuery(" select a from Accounts a where a.customer.customerId = :id", Accounts.class);
        query.setParameter("id", id);

        Accounts account = (Accounts) query.getResultList().get(0);
        em.close();
        return account;
    }

}
