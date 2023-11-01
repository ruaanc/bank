import org.account.Account;
import org.account.AccountService;
import org.account.AccountType;
import org.account.exceptions.BlockedAccountException;
import org.account.exceptions.InsufficientFundsException;
import org.account.exceptions.NotDepositNegativeAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.user.User;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {

    private final String USER_01 = "User01";
    private final String USER_02 = "User02";
    private final BigDecimal DEFAULT_TOTAL = new BigDecimal("1000.00");
    private final Long DEFAULT_ACCOUNT_NUMBER = 1000L;
    private final Long DEFAULT_ID = 1L;
    private final BigDecimal INSUFFICIENT_FUNDS = new BigDecimal("0.00");
    private final BigDecimal STANDARD_ADDITION = new BigDecimal("500.00");
    private final BigDecimal STANDARD_ADDITION_NEGATIVE = new BigDecimal("-500.00");

    private AccountService accountService;


    @BeforeEach
    void setUp() {
        this.accountService = new AccountService();
    }

    @Test
    void shouldInstantiateUser() {
        Account currentAccount = new Account(DEFAULT_ACCOUNT_NUMBER, AccountType.CURRENT_ACCOUNT, DEFAULT_TOTAL,
                false);
        User user01 = new User(DEFAULT_ID, USER_01, currentAccount);
        assertEquals(user01.getName(), USER_01);
    }

    @Test
    void shouldTransferMoneyBetweenUsers() {
        Account currentAccountToTransferUser = new Account(DEFAULT_ACCOUNT_NUMBER, AccountType.CURRENT_ACCOUNT,
                DEFAULT_TOTAL, false);
        User transferUser = new User(DEFAULT_ID, USER_01, currentAccountToTransferUser);

        Account currentAccountToReceivingUser = new Account(1001L, AccountType.CURRENT_ACCOUNT, DEFAULT_TOTAL,
                false);
        User receivingUser = new User(2L, USER_02, currentAccountToReceivingUser);

        BigDecimal currentTotalToTransferUser = this.accountService.transfer(STANDARD_ADDITION, transferUser,
                receivingUser);
        assertEquals(currentTotalToTransferUser, STANDARD_ADDITION);
    }

    @Test
    void shouldReturnUserBalance() {
        Account account = new Account(DEFAULT_ACCOUNT_NUMBER, AccountType.CURRENT_ACCOUNT, DEFAULT_TOTAL, false);
        User user = new User(DEFAULT_ID, USER_01, account);
        assertEquals(DEFAULT_TOTAL, this.accountService.checkBalance(user));
    }

    @Test
    void shouldReturnInsufficientBalanceException() {
        Account currentAccountToTransferUser = new Account(DEFAULT_ACCOUNT_NUMBER, AccountType.CURRENT_ACCOUNT,
                INSUFFICIENT_FUNDS, false);
        User transferUser = new User(DEFAULT_ID, USER_01, currentAccountToTransferUser);

        Account currentAccountToReceivingUser = new Account(1001L, AccountType.CURRENT_ACCOUNT, DEFAULT_TOTAL,
                false);
        User receivingUser = new User(2L, USER_02, currentAccountToReceivingUser);

        assertThrows(InsufficientFundsException.class, () -> this.accountService.transfer(STANDARD_ADDITION,
                transferUser, receivingUser));
    }

    @Test
    void shouldSuccessfullyDepositIntoUserAccount() {
        Account account = new Account(DEFAULT_ACCOUNT_NUMBER, AccountType.CURRENT_ACCOUNT,
                DEFAULT_TOTAL, false);
        User user = new User(DEFAULT_ID, USER_01, account);

        BigDecimal currentTotal = this.accountService.deposit(user, STANDARD_ADDITION);

        assertEquals(currentTotal, new BigDecimal("1500.00"));

    }

    @Test
    void shouldReturnErrorWhenDepositingNegativeAmount() {
        Account account = new Account(DEFAULT_ACCOUNT_NUMBER, AccountType.CURRENT_ACCOUNT,
                DEFAULT_TOTAL, false);
        User user = new User(DEFAULT_ID, USER_01, account);

        assertThrows(NotDepositNegativeAmountException.class, () -> this.accountService.deposit(user,
                STANDARD_ADDITION_NEGATIVE));

    }

    @Test
    void shouldReturnExceptionTryingTransferFromBlockedAccount() {
        Account currentAccountToTransferUser = new Account(DEFAULT_ACCOUNT_NUMBER, AccountType.CURRENT_ACCOUNT,
                DEFAULT_TOTAL, true);
        User transferUser = new User(DEFAULT_ID, USER_01, currentAccountToTransferUser);

        Account currentAccountToReceivingUser = new Account(1001L, AccountType.CURRENT_ACCOUNT, DEFAULT_TOTAL,
                false);
        User receivingUser = new User(2L, USER_02, currentAccountToReceivingUser);

        assertThrows(BlockedAccountException.class, () -> this.accountService.transfer(STANDARD_ADDITION,
                transferUser, receivingUser));
    }

}
