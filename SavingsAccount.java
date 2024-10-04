/* Group 1: Coding PROSSS HEHE
 * Name: Parejas, Arron Kian M.
 *       Tongol, Jenica Sarah
 *       Bermudo, Jeanne
 *       Baguio, Eriel
 */
class SavingsAccount extends Account {
    protected double interestRate;

    SavingsAccount(String accountNumber, String accountHolder, double balance, double interestRate) {
        super(accountNumber, accountHolder, balance, "Savings"); // Set account type
        this.interestRate = interestRate;
    }

    void applyInterest() {
        balance += balance * interestRate;
    }
}
