package com.github.princesslana.eriscasper.data.resource;

import com.github.princesslana.eriscasper.data.DataAssert;
import com.github.princesslana.eriscasper.types.Snowflake;
import com.google.common.collect.ImmutableList;
import java.util.Optional;
import org.testng.annotations.Test;

public class TestMessage {

  /**
   * @see <a
   *     href="https://discordapp.com/developers/docs/resources/channel#message-object-example-message">
   *     https://discordapp.com/developers/docs/resources/channel#message-object-example-message</a>
   */
  @Test
  public void fromJson_whenExamplePayload_shouldDeseralize() {
    User expectedAuthor =
        ImmutableUser.builder()
            .id(Snowflake.of("53908099506183680"))
            .username("Mason")
            .discriminator("9999")
            .avatar("a_bab14f271d565501444b2ca3be944b25")
            .build();

    DataAssert.thatJsonFromFile("resource/example_message.json", Message.class)
        .hasFieldOrPropertyWithValue("id", Snowflake.of("334385199974967042"))
        .hasFieldOrPropertyWithValue("author", Optional.of(expectedAuthor))
        .hasFieldOrPropertyWithValue("content", Optional.of("Supa Hot"));
  }

  @Test
  public void fromJson_whenPongEmbedPayload_shouldDeserialize() {
    Embed expectedEmbed = ImmutableEmbed.builder().type("rich").title("pong").build();

    DataAssert.thatJsonFromFile("resource/pong_embed_message.json", Message.class)
        .hasFieldOrPropertyWithValue("id", Snowflake.of("445358669157367810"))
        .hasFieldOrPropertyWithValue("embeds", ImmutableList.of(expectedEmbed));
  }
}
