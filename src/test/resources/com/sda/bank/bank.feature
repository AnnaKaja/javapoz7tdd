Feature: Bank functionality
  Scenario: I can add user to bank
    Given I instantiate bank
    And I create user with name 'Szymon Nowak' and pesel '123456789'
    When I insert user to bank
    Then User is present in bank

  Scenario: I can not duplicate users in bank
    Given I instantiate bank
    And I create user with name 'Szymon Nowak' and pesel '123456789'
    When I insert user to bank
    And I insert user to bank
    Then User is not present in bank

  Scenario: I can create account for existing user
    Given I instantiate bank
    And I create user with name 'Jan Kowalski' and pesel '543216789'
    And I create account with name 'konto oszczednosciowe'
    When I insert user to bank
    And I insert account to bank
    Then Account is present in bank

  Scenario: I can not create account for non existing user in bank
    Given I instantiate bank
    And I create user with name 'Jan Kowalski' and pesel '543216789'
    And I create account with name 'konto codzienne'
    When I insert account to bank
    Then Account is not present in bank

  Scenario: I cannot create another account with same name for existing user in bank
    Given I instantiate bank
    And I create user with name 'John Doe' and pesel '789437584'
    And I create account with name 'konto z unikalna nazwa'
    When I insert user to bank
    And I insert account to bank
    And I insert account to bank
    Then User has only '1' account in bank





