/* Group 1: Coding PROSSS HEHE
 * Name: Parejas, Arron Kian M.
 *       Tongol, Jenica Sarah
 *       Bermudo, Jeanne
 *       Baguio, Eriel
 */
class CreditAccount extends Account {
    protected double creditLimit;

    CreditAccount(String accountNumber, String accountHolder, double balance, double creditLimit) {
        super(accountNumber, accountHolder, balance, "Credit"); // Set account type
        this.creditLimit = creditLimit;
    }

    double getAvailableCredit() {
        return creditLimit - balance; // Assuming balance is negative for credit accounts
    }
}
