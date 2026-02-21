package org.example.com.minibank.dao.inter;

import org.example.com.minibank.entity.Customers;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDaoInter {

    List<Customers> getAll() throws SQLException;

    boolean addCustomer(Customers customer);

    boolean deleteCustomer(int id);

    boolean updateCustomer(Customers customer);

    Customers getCustomerByUsername(String id);

}
