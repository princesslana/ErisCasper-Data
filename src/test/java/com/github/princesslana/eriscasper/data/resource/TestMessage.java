package com.github.princesslana.eriscasper.data.resource;

import com.github.princesslana.eriscasper.data.DataAssert;
import com.github.princesslana.eriscasper.data.Snowflake;
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
    DataAssert.thatJsonFromFile("resource/example_message.json", Message.class)
        .hasFieldOrPropertyWithValue("id", Snowflake.of("334385199974967042"))
        .hasFieldOrPropertyWithValue("author.username", "Mason")
        .hasFieldOrPropertyWithValue("author.discriminator", "9999")
        .hasFieldOrPropertyWithValue(
            "author.avatar", Optional.of("a_bab14f271d565501444b2ca3be944b25"))
        .hasFieldOrPropertyWithValue("content", "Supa Hot");
  }
}
