package org.example.com.minibank.dao.impl;

import org.example.com.minibank.dao.inter.CustomerDaoInter;
import org.example.com.minibank.model.account.Account;
import org.example.com.minibank.model.user.Customer;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.com.minibank.dao.AbstractDAO.connect;

public class CustomerDaoImpl implements CustomerDaoInter {

    @Override
    public List<Customer> getAll() {
        List<Customer> allList = new ArrayList<>();
        try (Connection c = connect()) {
            String sql = "SELECT\n" +
                    "  c.*,\n" +
                    "  a.*\n" +
                    "FROM\n" +
                    "  customers c \n" +
                    "  LEFT JOIN accounts a ON c.customer_id = a.customer_id";
            Statement ps = c.createStatement();
            ps.execute(sql);
            ResultSet result = ps.getResultSet();
            while (result.next()) {
                allList.add(getCustomer(result));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allList;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        try (Connection c = connect()) {
            PreparedStatement ps = c.prepareStatement("insert into customers(name,surname,aze_id,age,username,password,customer_id) values (?,?,?,?,?,?,?)");
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSurname());
            ps.setString(3, customer.getazeID());
            ps.setInt(4, customer.getAge());
            ps.setString(5, customer.getUsername());
            ps.setString(6, customer.getPassword());
            ps.setString(7, customer.getCustomerId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getCustomer(ResultSet result) throws SQLException {
        String id = result.getString("customer_id");
        String name = result.getString("name");
        String surname = result.getString("surname");
        String azeId = result.getString("aze_id");
        int age = result.getInt("age");
        String username = result.getString("username");
        String password = result.getString("password");
        String accountId = result.getString("account_id");
        BigDecimal balance = result.getBigDecimal("balance");
        Date dt = result.getDate("create_date");
        Account account = new Account(accountId, balance, dt);

        return Customer.mapFromDatabase(account, name, surname, age, azeId, username, password, id);
    }

    @Override
    public boolean deleteCustomer() {
        return false;
    }

    @Override
    public boolean updateCustomer() {
        return false;
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        Customer customer = null;
        String sql = "SELECT c.*, a.* FROM customers c " +
                "LEFT JOIN accounts a ON c.customer_id = a.customer_id " +
                "WHERE c.username = ?";
        try (Connection c = connect()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ps.execute();
            ResultSet result = ps.getResultSet();
            while (result.next()) {
                customer = getCustomer(result);
            }
            return customer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
