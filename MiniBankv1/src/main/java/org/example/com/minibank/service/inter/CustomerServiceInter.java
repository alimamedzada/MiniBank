package org.example.com.minibank.service.inter;

import org.example.com.minibank.entity.Customers;

public interface CustomerServiceInter {

    Customers fillCustomerData();

    void addCustomer(Customers customer);

    void validateCustomersData(Customers customer);
}
