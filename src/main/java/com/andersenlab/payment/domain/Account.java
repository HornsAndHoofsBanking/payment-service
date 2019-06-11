package com.andersenlab.payment.domain;

import java.util.List;

public class Account {

    private long id;

    private long userId;

    private List<Wallet> purses;

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

    public List<Wallet> getPurses() {
        return purses;
    }

    public void setPurses(List<Wallet> purses) {
        this.purses = purses;
    }

}
