
require_relative 'field'

class ResourceDef
  attr_reader :name, :fields
  
  def initialize(name, fields)
    @name = name
    @fields = fields
  end
  
  def write(package)
    dir = File.join(TARGET_DIR, File.join(package.split('.')))
    
    FileUtils.mkdir_p dir
    
    File.open(File.join(dir, "#{name}.java"), "w") do |f|
      f.puts "package #{package};"
      f.puts
      
      %w(
        com.fasterxml.jackson.annotation.JsonProperty
        com.fasterxml.jackson.databind.annotation.JsonDeserialize
        com.github.princesslana.eriscasper.data.Snowflake
        com.google.common.collect.ImmutableList
        org.immutables.value.Value
      ).each do |import|
        f.puts "import #{import};"
      end
      
      f.puts
      f.puts "@Value.Immutable"
      f.puts "@JsonDeserialize(as=Immutable#{name}.class)"
      f.puts "public interface #{name} {"
      
      fields.each { |field| field.write(f) }
      
      f.puts "}"
    end
  end

  def self.from_file(filename)
    fields = []

    File.open(filename).each do |line|
      fields.push FieldDef.from_line(line)
    end
  
    ResourceDef.new File.basename(filename).split('_').collect(&:capitalize).join + "Resource", fields
  end
end