package com.andersenlab.payment.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @OneToMany(fetch = FetchType.EAGER,
               targetEntity = Wallet.class,
               orphanRemoval = true)
    @JoinColumn(name = "acc_id")
    private List<Wallet> wallets;

    public Account() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser() {
        return userId;
    }

    public void setUser(long user) {
        this.userId = user;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> purses) {
        this.wallets = purses;
    }

}
