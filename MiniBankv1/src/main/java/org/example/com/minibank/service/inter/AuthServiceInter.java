package org.example.com.minibank.service.inter;

import org.example.com.minibank.entity.Customers;

public interface AuthServiceInter {
    Customers register(Customers customer);

    Customers login(String username, String password);
}
