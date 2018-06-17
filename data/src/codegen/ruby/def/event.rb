
require_relative 'field'

class EventDef
  attr_reader :name, :fields
  
  def initialize(name, fields)
    @name = name
    @fields = fields
  end
  
  def write(package)
    dir = File.join(TARGET_DIR, File.join(package.split('.')))
    
    FileUtils.mkdir_p dir
    
    File.open(File.join(dir, "#{name}Data.java"), "w") do |f|
      f.puts "package #{package};"
      f.puts
      
      %w(
        com.fasterxml.jackson.annotation.JsonProperty
        com.fasterxml.jackson.databind.annotation.JsonDeserialize
        com.github.princesslana.eriscasper.data.Snowflake
        com.github.princesslana.eriscasper.data.resource.*
        com.google.common.collect.ImmutableList
        org.immutables.value.Value
      ).each do |import|
        f.puts "import #{import};"
      end
      
      f.puts
      f.puts "@Value.Immutable"
      f.puts "@JsonDeserialize(as=Immutable#{name}Data.class)"
      f.puts "public interface #{name}Data {"
      
      fields.each { |field| field.write(f) }
      
      f.puts "}"    
    end
  end

  def self.from_file(filename)
    fields = []

    File.open(filename).each do |line|
      fields.push FieldDef.from_line(line)
    end
  
    EventDef.new File.basename(filename).split('_').collect(&:capitalize).join + "Event", fields
  end
end