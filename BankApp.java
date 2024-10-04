/* Group 1: Coding PROSSS HEHE
 * Name: Parejas, Arron Kian M.
 *       Tongol, Jenica Sarah
 *       Bermudo, Jeanne
 *       Baguio, Eriel
 */
import java.util.ArrayList;
import javax.swing.*;

// Main Bank Application Class
class BankApp {
    static ArrayList<Account> accounts = new ArrayList<>();
    static ArrayList<Loan> loans = new ArrayList<>();

    public static void main(String[] args) {
        // Create pre-existing accounts
        createPreExistingAccounts();

        // Main frame setup
        JFrame frame = new JFrame("Bank Account Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(null, "CS-202 Bank Account Management System");

        // Buttons
        JButton createAccountBtn = new JButton("Create Account");
        JButton viewAccountsBtn = new JButton("View All Accounts");
        JButton checkBalanceBtn = new JButton("Check Balance");
        JButton applyInterestBtn = new JButton("Apply 2% Interest (Savings)");
        JButton creditCheckBtn = new JButton("Credit Check");
        JButton manageLoansBtn = new JButton("Manage Loans");

        // Action Listeners
        createAccountBtn.addActionListener(e -> createAccount());
        viewAccountsBtn.addActionListener(e -> viewAccounts());
        checkBalanceBtn.addActionListener(e -> checkBalance());
        applyInterestBtn.addActionListener(e -> applyInterest());
        creditCheckBtn.addActionListener(e -> creditCheck());
        manageLoansBtn.addActionListener(e -> manageLoans());

        // Add buttons to a panel
        JPanel panel = new JPanel();
        panel.add(createAccountBtn);
        panel.add(viewAccountsBtn);
        panel.add(checkBalanceBtn);
        panel.add(applyInterestBtn);
        panel.add(creditCheckBtn);
        panel.add(manageLoansBtn);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }

    // Method to create pre-existing accounts
    protected static void createPreExistingAccounts() {
        try {
            // Creating sample accounts
            accounts.add(new SavingsAccount("001", "Arron Parejas", 1500.00, 0.02));
            accounts.add(new CheckingAccount("002", "Jenica Tongol", 2000.00, 500.00));
            accounts.add(new CreditAccount("003", "Jeanne Bermudo", 1000.00, 2000.00));
            accounts.add(new SavingsAccount("004", "Eriel Bagiuo", 2500.00, 0.02));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error creating pre-existing accounts: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to create an account
    public static void createAccount() {
        String[] options = {"Savings", "Checking", "Credit"};
        String accountType = (String) JOptionPane.showInputDialog(null, "Select Account Type", "Account Type",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String accountNumber = JOptionPane.showInputDialog("Enter Account Number:");
        String accountHolder = JOptionPane.showInputDialog("Enter Account Holder's Name:");
        double initialBalance = getValidDoubleInput("Enter Initial Balance:");

        if (accountType != null && accountNumber != null && accountHolder != null) {
            try {
                if ("Savings".equals(accountType)) {
                    double interestRate = 0.02;  // 2% interest rate
                    SavingsAccount savingsAccount = new SavingsAccount(accountNumber, accountHolder, initialBalance, interestRate);
                    accounts.add(savingsAccount);
                    JOptionPane.showMessageDialog(null, "Savings Account Created Successfully!");
                } else if ("Checking".equals(accountType)) {
                    double overdraftLimit = getValidDoubleInput("Enter Overdraft Limit:");
                    CheckingAccount checkingAccount = new CheckingAccount(accountNumber, accountHolder, initialBalance, overdraftLimit);
                    accounts.add(checkingAccount);
                    JOptionPane.showMessageDialog(null, "Checking Account Created Successfully!");
                } else if ("Credit".equals(accountType)) {
                    double creditLimit = getValidDoubleInput("Enter Credit Limit:");
                    CreditAccount creditAccount = new CreditAccount(accountNumber, accountHolder, initialBalance, creditLimit);
                    accounts.add(creditAccount);
                    JOptionPane.showMessageDialog(null, "Credit Account Created Successfully!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Account creation cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Method to view all accounts
    public static void viewAccounts() {
        if (accounts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No accounts available.", "Accounts", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Account account : accounts) {
            sb.append(account.toString()).append("\n\n"); // Added extra line for better readability
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "All Accounts", JOptionPane.INFORMATION_MESSAGE);
    }
    

    // Method to check balance of a specific account
    public static void checkBalance() {
        String accountNumber = JOptionPane.showInputDialog("Enter Account Number:");
        if (accountNumber != null) {
            for (Account account : accounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    JOptionPane.showMessageDialog(null, "Balance: $" + account.getBalance(), "Account Balance", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Account number input cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Method to apply 2% interest to savings accounts
    public static void applyInterest() {
        boolean interestApplied = false;
        for (Account account : accounts) {
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).applyInterest();
                interestApplied = true;
            }
        }
        if (interestApplied) {
            JOptionPane.showMessageDialog(null, "2% interest applied to all savings accounts.", "Interest Applied", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No savings accounts found.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Method to check available credit in credit accounts
    public static void creditCheck() {
        String accountNumber = JOptionPane.showInputDialog("Enter Account Number:");
        if (accountNumber != null) {
            for (Account account : accounts) {
                if (account instanceof CreditAccount && account.getAccountNumber().equals(accountNumber)) {
                    CreditAccount creditAccount = (CreditAccount) account;
                    JOptionPane.showMessageDialog(null, "Available Credit: $" + creditAccount.getAvailableCredit(), "Credit Check", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Account number input cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, "Credit account not found!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Manage Loans with Exception Handling
    public static void manageLoans() {
        String[] loanOptions = {"Take Loan", "View Loan", "Make Repayment"};
        String loanAction = (String) JOptionPane.showInputDialog(null, "Select Loan Action", "Loan Management",
                JOptionPane.QUESTION_MESSAGE, null, loanOptions, loanOptions[0]);

        try {
            if ("Take Loan".equals(loanAction)) {
                takeLoan();
            } else if ("View Loan".equals(loanAction)) {
                viewLoan();
            } else if ("Make Repayment".equals(loanAction)) {
                makeRepayment();
            }
        } catch (InsufficientFundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidInputException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void takeLoan() {
        String loanId = JOptionPane.showInputDialog("Enter Loan ID:");
        String borrowerName = JOptionPane.showInputDialog("Enter Borrower's Name:");
        double loanAmount = getValidDoubleInput("Enter Loan Amount:");
        double interestRate = getValidDoubleInput("Enter Interest Rate (%):") / 100;

        if (loanId != null && borrowerName != null) {
            Loan loan = new Loan(loanId, borrowerName, loanAmount, interestRate);
            loans.add(loan);
            JOptionPane.showMessageDialog(null, "Loan Created Successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Loan creation cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void viewLoan() {
        if (loans.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No loans available.", "Loans", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Loan loan : loans) {
            sb.append(loan.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "All Loans", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void makeRepayment() {
        String loanId = JOptionPane.showInputDialog("Enter Loan ID:");
        if (loanId != null && !loanId.trim().isEmpty()) {
            double repaymentAmount = getValidDoubleInput("Enter Repayment Amount:");
            
            if (repaymentAmount <= 0) {
                JOptionPane.showMessageDialog(null, "Repayment amount must be positive!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            boolean loanFound = false;
            for (Loan loan : loans) {
                if (loan.getLoanId().equals(loanId)) {
                    loan.makeRepayment(repaymentAmount);
                    loanFound = true;
                    JOptionPane.showMessageDialog(null, "Repayment Successful!\nRemaining Balance: $" + loan.getRemainingBalance(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
    
            if (!loanFound) {
                JOptionPane.showMessageDialog(null, "Loan not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Loan ID input cancelled or empty.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    

    protected static double getValidDoubleInput(String message) {
        double value = 0.0;
        boolean validInput = false;
        while (!validInput) {
            String input = JOptionPane.showInputDialog(message);
            if (input != null) {
                try {
                    value = Double.parseDouble(input);
                    validInput = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Input cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
        return value;
    }
}