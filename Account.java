/* Group 1: Coding PROSSS HEHE
 * Name: Parejas, Arron Kian M.
 *       Tongol, Jenica Sarah
 *       Bermudo, Jeanne
 *       Baguio, Eriel
 */

// Abstract Account Class
abstract class Account {
    protected String accountNumber;
    protected String accountHolder;
    protected double balance;
    protected String accountType; // Add account type field

    Account(String accountNumber, String accountHolder, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.accountType = accountType; // Initialize account type
    }

    String getAccountNumber() { // Return the account number (Geteerss)
        return accountNumber;
    }

    double getBalance() {
        return balance;
    }

    // Override toString() to include account type
    @Override
    public String toString() {// OVERRIDE RAWR
        return String.format("Account Type: %s\nAccount Number: %s\nAccount Holder: %s\nBalance: $%.2f",
                accountType, accountNumber, accountHolder, balance);
    }
}
