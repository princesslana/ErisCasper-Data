package com.github.princesslana.eriscasper.data.resource;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserWithGuildId extends WithGuildId<User> {
  @JsonCreator
  public UserWithGuildId(@JacksonInject ObjectMapper jackson, JsonNode json)
      throws JsonProcessingException {
    super(User.class, jackson, json);
  }

  public User getUser() {
    return getValue();
  }
}
