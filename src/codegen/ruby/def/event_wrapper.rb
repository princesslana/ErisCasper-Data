require_relative 'field'

class EventWrapperDef
  attr_reader :name, :data
  
  def initialize(name, data)
    @name = name
    @data = data
  end
  
  def write(package)
    dir = File.join(TARGET_DIR, File.join(package.split('.')))
    
    FileUtils.mkdir_p dir
    
    File.open(File.join(dir, "#{name}Wrapper.java"), "w") do |f|
      f.puts "package #{package};"
      f.puts
      
      %w(
        com.fasterxml.jackson.databind.annotation.JsonDeserialize
        com.github.princesslana.eriscasper.data.immutable.Wrapped
        com.github.princesslana.eriscasper.data.immutable.Wrapper
        org.immutables.value.Value
      ).each do |import|
        f.puts "import #{import};"
      end
      
      f.puts
      f.puts "@Value.Immutable"
      f.puts "@Wrapped"
      f.puts "@JsonDeserialize(as=#{name}.class)"
      f.puts "public interface #{name}Wrapper extends Event, Wrapper<#{data}> { }"
    end
  end
end

