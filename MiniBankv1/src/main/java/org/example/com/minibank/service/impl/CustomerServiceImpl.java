package org.example.com.minibank.service.impl;

import org.example.com.minibank.dao.impl.CustomerDaoImpl;
import org.example.com.minibank.dao.inter.CustomerDaoInter;
import org.example.com.minibank.entity.Customers;
import org.example.com.minibank.service.inter.CustomerServiceInter;
import org.example.com.minibank.util.InputUtil;
import org.example.com.minibank.util.ValidationUtil;

public class CustomerServiceImpl implements CustomerServiceInter {

    private static CustomerDaoInter customerDao = new CustomerDaoImpl();

    public Customers fillCustomerData() {
        System.out.println("Welcome, Our MiniBank!");
        System.out.println("Please, fill your Data's: ");

        return Customers.createNewCustomer(InputUtil.requireName(),
                InputUtil.requireSurname(),
                InputUtil.requireAge(),
                InputUtil.requireAzeID(),
                InputUtil.requireUsername(),
                InputUtil.requirePassword());
    }

    @Override
    public void addCustomer(Customers customer) {
        validateCustomersData(customer);
        customerDao.addCustomer(customer);
    }

    public void validateCustomersData(Customers c) {
        ValidationUtil.validateName(c.getName());
        ValidationUtil.validateSurname(c.getSurname());
        ValidationUtil.validateAge(c.getAge());
        ValidationUtil.validateAzeID(c.getazeID());
        ValidationUtil.validateUsername(c.getUsername());
        ValidationUtil.validatePasswordStrength(c.getPassword());
    }
}
