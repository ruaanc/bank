import org.account.Account;
import org.account.AccountService;
import org.account.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.user.User;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    private final String USER_01 = "User01";
    private String USER_02 = "User02";
    private final BigDecimal DEFAULT_TOTAL = new BigDecimal("1000.00");
    private final Long DEFAULT_ACCOUNT_NUMBER = 1000L;
    private final Long DEFAULT_ID = 1L;

    private AccountService accountService;


    @BeforeEach
    void setUp() {
        this.accountService = new AccountService();
    }

    @Test
    void shouldInstantiateUser() {
        Account currentAccount = new Account(DEFAULT_ACCOUNT_NUMBER, AccountType.CURRENT_ACCOUNT, DEFAULT_TOTAL);
        User user01 = new User(DEFAULT_ID, USER_01, currentAccount);
        assertEquals(user01.getName(), USER_01);
    }

    @Test
    void shouldTransferMoneyBetweenUsers() {
        Account currentAccountToTransferUser = new Account(DEFAULT_ACCOUNT_NUMBER, AccountType.CURRENT_ACCOUNT,
                DEFAULT_TOTAL);
        User transferUser = new User(DEFAULT_ID, USER_01, currentAccountToTransferUser);

        Account currentAccountToReceivingUser = new Account(1001L, AccountType.CURRENT_ACCOUNT, DEFAULT_TOTAL);
        User receivingUser = new User(2L, USER_02, currentAccountToReceivingUser);

        BigDecimal currentTotalToTransferUser = this.accountService.transfer(new BigDecimal("500.00"), transferUser,
                receivingUser);
        assertEquals(currentTotalToTransferUser, new BigDecimal("500.00"));
    }

    @Test
    void shouldReturnUserBalance() {
        Account account = new Account(DEFAULT_ACCOUNT_NUMBER, AccountType.CURRENT_ACCOUNT, DEFAULT_TOTAL);
        User user = new User(DEFAULT_ID, USER_01, account);
        assertEquals(DEFAULT_TOTAL, this.accountService.checkBalance(user));
    }

}
