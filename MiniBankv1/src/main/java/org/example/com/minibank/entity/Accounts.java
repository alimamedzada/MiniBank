/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.com.minibank.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.example.com.minibank.exception.InsufficientBalanceException;
import org.example.com.minibank.exception.InvalidAmountException;
import org.example.com.minibank.service.inter.AccountOperationsInter;
import org.example.com.minibank.util.IdentifierUtil;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public class Accounts implements Serializable, AccountOperationsInter {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "account_id")
    private String id;
    @Basic(optional = false)
    @Column(name = "balance")
    private BigDecimal balance;
    @Basic(optional = false)
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ManyToOne
    private Customers customer;

    protected Accounts() {
        this.id = IdentifierUtil.generateAzerbaijanIBANId();
        this.balance = BigDecimal.ZERO;
        this.createDate = new Date(System.currentTimeMillis());
    }

    public Accounts(Customers customer) {
        this();
        this.customer = customer;
    }

    public Accounts(String accountId) {
        this.id = accountId;
    }

    public Accounts(BigDecimal balance) {
        this.id = IdentifierUtil.generateAzerbaijanIBANId();
        this.balance = balance;
        this.createDate = new Date(System.currentTimeMillis());
    }

    public Accounts(String accountId, BigDecimal balance, Date createDate) {
        this.id = accountId;
        this.balance = balance;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String accountId) {
        this.id = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customerId) {
        this.customer = customerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accounts)) {
            return false;
        }
        Accounts other = (Accounts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public void showAllAccountsBalances() {
        System.out.println("Account ID: " + id + "\033[3m" + "\u001B[1m"
                + " | Balance: " + balance + " AZN" + "\u001B[0m" + "\033[0m");
    }

    @Override
    public void deposit(BigDecimal amount) {

        if (amount.compareTo(BigDecimal.valueOf(0)) > 0) {
            balance = balance.add(amount);

        } else if (amount.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new InvalidAmountException("The amount cannot be negative!");
        }
        System.out.println(amount + " AZN has been successfully deposited into your account.");
        showBalance();
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if ((amount.compareTo(balance) < 0)) {
            throw new InvalidAmountException("The amount cannot be negative!");
        }
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientBalanceException("You don't have enough balance in your account!" + balance);
        }
        balance = balance.subtract(amount);
        System.out.println(amount + " AZN has been withdrawn from your account..");
        showBalance();
    }

    @Override
    public void showBalance() {
        System.out.println("Balance: " + balance);
    }

    @Override
    public String toString() {
        return "Accounts{" + "id=" + id + ", balance=" + balance + ", createDate=" + createDate + ", customer id= " + customer.getCustomerId() + '}';
    }

}
