package org.example.com.minibank.dao.inter;

import java.sql.SQLException;
import java.util.List;
import org.example.com.minibank.entity.Accounts;

public interface AccountDaoInter {

    List<Accounts> getAll() throws SQLException;

    boolean addAccount(Accounts account);

    boolean deleteAccount(String id);

    boolean updateAccount(Accounts account);

    Accounts getAccountByCustomerId(String id);
}
