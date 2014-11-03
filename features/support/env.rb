require 'aruba'
require 'aruba/cucumber'

Before do
  @dirs = ["./"]
end

Before('@slow_process') do
  @aruba_io_wait_seconds = 5
  @aruba_timeout_seconds = 10
end

Before do |scenario|
  # The +scenario+ argument is optional, but if you use it, you can get the title,
  # description, or name (title + description) of the scenario that is about to be
  # executed.
  puts "Starting scenario: #{scenario.title}"
end