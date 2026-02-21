package org.example.com.minibank.service.inter;

import org.example.com.minibank.entity.Accounts;
import org.example.com.minibank.entity.Customers;

public interface AccountServiceInter {
    

   void createAccount(Customers customer);

   Accounts findAccount(Customers customer, String accNum);

   Accounts getAccount(Customers customer);

   void showAccounts(Customers customer);

   void showAllAccountsBalance(Customers customer);
}
