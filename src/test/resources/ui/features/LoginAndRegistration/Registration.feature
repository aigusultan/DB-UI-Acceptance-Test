@Registration
Feature: Digital Bank Registration Page

  Background:
    Given The user with "jack@user.com" is not in DB
    And User navigates to Digital Bank signup page

  @Test
    @PositiveRegistrationCases
  Scenario Outline: Positive Case. As a user, I want to successfully create a Digital Bank account

    When User creates account with the following fields
      | title   | firstName   | lastName   | gender   | dob   | ssn   | email   | password   | confirmPassword   | address   | locality   | region   | postalCode   | country   | homePhone   | mobilePhone   | workPhone   | termsCheckMark   | accountNonExpired   | accountNonLocked   | credentialsNonExpired   | enabled   |
      | <title> | <firstName> | <lastName> | <gender> | <dob> | <ssn> | <email> | <password> | <confirmPassword> | <address> | <locality> | <region> | <postalCode> | <country> | <homePhone> | <mobilePhone> | <workPhone> | <termsCheckMark> | <accountNonExpired> | <accountNonLocked> | <credentialsNonExpired> | <enabled> |

    Then User should be displayed with the message "Registration Successful. Please Login."
    Then the following user info should be save in the db
      | title   | firstName   | lastName   | gender   | dob   | ssn   | email   | password   | confirmPassword   | address   | locality   | region   | postalCode   | country   | homePhone   | mobilePhone   | workPhone   | termsCheckMark   | accountNonExpired   | accountNonLocked   | credentialsNonExpired   | enabled   |
      | <title> | <firstName> | <lastName> | <gender> | <dob> | <ssn> | <email> | <password> | <confirmPassword> | <address> | <locality> | <region> | <postalCode> | <country> | <homePhone> | <mobilePhone> | <workPhone> | <termsCheckMark> | <accountNonExpired> | <accountNonLocked> | <credentialsNonExpired> | <enabled> |

    Examples:
      | title | firstName | lastName | gender | dob        | ssn         | email         | password   | confirmPassword | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckMark | accountNonExpired | accountNonLocked | credentialsNonExpired | enabled |
      | Mr.   | Jack      | Test     | M      | 12/12/1998 | 123-44-5566 | jack@user.com | Tester123! | Tester123!      | 12 Main St | City     | CA     | 99921      | US      | 2161234566 | 6578907766  | 5467890033 | true           | true              | true             | true                  | true    |
      #| Mrs.  | Sara      | Star     | F      | 12/12/1988 | 123-66-4433 | sara@user.com | Tester123! | Tester123!      | 14 Main St | City     | CA     | 99021      | US      | 2161234567 | 6578907760  | 5467890003 | true           |


  @NegativeRegistrationCases
  Scenario Outline: Negative Test Cases. As a Digital Bank Admin I want to make sure users cannot register
  without providing all valid data.

    When User creates account with the following fields
      | title   | firstName   | lastName   | gender   | dob   | ssn   | email   | password   | confirmPassword   | address   | locality   | region   | postalCode   | country   | homePhone   | mobilePhone   | workPhone   | termsCheckMark   |
      | <title> | <firstName> | <lastName> | <gender> | <dob> | <ssn> | <email> | <password> | <confirmPassword> | <address> | <locality> | <region> | <postalCode> | <country> | <homePhone> | <mobilePhone> | <workPhone> | <termsCheckMark> |
    Then User should see the "<fieldWithError>" required field error message "<errorMessage>"

    Examples:
      | title | firstName | lastName | gender | dob        | ssn    | email  | password   | confirmPassword | address     | locality | region | postalCode | country | homePhone    | mobilePhone | workPhone | termsCheckMark | fieldWithError  | errorMessage                                  |
      |       |           |          |        |            |        |        |            |                 |             |          |        |            |         |              |             |           |                | title           | Please select an item in the list.            |
      | Mr.   |           |          |        |            |        |        |            |                 |             |          |        |            |         |              |             |           |                | firstName       | Please fill out this field.                   |
      | Mr.   | Charlie   |          |        |            |        |        |            |                 |             |          |        |            |         |              |             |           |                | lastName        | Please fill out this field.                   |
      | Mr.   | Charlie   | Dim      |        |            |        |        |            |                 |             |          |        |            |         |              |             |           |                | gender          | Please select one of these options.           |
      | Mr.   | Charlie   | Dim      | M      |            |        |        |            |                 |             |          |        |            |         |              |             |           |                | dob             | Please fill out this field.                   |
      | Mr.   | Charlie   | Dim      | M      | 21/12/2001 |        |        |            |                 |             |          |        |            |         |              |             |           |                | ssn             | Please fill out this field.                   |
      | Mr.   | Charlie   | Dim      | M      | 21/12/2001 | random |        |            |                 |             |          |        |            |         |              |             |           |                | email           | Please fill out this field.                   |
      | Mr.   | Charlie   | Dim      | M      | 21/12/2001 | random | random |            |                 |             |          |        |            |         |              |             |           |                | password        | Please fill out this field.                   |
      | Mr.   | Charlie   | Dim      | M      | 21/12/2001 | random | random | Tester123! |                 |             |          |        |            |         |              |             |           |                | confirmPassword | Passwords Do Not Match                        |
      | Mrs.  | Sara      | Star     | F      | 12/12/1988 | random | random | Tester123! | Tester123!      |             |          |        |            |         |              |             |           |                | address         | Please fill out this field.                   |
      | Mrs.  | Sara      | Star     | F      | 12/12/1988 | random | random | Tester123! | Tester123!      | 12 Main St. |          |        |            |         |              |             |           |                | locality        | Please fill out this field.                   |
      | Mrs.  | Sara      | Star     | F      | 12/12/1988 | random | random | Tester123! | Tester123!      | 12 Main St. | City     |        |            |         |              |             |           |                | region          | Please fill out this field.                   |
      | Mrs.  | Sara      | Star     | F      | 12/12/1988 | random | random | Tester123! | Tester123!      | 12 Main St. | City     | CA     |            |         |              |             |           |                | postalCode      | Please fill out this field.                   |
      | Mrs.  | Sara      | Star     | F      | 12/12/1988 | random | random | Tester123! | Tester123!      | 12 Main St. | City     | CA     | 11121      |         |              |             |           |                | country         | Please fill out this field.                   |
      | Mrs.  | Sara      | Star     | F      | 12/12/1988 | random | random | Tester123! | Tester123!      | 12 Main St. | City     | CA     | 11121      | USA     |              |             |           |                | homePhone       | Please fill out this field.                   |
      | Mrs.  | Sara      | Star     | F      | 12/12/1988 | random | random | Tester123! | Tester123!      | 12 Main St. | City     | CA     | 11121      | USA     | 123-234-5643 |             |           |                | termsCheckMark  | Please check this box if you want to proceed. |
