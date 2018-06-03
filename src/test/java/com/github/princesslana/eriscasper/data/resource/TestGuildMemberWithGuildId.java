package com.github.princesslana.eriscasper.data.resource;

import com.github.princesslana.eriscasper.data.DataAssert;
import com.github.princesslana.eriscasper.data.Snowflake;
import java.util.Optional;
import org.testng.annotations.Test;

public class TestGuildMemberWithGuildId {
  @Test
  public void fromJson_whenValidPayload_shouldDeserialize() {
    String payload =
        "{"
            + "\"user\":"
            + "{"
            + "   \"username\"     :\"LaBotuel\","
            + "   \"id\"           :\"417388135027048495\","
            + "   \"discriminator\":\"7013\","
            + "   \"bot\"          :true,"
            + "   \"avatar\"       :null"
            + "},"
            + "\"joined_at\":\"2018-06-03T10:59:19.884195-04:00\","
            + "\"deaf\":false,"
            + "\"mute\":false,"
            + "\"guild_id\"     :\"123456789123456789\""
            + "}";

    DataAssert.thatFromJson(payload, GuildMemberWithGuildId.class)
        .hasFieldOrPropertyWithValue("guildId", Snowflake.of("123456789123456789"))
        .hasFieldOrPropertyWithValue("member.user.id", Snowflake.of("417388135027048495"))
        .hasFieldOrPropertyWithValue("member.deaf", false)
        .hasFieldOrPropertyWithValue("member.mute", false)
        .hasFieldOrPropertyWithValue("member.user.username", "LaBotuel")
        .hasFieldOrPropertyWithValue("member.user.discriminator", "7013")
        .hasFieldOrPropertyWithValue("member.user.avatar", Optional.empty())
        .hasFieldOrPropertyWithValue("member.user.bot", Optional.of(true))
        .hasFieldOrPropertyWithValue("member.user.mfaEnabled", Optional.empty())
        .hasFieldOrPropertyWithValue("member.user.verified", Optional.empty())
        .hasFieldOrPropertyWithValue("member.user.email", Optional.empty());
  }
}
