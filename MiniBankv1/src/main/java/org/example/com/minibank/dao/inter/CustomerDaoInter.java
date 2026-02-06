package org.example.com.minibank.dao.inter;

import org.example.com.minibank.model.user.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDaoInter {
    List<Customer> getAll() throws SQLException;

    boolean addCustomer(Customer customer);

    Customer getCustomer(ResultSet result) throws SQLException;

    boolean deleteCustomer();

    boolean updateCustomer();

    Customer getCustomerByUsername(String id);

}
