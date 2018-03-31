# Using non-primitive types, as we may want to wrap in Optional

TYPES = {
  'array of DM channel objects' => 'ImmutableList<ChannelResource>',
  'array of overwrite objects' => 'ImmutableList<OverwriteResource>',
  'array of strings' => 'ImmutableList<String>',
  'array of Unavailable Guild objects' => 'ImmutableList<UnavailableGuildResource>',
  'array of user objects' => 'ImmutableList<UserResource>',
  'bool' => 'Boolean',
  'integer' => 'Long',
  'ISO8601 timestamp' => 'java.time.OffsetDateTime',
  'snowflake' => 'Snowflake',
  'string' => 'String',
  'user object' => 'UserResource'
}

def get_java_type(t)
  raise "Unknown type '#{t}'" unless TYPES.key? t

  TYPES[t]
end
