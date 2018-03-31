require 'fileutils'

RESOURCES_DIR = File.join %w(. src codegen resources)
TARGET_DIR = File.join %w(. target generated-sources data)

BASE_PACKAGE = 'com.github.princesslana.eriscasper.data'

FileUtils.mkdir_p TARGET_DIR

event_files = Dir["#{RESOURCES_DIR}/event/*"]

ClassDef = Struct.new :name, :package, :fields

FieldDef = Struct.new :name, :type, :description do
  def java_name
    "get" + name.split('_').collect(&:capitalize).join
  end
  
  def java_type
  {
    'integer' => 'long',
    'array of strings' => 'com.google.common.collect.ImmutableList<String>'
    
  }[type]
  end
end

event_classes = event_files.map do |f|
  fields = []

  File.open(f).each do |line|
    fields.push FieldDef.new(*line.split("\t").map(&:strip))
  end

  ClassDef.new File.basename(f).capitalize + "Event", "#{BASE_PACKAGE}.event", fields
end

event_classes.each do |clz|
  puts "Generating #{clz.package}.#{clz.name}..."
  
  dir = File.join(TARGET_DIR, File.join(clz.package.split('.')))
  
  FileUtils.mkdir_p dir
  
  File.open(File.join(dir, "#{clz.name}.java"), "w") do |f|
    f.puts "package #{clz.package};"
    f.puts
    
    %w(
      com.fasterxml.jackson.annotation.JsonProperty
      com.fasterxml.jackson.databind.annotation.JsonDeserialize
      org.immutables.value.Value
    ).each do |import|
      f.puts "import #{import};"
    end
    
    f.puts
    f.puts '@Value.Immutable'
    f.puts "@JsonDeserialize(as=Immutable#{clz.name}.class)"
    f.puts "public interface #{clz.name} {"
    
    clz.fields.each do |field|
    
    f.puts "  @JsonProperty(\"#{field.name}\")"
    f.puts "  #{field.java_type} #{field.java_name}();"
    f.puts
    end
    
    f.puts "}"
  end
end

puts "Done."




