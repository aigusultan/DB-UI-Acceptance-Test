Feature: Link External Account Functionality

  Background:
    Given the user registers a new account as follows with mock email and ssn:
      | title | firstName | lastName | gender | dateOfBirth | ssn    | email  | password  | address | locality | region | postalCode | country | homePhone  |
      | Mr.   | Peter     | Kale     | M      | 11/05/1994  | random | random | Hello123! | Main st | LA       | CA     | 90808      | US      | 3607857533 |
    And the user signs in with their credentials

  @VisaTransfer
    # 19.5.1 Broken functionality 1
  Scenario: Positive - Error
    When the user wants to link an external account
    Then the user is displayed with an error "There are no banking providers available." message
    Given the existing field "Open Banking Providers" doesn't have any options
    When the user click on the Submit button
    Then the user sees the "Please select an item in the list." error message



