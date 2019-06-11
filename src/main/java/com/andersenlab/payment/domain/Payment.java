package com.andersenlab.payment.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;

public class Payment {

    private long id;

    private long accountId;

    private Currency currency;

    private BigDecimal amount;

    private Timestamp executionStart;

    private Timestamp executionEnd;

    private ExecutionStatus executionStatus;

    public Payment() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
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

    public Timestamp getExecutionStart() {
        return executionStart;
    }

    public void setExecutionStart(Timestamp executionStart) {
        this.executionStart = executionStart;
    }

    public Timestamp getExecutionEnd() {
        return executionEnd;
    }

    public void setExecutionEnd(Timestamp executionEnd) {
        this.executionEnd = executionEnd;
    }

    public ExecutionStatus getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(ExecutionStatus executionStatus) {
        this.executionStatus = executionStatus;
    }

    public enum ExecutionStatus {
        SUCCESS, FAILED;
    }

    @Override
    public String toString() {
        return "Payment [id=" + id + ", accountId=" + accountId + ", currency=" + currency + ", amount=" + amount
                + ", executionStart=" + executionStart + ", executionEnd=" + executionEnd + ", executionStatus="
                + executionStatus + "]";
    }

}
