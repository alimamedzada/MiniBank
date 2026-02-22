/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.com.minibank.entity;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.example.com.minibank.util.IdentifierUtil;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "customers")
public class Customers extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "customer_id")
    private String customerId;

    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Accounts> accounts = new ArrayList<>();

    public Customers() {
    }

    private Customers(String name, String surname, int age, String AzeID, String username, String password, String customerId, boolean isNew) {
        super(name, surname, age, AzeID);

        this.username = username;
        this.customerId = customerId;
        this.password = password;
    }

    private Customers(Accounts account, String name, String surname, int age, String AzeID, String username, String password, String customerId, boolean isNew) {
        super(name, surname, age, AzeID);
        this.username = username;
        this.customerId = customerId;
        this.password = password;
        if (account != null) {
            this.accounts.add(account);
        }
    }

    public static Customers createNewCustomer(String name, String surname, int age, String AzeID, String username, String password) {
        String generatedId = IdentifierUtil.generateId(10);

        Customers customers = new Customers(name, surname, age, AzeID, username, password, generatedId, true);
        CheckingAccount account = new CheckingAccount(BigDecimal.ZERO);
        customers.addAccount(account);
        return customers;
    }

    public static Customers mapFromDatabase(Accounts account, String name, String surname, int age, String AzeID, String username, String password, String customerId) {
        return new Customers(account, name, surname, age, AzeID, username, password, customerId, false);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Accounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Accounts> accountsList) {
        this.accounts = accountsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customers)) {
            return false;
        }
        Customers other = (Customers) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " | Customer ID: " + customerId + "| Username: " + username + " | Accounts Count: " + (accounts != null ? accounts.size() : 0);
    }

    public void addAccount(Accounts account) {
        if (account != null) {

            this.accounts.add(account);
            account.setCustomer(this);
        }
    }
}
