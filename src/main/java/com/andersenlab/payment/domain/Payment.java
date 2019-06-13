package com.andersenlab.payment.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Table(name = "payment")
@Document(indexName = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "acc_id")
    private long accountId;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "execution_start")
    private Timestamp executionStart;

    @Column(name = "execution_end")
    private Timestamp executionEnd;

    @Column(name = "execution_status")
    private String executionStatus;

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
        return ExecutionStatus.valueOf(executionStatus); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    public void setExecutionStatus(ExecutionStatus executionStatus) {
        this.executionStatus = executionStatus.toString(); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
