# Using non-primitive types, as we may want to wrap in Optional

TYPES = {
  'a role object' => 'Role',
  'a user object' => 'User',
  'activity object' => 'Activity',
  'array of attachment objects' => 'ImmutableList<Attachment>',
  'array of channel objects' => 'ImmutableList<Channel>',
  'array of DM channel objects' => 'ImmutableList<Channel>',
  'array of embed objects' => 'ImmutableList<Embed>',
  'array of embed field objects' => 'ImmutableList<EmbedField>',
  'array of emoji objects' => 'ImmutableList<Emoji>',
  'array of guild member objects' => 'ImmutableList<GuildMember>',
  'array of guild members' => 'ImmutableList<GuildMember>',
  'array of overwrite objects' => 'ImmutableList<Overwrite>',
  'array of partial presence update objects' => 'ImmutableList<PresenceUpdate>',
  'array of partial voice state objects' => 'ImmutableList<VoiceState>',
  'array of reaction objects' => 'ImmutableList<Reaction>',
  'array of role objects' => 'ImmutableList<Role>',
  'array of role object ids' => 'ImmutableList<Snowflake>',
  'array of snowflakes' => 'ImmutableList<Snowflake>',
  'array of strings' => 'ImmutableList<String>',
  'array of two integers (current_size, max_size)' => 'ImmutableList<Long>',
  'array of Unavailable Guild objects' => 'ImmutableList<UnavailableGuild>',
  'array of user objects' => 'ImmutableList<User>',
  'assets object' => 'ActivityAssets',
  'bool' => 'Boolean',
  'embed author object' => 'EmbedAuthor',
  'embed footer object' => 'EmbedFooter',
  'embed image object' => 'EmbedImage',
  'embed provider object' => 'EmbedProvider',
  'embed thumbnail object' => 'EmbedThumbnail',
  'embed video object' => 'EmbedVideo',
  'int' => 'Long',
  'integer' => 'Long',
  'ISO8601 timestamp' => 'java.time.OffsetDateTime',
  'message activity object' => 'MessageActivity',
  'message application object' => 'MessageApplication',
  'partial emoji object' => 'Emoji',
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
