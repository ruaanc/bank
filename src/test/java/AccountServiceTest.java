import org.account.Account;
import org.account.AccountService;
import org.account.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.user.User;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AccountServiceTest {

    @Test
    void methodTransferShouldBeExecutedSuccessfully() {
        AccountService mock = spy(AccountService.class);

        mock.transfer(new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
        new BigDecimal("1000.00"), false)), new BigDecimal("500.00"));

        verify(mock, times(1)).deposit(
            ArgumentCaptor.forClass(BigDecimal.class).capture(),
            ArgumentCaptor.forClass(User.class).capture(),
            ArgumentCaptor.forClass(User.class).capture()
        );
    }
    @Test
    void methodDepositShouldBeExecutedSuccessfully() {
        AccountService mock = spy(AccountService.class);

        mock.deposit(new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
        new BigDecimal("1000.00"), false)), new BigDecimal("500.00"));

        verify(mock, times(1)).deposit(
            ArgumentCaptor.forClass(User.class).capture(),
            ArgumentCaptor.forClass(BigDecimal.class).capture()
        );
    }
    @Test
    void methodCheckBalanceShouldBeExecutedSuccessfully() {
        AccountService mock = spy(AccountService.class);

        mock.checkBalance(new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
        new BigDecimal("1000.00"), false)), new BigDecimal("500.00"));

        verify(mock, times(1)).deposit(
            ArgumentCaptor.forClass(User.class).capture()
        );
    }
    @Test
    void methodWaitShouldBeExecutedSuccessfully() {
        AccountService mock = spy(AccountService.class);

        mock.wait(new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
        new BigDecimal("1000.00"), false)), new BigDecimal("500.00"));

        verify(mock, times(1)).deposit(
            ArgumentCaptor.forClass(long.class).capture(),
            ArgumentCaptor.forClass(int.class).capture()
        );
    }
    @Test
    void methodWaitShouldBeExecutedSuccessfully() {
        AccountService mock = spy(AccountService.class);

        mock.wait(new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
        new BigDecimal("1000.00"), false)), new BigDecimal("500.00"));

        verify(mock, times(1)).deposit(
            ArgumentCaptor.forClass(long.class).capture()
        );
    }
    @Test
    void methodEqualsShouldBeExecutedSuccessfully() {
        AccountService mock = spy(AccountService.class);

        mock.equals(new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
        new BigDecimal("1000.00"), false)), new BigDecimal("500.00"));

        verify(mock, times(1)).deposit(
            ArgumentCaptor.forClass(Object.class).capture()
        );
    }
}

