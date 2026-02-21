package org.example.com.minibank.service.impl;

import org.example.com.minibank.entity.Customers;
import org.example.com.minibank.service.inter.CustomerServiceInter;
import org.example.com.minibank.util.InputUtil;

public class CustomerServiceImpl implements CustomerServiceInter {
    public Customers createCustomer() {
        System.out.println("Welcome, Our MiniBank!");
        System.out.println("Please, fill your Data's: ");
        
        return Customers.createNewCustomer(InputUtil.requireName(), InputUtil.requireSurname(), InputUtil.requireAge(), InputUtil.requireAzeID(), InputUtil.requireUsername(), InputUtil.requirePassword());
    }
}
