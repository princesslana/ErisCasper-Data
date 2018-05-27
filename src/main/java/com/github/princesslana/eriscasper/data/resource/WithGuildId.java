package com.github.princesslana.eriscasper.data.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.princesslana.eriscasper.data.Data;
import com.github.princesslana.eriscasper.data.DataException;
import com.github.princesslana.eriscasper.data.Snowflake;

public abstract class WithGuildId<T> {

  private final Snowflake guildId;

  private final T value;

  public WithGuildId(Snowflake guildId, T value) {
    this.guildId = guildId;
    this.value = value;
  }

  protected WithGuildId(Class<T> clazz, JsonNode json) throws DataException {
    guildId = Snowflake.of(json.get("guild_id").asText());
    value = Data.fromJson(json, clazz);
  }

  public Snowflake getGuildId() {
    return guildId;
  }

  protected T getValue() {
    return value;
  }
}
