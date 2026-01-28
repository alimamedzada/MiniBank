package org.example.com.minibank.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.com.minibank.model.user.Customer;
import org.example.com.minibank.repository.CustomerRepository;
import org.example.com.minibank.service.inter.AuthServiceInter;

import java.util.HashMap;

public class AuthServiceImpl implements AuthServiceInter {
    @Override
    public void register(Customer customer) {
        CustomerRepository.customers.put(customer.getCustomerId(), customer);
    }


    public void login(String username, String password) {
        HashMap<String, Customer> customers = CustomerRepository.customers;
        for (Customer customer : customers.values()) {
            if (customer.getUsername().equals(username)) {
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), customer.getPassword());
                if (result.verified) {
                    System.out.println("girish dogrulandi!");
                    return;
                } else {
                    System.out.println("sehv");
                }
            }
        }
    }
}
