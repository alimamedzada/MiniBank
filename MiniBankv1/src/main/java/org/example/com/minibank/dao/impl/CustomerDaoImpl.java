package org.example.com.minibank.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.com.minibank.dao.inter.CustomerDaoInter;
import org.example.com.minibank.entity.Customers;

import java.util.List;
import org.example.com.minibank.dao.inter.AbstractDAO;

public class CustomerDaoImpl implements CustomerDaoInter {

    public List<Customers> getAll() {
        EntityManager em = AbstractDAO.em();
        Query query = em.createQuery(
                "select distinct c from Customers c LEFT JOIN FETCH c.accounts");
        List<Customers> list = query.getResultList();
        em.close();
        return list;
    }

    public boolean addCustomer(Customers customer) {
        EntityManager em = AbstractDAO.em();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean updateCustomer(Customers customer) {
        EntityManager em = AbstractDAO.em();
        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean deleteCustomer(int id) {
        EntityManager em = AbstractDAO.em();
        Customers c = em.find(Customers.class, id);
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public Customers getCustomerByUsername(String username) {
        EntityManager em = AbstractDAO.em();
        Query query = em.createQuery("select c from Customers c where c.username = :u", Customers.class);
        query.setParameter("u", username);
        Customers c = (Customers) query.getResultList().get(0);
        em.close();
        return c;
    }
}
