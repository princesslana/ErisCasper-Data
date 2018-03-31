
class FieldDef
  attr_reader :name, :type, :description
  
  def initialize(name, type, *description)
    @name = name
    @type = type
    @description = description.join
  end
  
  def optional?
    # Lists are alreay optional - as the empty list
    return false if type.include? 'array'
    
    type.start_with?('?') || name.end_with?('?')
  end
  
  def java_name
    "get" + name.tr('?', '').split('_').collect(&:capitalize).join
  end
  
  def java_type
    jt = get_java_type type.tr('?', '')
    
    return "java.util.Optional<#{jt}>" if optional?
    
    jt
  end
  
  def json_property_annotation
    property_name = name.tr '?', ''
    
    "@JsonProperty(\"#{property_name}\")"
  end
  
  def write(f)
    f.puts "  /**"
    f.puts "    * #{description}"
    f.puts "    */"
    f.puts "  #{json_property_annotation}"
    f.puts "  #{java_type} #{java_name}();"
    f.puts
  end
  
  def self.from_line(line)
    FieldDef.new *line.split("\t").map(&:strip)
  end
end