Given(/^I start classic chess$/) do
  run_interactive("java -cp bin/ Controller.GameController")
  wait_for_output_to_contain "Text"
  type("1")
  wait_for_output_to_contain "Classic"
  type("1")
  wait_for_output_to_contain "p p p p p p p p"
end

Given(/^I make a move "(.*?)"$/) do |move|
  type(move)
  wait_for_output_to_contain "move"
end

When(/^I enter completely random input$/) do
  type(random_data)
end

When(/^I enter random input in the correct format$/) do
  type(random_data_formatted)
end

When(/^I enter random coordinates$/) do
  10.times { type(random_coordinates + "\n") }
end

When(/^I enter random coordinates with garbage$/) do
  10.times { type(garbage + random_coordinates + garbage) }
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

def random_data_formatted
  c = FuzzBert::Container.new
  c << FuzzBert::Generators.random_fixlen(2)
  c << FuzzBert::Generators.fixed("-")
  c << FuzzBert::Generators.random_fixlen(2)
  c.generator.yield  
end

def random_coordinates
  numbers = [*'0'..'9']
  letters = [*'A'..'Z']
  input = letters.sample + numbers.sample + "-" + letters.sample + numbers.sample
end

def garbage
  FuzzBert::Generators.random_fixlen([*0..9].sample).yield
end