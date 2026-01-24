package com.minibank.service.inter;

import com.minibank.model.Account;
import com.minibank.model.Customer;

public interface AccountServiceInter {

   void createAccount(Customer customer);

   Account findAccount(Customer customer, int accNum);

   Account getAccount(Customer customer);

   void showAccounts(Customer customer);

   void showAllAccountsBalance(Customer customer);
}
