require 'fileutils'

require_relative 'java_types'
require_relative 'def/event'
require_relative 'def/resource'

RESOURCES_DIR = File.join %w(. src codegen resources)
TARGET_DIR = File.join %w(. target generated-sources data)

BASE_PACKAGE = 'com.github.princesslana.eriscasper.data'

FileUtils.mkdir_p TARGET_DIR

event_files = Dir["#{RESOURCES_DIR}/event/*"]
resource_files = Dir["#{RESOURCES_DIR}/resource/*"]

def generate(files, x_def, package)
  classes = files.map { |f| x_def.from_file f }
  
  package = BASE_PACKAGE + "." + package
  
  classes.each do |clz|
    puts "Generating #{package}.#{clz.name}..."
    clz.write package
  end
end

generate(resource_files, ResourceDef, 'resource')
generate(event_files, EventDef, 'event')

puts "Done."




