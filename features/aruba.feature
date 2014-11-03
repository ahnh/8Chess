Feature: exit statuses

  In order to specify expected exit statuses
  As a developer using Cucumber
  I want to use the "the exit status should be" step

  @slow_process
  Scenario: Starting a new game
    When I start classic chess
      Then the output should contain:
        """
        Enter your move, player 1.
        """
