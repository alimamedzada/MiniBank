package org.example.com.minibank.service.impl;

import org.example.com.minibank.model.user.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.com.minibank.exception.AuthenticationException;
import org.example.com.minibank.repository.CustomerRepository;
import org.example.com.minibank.service.inter.AuthServiceInter;

import java.util.HashMap;

public class AuthServiceImpl implements AuthServiceInter {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public Customer register(Customer customer) {
        CustomerRepository.customers.put(customer.getCustomerId(), customer);
        return customer;
    }


    public Customer login(String username, String password) {
        HashMap<String, Customer> customers = CustomerRepository.customers;
        Customer customer = customers.values().stream()
                .filter(c -> c.getUsername().equals(username))
                .findFirst().orElseThrow(() -> {
                    logger.error("User not found or invalid password:{} ", username);
                    return new AuthenticationException("User not found or invalid password: " + username);
                });
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), customer.getPassword());
        if (result.verified) {
            logger.info("Successfully accessed");
            System.out.println(customer.getAccounts().values());

            return customer;
        } else {
            logger.error("wrong!");
            throw new AuthenticationException("User not found or invalid password: " + username);
        }
    }
}
