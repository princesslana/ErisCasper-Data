
class FieldDef
  attr_reader :name, :type, :description
  
  def initialize(name, type, description)
    @name = name
    @type = type
    @description = description
  end
  
  def java_name
    "get" + name.split('_').collect(&:capitalize).join
  end
  
  def java_type
    get_java_type(type)
  end
  
  def write(f)
    f.puts "  /**"
    f.puts "    * #{description}"
    f.puts "    */"
    f.puts "  @JsonProperty(\"#{name}\")"
    f.puts "  #{java_type} #{java_name}();"
    f.puts
  end
  
  def self.from_line(line)
    FieldDef.new *line.split("\t").map(&:strip)
  end
end