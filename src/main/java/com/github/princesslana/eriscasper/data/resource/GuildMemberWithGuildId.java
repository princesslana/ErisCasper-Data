package com.github.princesslana.eriscasper.data.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.princesslana.eriscasper.data.Data;
import com.github.princesslana.eriscasper.data.DataException;
import com.github.princesslana.eriscasper.data.Snowflake;

public class GuildMemberWithGuildId {

  private Snowflake guildId;
  private GuildMember member;

  private GuildMemberWithGuildId() {}

  @JsonCreator
  public GuildMemberWithGuildId(JsonNode json) throws DataException {
    guildId = Snowflake.of(json.get("guild_id").asText());
    member = Data.fromJson(json, GuildMember.class);
  }

  public Snowflake getGuildId() {
    return guildId;
  }

  @SuppressWarnings("unused")
  public GuildMember getGuildMember() {
    return member;
  }

  public static GuildMemberWithGuildId from(Snowflake guildId, GuildMember member) {
    GuildMemberWithGuildId guildMember = new GuildMemberWithGuildId();
    guildMember.guildId = guildId;
    guildMember.member = member;
    return guildMember;
  }
}
