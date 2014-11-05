Feature: Fuzzing Input
  @announce
  Scenario: Randomized input in unexpected format
    When I start classic chess
    When I enter completely random input
    When I type "exit"
    And the output should not contain "Exception"

  Scenario: Randomized input in an expected format
    When I start classic chess
    When I enter random input in the correct format
    When I type "exit"
    And the output should not contain "Exception"

  Scenario: Randomized coordinates
    When I start classic chess
    When I enter random coordinates
    When I type "exit"
    And the output should not contain "Exception"

  Scenario: Randomized coordinates with garbage included
    When I start classic chess
    When I enter random coordinates with garbage
    When I type "exit"
    And the output should not contain "Exception"  