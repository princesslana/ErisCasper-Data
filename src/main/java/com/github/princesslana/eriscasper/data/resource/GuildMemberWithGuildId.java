package com.github.princesslana.eriscasper.data.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.princesslana.eriscasper.data.DataException;

public class GuildMemberWithGuildId extends WithGuildId<GuildMember> {
  @JsonCreator
  public GuildMemberWithGuildId(JsonNode json) throws DataException {
    super(GuildMember.class, json);
  }

  public GuildMember getGuildMember() {
    return getValue();
  }
}
