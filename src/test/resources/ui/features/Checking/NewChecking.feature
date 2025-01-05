Feature: Creating a new checking account

  Scenario: Create a standard individual checking account
    Given the user is logged in as "astrid@gmail.com" "Sdetme2025"
    When the user creates a new checking account with the following data
      | checkingAccountType | accountOwnership | accountName              | initialDepositAmount |
      | Standard Checking   | Individual       | Astrid K Second Checking | 100000.00            |
    Then the user should see the green "Successfully created new Standard Checking account named Astrid K Second Checking" message
    And the user should see newly added account cart
      | accountName              | accountType       | ownership  | accountNumber | interestRate | balance   |
      | Astrid K Second Checking | Standard Checking | Individual | 486140468     | 0.0%         | 100000.00 |
    And the user should see the following transactions
      | date             | category | description               | amount    | balance   |
      | 2024-11-30 19:24 | Income   | 845336316 (DPT) - Deposit | 100000.00 | 100000.00 |





