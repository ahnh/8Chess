Feature: Input Formats

  Testing various valid input formats to ensure that moves are still made

  @slow_process
  Scenario: Making a valid move
    When I start classic chess
    When I type "A7-A6"
    When I type "exit"
    Then the output should contain:
      """
         A B C D E F G H
      1  R N B Q K B N R
      2  P P P P P P P P
      3  _ _ _ _ _ _ _ _
      4  _ _ _ _ _ _ _ _
      5  _ _ _ _ _ _ _ _
      6  p _ _ _ _ _ _ _
      7  _ p p p p p p p
      8  r n b q k b n r
      """
    And the output should not contain:
      """
      Exception
      """
  Scenario: Valid Move Formats
    When I start classic chess
    When I type "        A7     - A6"
    When I type "exit"
    Then the output should contain:
      """
         A B C D E F G H
      1  R N B Q K B N R
      2  P P P P P P P P
      3  _ _ _ _ _ _ _ _
      4  _ _ _ _ _ _ _ _
      5  _ _ _ _ _ _ _ _
      6  p _ _ _ _ _ _ _
      7  _ p p p p p p p
      8  r n b q k b n r
      """
    And the output should not contain:
      """
      Exception
      """

  Scenario: Sample
    When I start classic chess
    When I type "A7-A6"
    When I type "A2-A3"
    When I type "A6-A5"
    When I type "A3-A4"
    When I type "exit"
    And I close the stdin stream
    Then the output should contain:
      """
         A B C D E F G H
      1  R N B Q K B N R
      2  P P P P P P P P
      3  _ _ _ _ _ _ _ _
      4  _ _ _ _ _ _ _ _
      5  _ _ _ _ _ _ _ _
      6  p _ _ _ _ _ _ _
      7  _ p p p p p p p
      8  r n b q k b n r
      """
    And the output should contain:
      """
         A B C D E F G H
      1  R N B Q K B N R
      2  _ P P P P P P P
      3  P _ _ _ _ _ _ _
      4  _ _ _ _ _ _ _ _
      5  _ _ _ _ _ _ _ _
      6  p _ _ _ _ _ _ _
      7  _ p p p p p p p
      8  r n b q k b n r
      """
    And the output should not contain:
      """
      Exception
      """