Feature:

  Scenario: I can calculate null number
    Given I initialize stringCalculator
    And I pass value null
    When I trigger calculate function
    Then I get 0 as a result

  Scenario Outline: I can calculate multiple values
    Given I initialize stringCalculator
    And I pass <value> value
    When I trigger calculate function
    Then I get <result> as a result
    Examples:
      | value         | result |
      | 5;4;2         | 11     |
      | 8;1           | 9      |
      | 5             | 5      |
      |               | 0      |
      | 5;4           | 9      |
      | 2;3;4         | 9      |
      | 2  ;3    ;  4 | 9      |