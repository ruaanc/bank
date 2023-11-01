package org.account;

import org.account.exceptions.BlockedAccountException;
import org.account.exceptions.InsufficientFundsException;
import org.account.exceptions.NotDepositNegativeAmountException;
import org.user.User;

import java.math.BigDecimal;

public class AccountService {

    /*
    AccountTest: shouldTransferMoneyBetweenUsers
    * @CheckExpect("transfer(new BigDecimal("500.00"), new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
                new BigDecimal("1000.00")), false), new User(2L, "User02", new Account(1001L, AccountType.CURRENT_ACCOUNT,
                new BigDecimal("1000.00")), false))", "new BigDecimal("500.00")")
    * */

    /*
    AccountTest: shouldReturnInsufficientBalanceException
    * @CheckError("transfer(new BigDecimal("500.00"), new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
                new BigDecimal("0.00")), false), new User(2L, "User02", new Account(1001L, AccountType.CURRENT_ACCOUNT,
                new BigDecimal("1000.00")), false))", "InsufficientFundsException.class")
    * */

    /*
    AccountTest: shouldReturnExceptionTryingTransferFromBlockedAccount
    * @CheckError("transfer(new BigDecimal("500.00"), new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
                new BigDecimal("1000.00")), true), new User(2L, "User02", new Account(1001L, AccountType.CURRENT_ACCOUNT,
                new BigDecimal("1000.00")), false))", "BlockedAccountException.class")
    * */
    public BigDecimal transfer(BigDecimal totalToTransfer, User transferUser, User receivingUser) {
        Account transferUserAccount = transferUser.getAccount();

        if(transferUserAccount.isBlocked()) {
            throw new BlockedAccountException("The transferring user account is locked.");
        }

        int comparisonAnswer = transferUserAccount.getTotal().compareTo(totalToTransfer);

        if(comparisonAnswer < 0) {
            throw new InsufficientFundsException();
        }

        transferUserAccount.setTotal(transferUserAccount.getTotal().subtract(totalToTransfer));

        Account receivingUserAccount = receivingUser.getAccount();

        receivingUserAccount.setTotal(receivingUserAccount.getTotal().add(totalToTransfer));

        return transferUserAccount.getTotal();
    }

    /*
    AccountTest: shouldReturnUserBalance
    * @CheckExpect("checkBalance(new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
    new BigDecimal("1000.00")), false))", "new BigDecimal("1000.00")")
    * */
    public BigDecimal checkBalance(User user) {
        return user.getAccount().getTotal();
    }

    /*
    AccountTest: shouldSuccessfullyDepositIntoUserAccount
    * @CheckExpect("deposit(new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
        new BigDecimal("1000.00")), false), new BigDecimal("500.00"))", "new BigDecimal("1500.00")")
    * */

    /*
    AccountTest: shouldReturnErrorWhenDepositingNegativeAmount
    * @CheckError("deposit(new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
        new BigDecimal("1000.00")), false), new BigDecimal("-500.00"))", "NotDepositNegativeAmountException.class")
    * */
    public BigDecimal deposit(User user, BigDecimal amount) {
        if(amount.signum() < 0) {
            throw new NotDepositNegativeAmountException();
        }
        Account account = user.getAccount();
        account.setTotal(account.getTotal().add(amount));
        return account.getTotal();
    }

}
