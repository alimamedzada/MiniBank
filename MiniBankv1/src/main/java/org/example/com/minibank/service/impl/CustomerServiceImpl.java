package org.example.com.minibank.service.impl;

import org.example.com.minibank.model.user.Customer;
import org.example.com.minibank.service.inter.CustomerServiceInter;
import org.example.com.minibank.util.InputUtil;


public class CustomerServiceImpl implements CustomerServiceInter {
    public Customer createCustomer() {
        System.out.println("Welcome, Our MiniBank!");
        System.out.println("Please, fill your Datas: ");
        return new Customer(
                InputUtil.requireName(),
                InputUtil.requireSurname(),
                InputUtil.requireAge(),
                InputUtil.requireAzeID(),
                InputUtil.requireUsername(),
                InputUtil.requirePassword());
    }
}
