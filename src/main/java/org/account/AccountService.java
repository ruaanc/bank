package org.account;

import org.user.User;

import java.math.BigDecimal;

public class AccountService {

    /*
    UserTest: shouldTransferMoneyBetweenUsers
    * @CheckExpect("transfer(new BigDecimal("500.00"), new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
                new BigDecimal("1000.00"))), new User(2L, "User02", new Account(1001L, AccountType.CURRENT_ACCOUNT,
                new BigDecimal("1000.00"))))", "new BigDecimal("500.00")")
    * */
    public BigDecimal transfer(BigDecimal totalToTransfer, User transferUser, User receivingUser) {
        Account transferUserAccount = transferUser.getAccount();
        transferUserAccount.setTotal(transferUserAccount.getTotal().subtract(totalToTransfer));

        Account receivingUserAccount = receivingUser.getAccount();
        receivingUserAccount.setTotal(receivingUserAccount.getTotal().add(totalToTransfer));

        return transferUserAccount.getTotal();
    }


    /*
    UserTest: shouldReturnUserBalance
    * @CheckExpect("checkBalance(new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
    new BigDecimal("1000.00"))))", "new BigDecimal("1000.00")")
    * */
    public BigDecimal checkBalance(User user) {
        return user.getAccount().getTotal();
    }

}
