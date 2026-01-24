package com.minibank.service;

import com.minibank.model.Customer;
import com.minibank.service.inter.CustomerServiceInter;
import com.minibank.util.InputUtil;


public class CustomerService implements CustomerServiceInter {
    public Customer createCustomer() {
        System.out.println("Welcome, Our MiniBank!");
        System.out.println("Please, fill your Datas: ");
        return new Customer(InputUtil.requireName(),
                InputUtil.requireSurname(),
                InputUtil.requireAge(),
                InputUtil.requireAzeID());
    }
}
