package com.github.princesslana.eriscasper.data.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.princesslana.eriscasper.data.Snowflake;

public abstract class WithGuildId<T> {

  private final Snowflake guildId;

  private final T value;

  protected WithGuildId(Class<T> clazz, ObjectMapper jackson, JsonNode json)
      throws JsonProcessingException {
    guildId = Snowflake.of(json.get("guild_id").asText());
    value = jackson.treeToValue(json, clazz);
  }

  public Snowflake getGuildId() {
    return guildId;
  }

  protected T getValue() {
    return value;
  }
}
