package com.github.princesslana.eriscasper.data.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.princesslana.eriscasper.data.Data;
import com.github.princesslana.eriscasper.data.DataException;
import com.github.princesslana.eriscasper.data.Snowflake;

public class GuildMemberWithGuildId {

  private final Snowflake guildId;
  private final GuildMember member;

  public GuildMemberWithGuildId(Snowflake guildId, GuildMember member) {
    this.guildId = guildId;
    this.member = member;
  }

  public Snowflake getGuildId() {
    return guildId;
  }

  @SuppressWarnings("unused")
  public GuildMember getGuildMember() {
    return member;
  }

  @JsonCreator
  public static GuildMemberWithGuildId fromJson(JsonNode node) throws DataException {
    Snowflake guildId = Snowflake.of(node.get("guild_id").asText());
    GuildMember member = Data.fromJson(node, GuildMember.class);
    return new GuildMemberWithGuildId(guildId, member);
  }
}
