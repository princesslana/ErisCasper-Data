package com.github.princesslana.eriscasper.data.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.princesslana.eriscasper.data.Snowflake;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableUserId.class)
public interface UserId {

  @JsonProperty("id")
  Snowflake getId();
}
