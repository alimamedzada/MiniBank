package org.example.com.minibank.service.inter;

import org.example.com.minibank.model.account.Account;
import org.example.com.minibank.model.user.Customer;

public interface AccountServiceInter {

   void createAccount(Customer customer);

   Account findAccount(Customer customer, String accNum);

   Account getAccount(Customer customer);

   void showAccounts(Customer customer);

   void showAllAccountsBalance(Customer customer);
}
