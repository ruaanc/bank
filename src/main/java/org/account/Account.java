package org.account;

import java.math.BigDecimal;

public class Account {

    private Long number;
    private AccountType accountType;
    private BigDecimal total;

    public Account(Long number, AccountType accountType, BigDecimal total) {
        this.number = number;
        this.accountType = accountType;
        this.total = total;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
