import org.account.Account;
import org.account.AccountService;
import org.account.AccountType;
import org.code_generator.ExpectedResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.user.User;
import java.math.BigDecimal;

import static org.account.AccountType.CURRENT_ACCOUNT;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @Test
    void methodTransferShouldBeExecutedSuccessfully() {
        AccountService mock = spy(AccountService.class);

        mock.transfer(
                BigDecimal.valueOf(500),
            new User(Long.getLong("1"), "User01", new Account(Long.getLong("1000"), CURRENT_ACCOUNT, BigDecimal.valueOf(1000.00), false)),
            new User(Long.getLong("1"), "User01", new Account(Long.getLong("1000"), CURRENT_ACCOUNT, BigDecimal.valueOf(1000.00), false))
        );

        verify(mock, times(1)).transfer(
            ArgumentCaptor.forClass(BigDecimal.class).capture(),
            ArgumentCaptor.forClass(User.class).capture(),
            ArgumentCaptor.forClass(User.class).capture()
        );
    }

    @Test
    void methodTransferNotBeExecutedUnsuccessfully() {
        AccountService mock = spy(AccountService.class);

        verify(mock, never()).transfer(
            ArgumentCaptor.forClass(BigDecimal.class).capture(),
            ArgumentCaptor.forClass(User.class).capture(),
            ArgumentCaptor.forClass(User.class).capture()
        );
    }
    @Test
    void methodTransferShouldReturnExpectedResult() {
        AccountService mock = spy(AccountService.class);

        BigDecimal response = mock.transfer(
                BigDecimal.valueOf(500),
            new User(Long.getLong("1"), "User01", new Account(Long.getLong("1000"), CURRENT_ACCOUNT, BigDecimal.valueOf(1000.00), false)),
            new User(Long.getLong("1"), "User01", new Account(Long.getLong("1000"), CURRENT_ACCOUNT, BigDecimal.valueOf(1000.00), false))
        );

        Assertions.assertEquals(new ExpectedResult("transfer", BigDecimal.valueOf(500.0)).getResult(), response);
    }

    @Test
    void methodDepositShouldBeExecutedSuccessfully() {
        AccountService mock = spy(AccountService.class);

        mock.deposit(
            new User(Long.getLong("1"), "User01", new Account(Long.getLong("1000"), CURRENT_ACCOUNT, BigDecimal.valueOf(1000.00), false)),
                BigDecimal.valueOf(500)
        );

        verify(mock, times(1)).deposit(
            ArgumentCaptor.forClass(User.class).capture(),
            ArgumentCaptor.forClass(BigDecimal.class).capture()
        );
    }

    @Test
    void methodDepositNotBeExecutedUnsuccessfully() {
        AccountService mock = spy(AccountService.class);

        verify(mock, never()).deposit(
            ArgumentCaptor.forClass(User.class).capture(),
            ArgumentCaptor.forClass(BigDecimal.class).capture()
        );
    }
    @Test
    void methodDepositShouldReturnExpectedResult() {
        AccountService mock = spy(AccountService.class);

        BigDecimal response = mock.deposit(
            new User(Long.getLong("1"), "User01", new Account(Long.getLong("1000"), CURRENT_ACCOUNT, BigDecimal.valueOf(1000.00), false)),
                BigDecimal.valueOf(500)
        );

        Assertions.assertEquals(new ExpectedResult("deposit", BigDecimal.valueOf(1500.0)).getResult(), response);
    }

    @Test
    void methodCheckBalanceShouldBeExecutedSuccessfully() {
        AccountService mock = spy(AccountService.class);

        mock.checkBalance(
            new User(Long.getLong("1"), "User01", new Account(Long.getLong("1000"), CURRENT_ACCOUNT, BigDecimal.valueOf(1000.00), false))
        );

        verify(mock, times(1)).checkBalance(
            ArgumentCaptor.forClass(User.class).capture()
        );
    }

    @Test
    void methodCheckBalanceNotBeExecutedUnsuccessfully() {
        AccountService mock = spy(AccountService.class);

        verify(mock, never()).checkBalance(
            ArgumentCaptor.forClass(User.class).capture()
        );
    }
    @Test
    void methodCheckBalanceShouldReturnExpectedResult() {
        AccountService mock = spy(AccountService.class);

        BigDecimal response = mock.checkBalance(
            new User(Long.getLong("1"), "User01", new Account(Long.getLong("1000"), CURRENT_ACCOUNT, BigDecimal.valueOf(1000.00), false))
        );

        Assertions.assertEquals(new ExpectedResult("checkBalance", BigDecimal.valueOf(1000.0)).getResult(), response);
    }


}

