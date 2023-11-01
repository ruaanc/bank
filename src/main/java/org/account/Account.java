package org.account;

import java.math.BigDecimal;

public class Account {

    private Long number;
    private AccountType accountType;
    private BigDecimal total;
    private boolean blocked;

    public Account(Long number, AccountType accountType, BigDecimal total, boolean blocked) {
        this.number = number;
        this.accountType = accountType;
        this.total = total;
        this.blocked = blocked;
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

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
