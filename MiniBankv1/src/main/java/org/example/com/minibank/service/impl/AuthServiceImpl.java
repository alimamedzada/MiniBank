package org.example.com.minibank.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.com.minibank.exception.AuthenticationException;
import org.example.com.minibank.entity.Customers;
import org.example.com.minibank.service.inter.AuthServiceInter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.com.minibank.dao.impl.CustomerDaoImpl;
import org.example.com.minibank.dao.inter.CustomerDaoInter;

public class AuthServiceImpl implements AuthServiceInter {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public Customers register(Customers customer) {
        CustomerDaoInter customerDao = new CustomerDaoImpl();
        customerDao.addCustomer(customer);
        return customer;
    }

    public static BCrypt.Result verify(String password, Customers customer) {
        return BCrypt.verifyer().verify(password.toCharArray(), customer.getPassword());
    }

    public Customers login(String username, String password) {
        CustomerDaoInter customerDao = new CustomerDaoImpl();
        Customers customer = customerDao.getCustomerByUsername(username);
        if (customer == null) {
            logger.error("User not found: {}", username);
            throw new AuthenticationException("User not found or invalid password!");
        }

        BCrypt.Result result = verify(password, customer);

        if (result.verified) {
            logger.info("Successfully accessed");
            System.out.println(customer.getAccounts());

            return customer;
        } else {
            logger.error("wrong!");
            throw new AuthenticationException("User not found or invalid password: " + username);
        }
    }
}
