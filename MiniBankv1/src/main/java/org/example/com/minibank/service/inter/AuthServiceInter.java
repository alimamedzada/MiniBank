package org.example.com.minibank.service.inter;

import org.example.com.minibank.model.user.Customer;

public interface AuthServiceInter {
    void register(Customer customer);

    void login(String username, String password);
}
