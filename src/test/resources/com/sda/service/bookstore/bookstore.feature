Feature: Test cases for bookstore

  Scenario:I can add a book to bookstore
    Given I instantiate bookstore
    And I create book
    When I add book to bookstore
    Then Book is present in bookstore

  Scenario:I can edit title from bookstore
    Given I instantiate bookstore
    And I create book
    When I add book to bookstore
    And I edit title of book
    Then Book's title has changed