import org.account.Account;
import org.account.AccountService;
import org.account.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.user.User;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserTest {

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        this.accountService = new AccountService();
    }

    @Test
    void shouldInstantiateUser() {
        User user = new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT, new BigDecimal("1000.00"), false));
        assertNotEquals(null, user);
    }

    @Test
    void methodShouldBeExecutedSuccessfully() {
        AccountService mock = spy(AccountService.class);

        mock.deposit(new User(1L, "User01", new Account(1000L, AccountType.CURRENT_ACCOUNT,
        new BigDecimal("1000.00"), false)), new BigDecimal("500.00"));

        verify(mock, times(1)).deposit(ArgumentCaptor.forClass(User.class).capture(),
        ArgumentCaptor.forClass(BigDecimal.class).capture());

        Method[] methods = mock.getClass().getMethods();

        String methodName = "deposit";

        Method method = Arrays.stream(methods)
        .filter(m -> m.getName().equals(methodName))
        .findFirst()
        .orElseThrow(() -> new NoSuchMethodError(methodName));

        List<String> parametersTypes = Arrays.stream(method.getParameters()).map(Parameter::getType).map(Class::getName).toList();

        System.out.println(parametersTypes);

    }
}

