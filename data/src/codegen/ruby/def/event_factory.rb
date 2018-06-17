
class EventFactoryDef
  attr_reader :events
  
  def initialize(events)
    @events = events;
  end
  
  def write(package)
    dir = File.join(TARGET_DIR, File.join(package.split('.')))
    
    FileUtils.mkdir_p dir
    
    File.open(File.join(dir, "EventFactory.java"), "w") do |f|
      f.puts "package #{package};"
      f.puts
      
      %w(
        com.fasterxml.jackson.databind.JsonNode
        com.github.princesslana.eriscasper.data.Data
        com.github.princesslana.eriscasper.data.DataException
        com.github.princesslana.eriscasper.data.resource.*
      ).each do |import|
        f.puts "import #{import};"
      end
  
      f.puts
      f.puts "public interface EventFactory {"
      f.puts
      f.puts "  Event create(JsonNode data) throws DataException;"
      f.puts
      f.puts "  static EventFactory forType(String type) throws DataException {"
      f.puts "    switch(type) {"
      
      events.each do |evt, dat|
        f.puts "      case \"#{evt.upcase}\": "
        f.puts "        return d -> #{to_event_name(evt)}.of(Data.fromJson(d, #{dat}.class));"
      end
      
      f.puts "      default: "
      f.puts "        throw new DataException(\"Unknown event type \" + type);"
      f.puts "    }"
      f.puts "  }"
      f.puts "}"
    end
  end
  
  
  def to_event_name(evt)
    evt.to_s.split('_').collect(&:capitalize).join + "Event"
  end
end
