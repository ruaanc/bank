import org.account.Account;
import org.account.AccountService;
import org.account.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.user.User;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GenerateTest {

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        this.accountService = new AccountService();
    }

    @Test
    void shouldInstantiateUser() {
        Account currentAccount = new Account(1000L, AccountType.CURRENT_ACCOUNT, new BigDecimal("1000.00"), false);
        User user01 = new User(1L, "User01", currentAccount);
        assertEquals(user01.getName(), "User01");
    }
}

