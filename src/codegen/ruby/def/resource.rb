
require_relative 'field'

class ResourceDef
  attr_reader :name, :fields, :imports, :query_string
  
  def initialize(name, fields, query_string, opts={})
    @name = name + (opts[:suffix] || "")
    @fields = fields
    @imports = opts[:imports] || []
    @query_string = query_string
  end
  
  def write(package)
    dir = File.join(TARGET_DIR, File.join(package.split('.')))
    
    FileUtils.mkdir_p dir
    
    File.open(File.join(dir, "#{name}.java"), "w") do |f|
      package = package.gsub("/", ".")
      f.puts "package #{package};"
      f.puts
      
      (%w(
        com.fasterxml.jackson.annotation.JsonProperty
        com.fasterxml.jackson.databind.annotation.JsonDeserialize
        com.github.princesslana.eriscasper.data.Snowflake
        com.google.common.collect.ImmutableList
        org.immutables.value.Value
      ) + imports).each do |import|
        f.puts "import #{import};"
      end

      f.puts "@Value.Immutable"
      f.puts "@JsonDeserialize(as=Immutable#{name}.class)"
      f.puts "public interface #{name} {"

      query_string_method = '    return new com.github.princesslana.eriscasper.data.util.QueryStringBuilder()'

      fields.each { |field|
        field.write(f)
        if query_string
          query_string_method += "\n      .add#{field.raw_java_type}(\"#{field.property_name}\", #{field.java_name}())"
        end
      }

      if query_string
        f.puts '  default String toQueryString() {'
        f.puts query_string_method
        f.puts '      .build();'
        f.puts '  }'
      end

      f.puts "}"
    end
  end

  def self.from_file(filename, opts={})
    fields = []
    query_string = false

    File.open(filename).each do |line|
      if line.start_with?('qs ')
        query_string = true
      else
        fields.push FieldDef.from_line(line)
      end
    end
  
    ResourceDef.new File.basename(filename).split('_').collect(&:capitalize).join, fields, query_string, opts
  end
end