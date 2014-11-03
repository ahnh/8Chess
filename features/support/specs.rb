Given(/^I start classic chess$/) do
  run_interactive("java -cp bin/ Controller.GameController")
  wait_for_output_to_contain "Text"
  type("1")
  wait_for_output_to_contain "Classic"
  type("1")
  wait_for_output_to_contain "p p p p p p p p"
      # And I wait for output to contain "Classic."
      # Then I type "1"
      # And I wait for output to contain "p p p p p p p p"
end

def wait_for_output_to_contain(expected)
  Timeout::timeout(exit_timeout) do
    loop do
      break if assert_partial_output_interactive(expected)
      sleep 0.01
    end
  end
end