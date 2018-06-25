require 'fileutils'

require_relative 'event_types'
require_relative 'java_types'
require_relative 'def/event_factory'
require_relative 'def/event_wrapper'
require_relative 'def/resource'

RESOURCES_DIR = File.join %w(. src codegen resources)
TARGET_DIR = File.join %w(. target generated-sources data)

BASE_PACKAGE = 'com.github.princesslana.eriscasper.data'

FileUtils.mkdir_p TARGET_DIR

event_files = Dir["#{RESOURCES_DIR}/event/*"]
resource_files = Dir["#{RESOURCES_DIR}/resource/*"]
gateway_files = Dir["#{RESOURCES_DIR}/gateway/*"]

auditlog_files = Dir["#{RESOURCES_DIR}/rest/auditlog/*"]
channel_files = Dir["#{RESOURCES_DIR}/rest/channel/*"]
emoji_files = Dir["#{RESOURCES_DIR}/rest/emoji/*"]
guild_files = Dir["#{RESOURCES_DIR}/rest/guild/*"]
user_files = Dir["#{RESOURCES_DIR}/rest/user/*"]
webhook_files = Dir["#{RESOURCES_DIR}/rest/webhook/*"]

def generate(files, x_def, package, opts={})
  classes = files.map { |f| x_def.from_file f, opts }
  
  package = BASE_PACKAGE + "." + package
  
  classes.each do |clz|
    puts "Generating #{package}.#{clz.name}..."
    clz.write package
  end
end

def generate_rest(files, package)
    generate files, ResourceDef, package,
      suffix: 'Request',
      imports: ['com.github.princesslana.eriscasper.data.resource.*']
end

generate resource_files, ResourceDef, 'resource'

generate event_files, ResourceDef, 'event',
  suffix: 'EventData',
  imports: ['com.github.princesslana.eriscasper.data.resource.*']

generate gateway_files, ResourceDef, 'gateway',
  suffix: 'Payload',
  imports: ['com.github.princesslana.eriscasper.data.resource.*']

generate_rest auditlog_files, 'rest/auditlog'
generate_rest channel_files, 'rest/channel'
generate_rest emoji_files, 'rest/emoji'
generate_rest guild_files, 'rest/guild'
generate_rest user_files, 'rest/user'
generate_rest webhook_files, 'rest/webhook'

EVENTS.each do |evt, dat|
  name = evt.to_s.split('_').collect(&:capitalize).join + "Event"
  
  clz = EventWrapperDef.new name, dat
  
  puts "Generating #{BASE_PACKAGE}.event.#{clz.name}..."
  clz.write(BASE_PACKAGE + ".event")  
end

puts "Generating #{BASE_PACKAGE}.event.EventFactory..."
EventFactoryDef.new(EVENTS).write(BASE_PACKAGE + ".event")

puts "Done."




