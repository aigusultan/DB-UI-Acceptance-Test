Feature: Create Account Test Scenarios

  @DB
  Scenario: Create a valid account
    Given The user with "vanilla@french.com" is not in DB
    And the following user is in the db
      | title | firstName | lastName | gender | dob        | ssn         | emailAddress       | password | address    | locality | region | postalCode | country | homePhone    | mobilePhone | workPhone |
      | Mrs.  | Vanilla   | Bean     | F      | 02/02/1999 | 123-56-5544 | vanilla@french.com | Test123! | 1 Sweet St | CA       | CA     | 123945     | USA     | 123-554-5566 |             |           |

    When the following account is created
      | accountName                      | accountTypeCode | openingDeposit | ownerTypeCode |
      | Vanilla's Test Standard Checking | SCK             | 25000.00       | IND           |

    #some json files that you will validate are huge: 50 items or more
    #in these cases you will need to only include the validation of dynamic details
    #into your feature file text

    #in this account example account name, current balance are dynamic and will vary depending on
    #the type of the account (SCK, IND joint, savings) etc, so these variables need to me validated
    #not the static ones like opening valance.
    #But we have to validate static details as well, those will be done ibn steps
    #keep your feature files clean only including the dynamic details in the actual text of your feature file
    #however all info i actually getting validated in the back
    #SDETs need to validate every single piece of information
    Then the following account details are returned in the response
      | accountName                      | accountTypeCode | openingDeposit | ownerTypeCode | accountStandingName |
      | Vanilla's Test Standard Checking | SCK             | 25000.00       | IND           | Open                |

    #Then the following account details are saved in the db


  Scenario: Create an account with wrong account name

  Scenario: Create an account with wrong account type code

  Scenario: Create an account with wrong opening deposit

  Scenario: Create an account with wrong owner type code