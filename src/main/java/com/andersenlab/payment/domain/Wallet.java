package com.andersenlab.payment.domain;

import java.math.BigDecimal;
import java.util.Currency;

public class Wallet {

    private long id;

    private Currency currency;

    private BigDecimal amount;

    public Wallet() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
