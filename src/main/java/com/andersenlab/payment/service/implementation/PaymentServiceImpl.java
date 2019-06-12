package com.andersenlab.payment.service.implementation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersenlab.payment.dao.AccountDao;
import com.andersenlab.payment.domain.Account;
import com.andersenlab.payment.domain.Payment;
import com.andersenlab.payment.domain.Payment.ExecutionStatus;
import com.andersenlab.payment.domain.Wallet;
import com.andersenlab.payment.service.PaymentService;
import com.andersenlab.payment.service.exception.NotEnoughFoundsException;
import com.andersenlab.payment.service.exception.PaymentExecutionException;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private AccountDao accountDao;

    @Autowired
    public PaymentServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Payment execute(Payment payment) {
        registerExecutionStart(payment);
        try {
            Optional<Account> result = accountDao.findById(payment.getAccountId());
            Account account = result.get();

            BigDecimal withdrawAmount = payment.getAmount();

            Wallet suitableWallet = checkFounds(account, withdrawAmount, payment.getCurrency());

            withdraw(suitableWallet, withdrawAmount);
        } catch (Exception e) {
            payment.setExecutionStatus(ExecutionStatus.FAILED);
            throw new PaymentExecutionException("payment " + payment.getId() + " failed" + System.lineSeparator()
                    + "Caused by:" + System.lineSeparator() + e.getCause());
        } finally {
            registerExecutionEnd(payment);
            return payment;
        }
    }

    private void registerExecutionStart(Payment payment) {
        Timestamp now = new Timestamp(new Date().getTime());
        payment.setExecutionStart(now);
        payment.setExecutionStatus(ExecutionStatus.SUCCESS);
    }

    private Wallet checkFounds(Account account, BigDecimal withdrawAmount, Currency currency) {
        Wallet suitableWallet = null;
        for (Wallet wallet : account.getWallets()) {
            if (wallet.getCurrency().equals(currency)) {
                suitableWallet = wallet;
                break;
            }
        }

        if (suitableWallet == null) {
            throw new NotEnoughFoundsException("There is no wallet with specified currency.");
        }

        BigDecimal balance = suitableWallet.getAmount();
        boolean notEnoughFounds = balance.compareTo(withdrawAmount) == -1;

        if (notEnoughFounds) {
            throw new NotEnoughFoundsException("Not enough founds, wallet ID: " + suitableWallet.getId());
        }

        return suitableWallet;
    }

    private void withdraw(Wallet wallet, BigDecimal withdrawAmount) {
        BigDecimal balance = wallet.getAmount();
        balance = balance.subtract(withdrawAmount);
        wallet.setAmount(balance);
    }

    private void registerExecutionEnd(Payment payment) {
        Timestamp now = new Timestamp(new Date().getTime());
        payment.setExecutionEnd(now);
    }
}
