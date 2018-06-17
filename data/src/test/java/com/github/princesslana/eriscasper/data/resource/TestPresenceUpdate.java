package com.github.princesslana.eriscasper.data.resource;

import com.github.princesslana.eriscasper.data.DataAssert;
import com.github.princesslana.eriscasper.types.Snowflake;
import java.util.Optional;
import org.testng.annotations.Test;

public class TestPresenceUpdate {

  @Test
  public void fromJson_whenMinimalPayload_shouldDeserialize() {
    String payload =
        "{         \"user\":{\"id\":\"215210079148834816\"},"
            + "\"status\":\"online\","
            + "\"game\":null}";

    DataAssert.thatFromJson(payload, PresenceUpdate.class)
        .hasFieldOrPropertyWithValue("user.id", Snowflake.of("215210079148834816"))
        .hasFieldOrPropertyWithValue("status", Optional.of("online"))
        .hasFieldOrPropertyWithValue("game", null)
        .hasFieldOrPropertyWithValue("guildId", Optional.empty());
  }
}
