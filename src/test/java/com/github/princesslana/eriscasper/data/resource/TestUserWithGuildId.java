package com.github.princesslana.eriscasper.data.resource;

import com.github.princesslana.eriscasper.data.DataAssert;
import com.github.princesslana.eriscasper.data.Snowflake;
import java.util.Optional;

public class TestUserWithGuildId {
  public void fromJson_whenValidPayload_shouldDeserialize() {
    String payload =
        "{     \"username\"     :\"LaBotuel\","
            + "\"id\"           :\"417388135027048495\","
            + "\"discriminator\":\"7013\","
            + "\"bot\"          :true,"
            + "\"avatar\"       :null,"
            + "\"guild_id\"     :\"123456789123456789\""
            + "}";

    DataAssert.thatFromJson(payload, UserWithGuildId.class)
        .hasFieldOrPropertyWithValue("guildId", Snowflake.of("123456789123456789"))
        .hasFieldOrPropertyWithValue("user.id", Snowflake.of("417388135027048495"))
        .hasFieldOrPropertyWithValue("user.username", "LaBotuel")
        .hasFieldOrPropertyWithValue("user.discriminator", "7013")
        .hasFieldOrPropertyWithValue("user.avatar", Optional.empty())
        .hasFieldOrPropertyWithValue("user.bot", Optional.of(true))
        .hasFieldOrPropertyWithValue("user.mfaEnabled", Optional.empty())
        .hasFieldOrPropertyWithValue("user.verified", Optional.empty())
        .hasFieldOrPropertyWithValue("user.email", Optional.empty());
  }
}
