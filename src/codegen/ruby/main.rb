require 'fileutils'

require_relative 'java_types'
require_relative 'def/event'

RESOURCES_DIR = File.join %w(. src codegen resources)
TARGET_DIR = File.join %w(. target generated-sources data)

BASE_PACKAGE = 'com.github.princesslana.eriscasper.data'

FileUtils.mkdir_p TARGET_DIR

event_files = Dir["#{RESOURCES_DIR}/event/*"]



event_classes = event_files.map do |f|
  EventDef.from_file f
end

package = BASE_PACKAGE + ".event"
event_classes.each do |clz|
  puts "Generating #{package}.#{clz.name}..."
  clz.write package
end
  
puts "Done."




