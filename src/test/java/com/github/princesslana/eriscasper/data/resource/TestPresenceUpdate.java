package com.github.princesslana.eriscasper.data.resource;

import com.github.princesslana.eriscasper.data.DataAssert;
import com.github.princesslana.eriscasper.data.Snowflake;
import java.util.Optional;

public class TestPresenceUpdate {

  public void fromJson_whenMinimalPayload_shouldDeserialize() {
    String payload =
        "{         \"user\":{\"id\":\"215210079148834816\"},"
            + "\"status\":\"online\","
            + "\"game\":null}";

    DataAssert.thatFromJson(payload, PresenceUpdate.class)
        .hasFieldOrPropertyWithValue("user.id", Snowflake.of("215210079148834816"))
        .hasFieldOrPropertyWithValue("status", Optional.of("online"))
        .hasFieldOrPropertyWithValue("game", Optional.empty());
  }
}
