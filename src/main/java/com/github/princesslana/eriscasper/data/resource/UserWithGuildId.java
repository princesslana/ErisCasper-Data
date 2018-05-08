package com.github.princesslana.eriscasper.data.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.princesslana.eriscasper.data.DataException;

public class UserWithGuildId extends WithGuildId<User> {
  @JsonCreator
  public UserWithGuildId(JsonNode json) throws DataException {
    super(User.class, json);
  }

  public User getUser() {
    return getValue();
  }
}
