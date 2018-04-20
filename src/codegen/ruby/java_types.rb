# Using non-primitive types, as we may want to wrap in Optional

TYPES = {
  'array of DM channel objects' => 'ImmutableList<Channel>',
  'array of overwrite objects' => 'ImmutableList<Overwrite>',
  'array of strings' => 'ImmutableList<String>',
  'array of Unavailable Guild objects' => 'ImmutableList<UnavailableGuild>',
  'array of user objects' => 'ImmutableList<User>',
  'bool' => 'Boolean',
  'integer' => 'Long',
  'ISO8601 timestamp' => 'java.time.OffsetDateTime',
  'snowflake' => 'Snowflake',
  'string' => 'String',
  'user object' => 'User'
}

def get_java_type(t)
  raise "Unknown type '#{t}'" unless TYPES.key? t

  TYPES[t]
end
