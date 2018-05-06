# Using non-primitive types, as we may want to wrap in Optional

TYPES = {
  'activity object' => 'Activity',
  'array of channel objects' => 'ImmutableList<Channel>',
  'array of DM channel objects' => 'ImmutableList<Channel>',
  'array of emoji objects' => 'ImmutableList<Emoji>',
  'array of guild member objects' => 'ImmutableList<GuildMember>',
  'array of overwrite objects' => 'ImmutableList<Overwrite>',
  'array of partial presence update objects' => 'ImmutableList<PresenceUpdate>',
  'array of partial voice state objects' => 'ImmutableList<VoiceState>',
  'array of role objects' => 'ImmutableList<Role>',
  'array of role object ids' => 'ImmutableList<Snowflake>',
  'array of snowflakes' => 'ImmutableList<Snowflake>',
  'array of strings' => 'ImmutableList<String>',
  'array of two integers (current_size, max_size)' => 'ImmutableList<Long>',
  'array of Unavailable Guild objects' => 'ImmutableList<UnavailableGuild>',
  'array of user objects' => 'ImmutableList<User>',
  'assets object' => 'ActivityAssets',
  'bool' => 'Boolean',
  'int' => 'Long',
  'integer' => 'Long',
  'ISO8601 timestamp' => 'java.time.OffsetDateTime',
  'object' => 'User',
  'party object' => 'ActivityParty',
  'snowflake' => 'Snowflake',
  'string' => 'String',
  'timestamps object' => 'ActivityTimestamps',
  'user object' => 'User'
}

def get_java_type(t)
  raise "Unknown type '#{t}'" unless TYPES.key? t

  TYPES[t]
end
