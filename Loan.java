/* Group 1: Coding PROSSS HEHE
 * Name: Parejas, Arron Kian M.
 *       Tongol, Jenica Sarah
 *       Bermudo, Jeanne
 *       Baguio, Eriel
 */
class Loan {
    protected String loanId;   // ID of the loan
    protected String borrowerName;  // Name of the borrower
    protected double principal; // Initial amount of the loan
    protected double interestRate; // Interest rate of the loan
    protected double balance;  // Current balance of the loan

    // Constructor with the required parameters
    Loan(String loanId, String borrowerName, double principal, double interestRate) {
        this.loanId = loanId;
        this.borrowerName = borrowerName;
        this.principal = principal;
        this.interestRate = interestRate;
        this.balance = principal; // Initialize balance to the principal amount
    }

    // Getter for loanId
    String getLoanId() {
        return loanId;
    }

    // Method to make a repayment
    void makeRepayment(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount; // Deduct repayment amount from balance
        } else {
            // Handle cases where repayment amount is invalid
            System.out.println("Invalid repayment amount. Please enter a valid amount.");
        }
    }

    // Getter for remaining balance
    double getRemainingBalance() {
        return balance; // Return the current balance
    }
}
