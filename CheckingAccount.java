/* Group 1: Coding PROSSS HEHE
 * Name: Parejas, Arron Kian M.
 *       Tongol, Jenica Sarah
 *       Bermudo, Jeanne
 *       Baguio, Eriel
 */
class CheckingAccount extends Account {
    protected double overdraftLimit;

    CheckingAccount(String accountNumber, String accountHolder, double balance, double overdraftLimit) {
        super(accountNumber, accountHolder, balance, "Checking"); // Set account type
        this.overdraftLimit = overdraftLimit;
    }
}
