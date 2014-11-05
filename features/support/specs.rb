require 'fuzzbert'

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

Given(/^I make a move "(.*?)"$/) do |move|
  type(move)
  wait_for_output_to_contain "move"
end

When(/^I enter completely random input$/) do
  type(random_data)
end

When (/^I randomize coordinates$/) do
  type(random_coordinates)
end

def wait_for_output_to_contain(expected)
  Timeout::timeout(exit_timeout) do
    loop do
      break if assert_partial_output_interactive(expected)
      sleep 0.01
    end
  end
end

def random_data
  FuzzBert::Generators.random.yield + "\n"
end

def random_coordinates
  "TODO"
end