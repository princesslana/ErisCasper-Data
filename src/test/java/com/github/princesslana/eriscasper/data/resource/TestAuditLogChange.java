package com.github.princesslana.eriscasper.data.resource;

import com.github.princesslana.eriscasper.data.DataAssert;
import java.util.Optional;
import org.testng.annotations.Test;

public class TestAuditLogChange {
  @Test
  public void fromJson_whenValidPayload_shouldDeserialize() {
    String payload = "{" + "\"key\":\"nick\"," + "\"old_value\":\"SomeNick\"" + "}";

    DataAssert.thatFromJson(payload, AuditLogChange.class)
        .hasFieldOrPropertyWithValue("key", "nick")
        .hasFieldOrPropertyWithValue("oldValue", Optional.of("SomeNick"))
        .hasFieldOrPropertyWithValue("newValue", Optional.empty());
  }
}
