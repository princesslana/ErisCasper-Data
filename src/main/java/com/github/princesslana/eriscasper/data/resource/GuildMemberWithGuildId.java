package com.github.princesslana.eriscasper.data.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.princesslana.eriscasper.data.DataException;
import com.github.princesslana.eriscasper.data.Snowflake;

public class GuildMemberWithGuildId extends WithGuildId<GuildMember> {

  public GuildMemberWithGuildId(Snowflake guildId, GuildMember member) {
    super(guildId, member);
  }

  @JsonCreator
  public GuildMemberWithGuildId(JsonNode json) throws DataException {
    super(GuildMember.class, json);
  }

  public GuildMember getGuildMember() {
    return getValue();
  }
}
