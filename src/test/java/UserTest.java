class UserTest {

   private AccountService AccountService; 

   @BeforeEach
   void setUp() {
        this.AccountService = new AccountService(); 
   }

   @Test
   void shouldInstantiateUser() {
        Account currentAccount = new Account(1000L, AccountType.CURRENT_ACCOUNT, new BigDecimal("1000.00"), false);
        User user01 = new User(1L, "User01", currentAccount);
        assertEquals(user01.getName(), "User01");
   }
}