package com.github.princesslana.eriscasper.data.gateway;

import com.github.princesslana.eriscasper.data.Data;
import com.github.princesslana.eriscasper.data.DataException;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class TestGatewayStatusUpdatePayload {

  @Test
  public void toJsonString_whenNulls_shouldIncludeNulls() throws DataException {
    GatewayStatusUpdatePayload payload =
        ImmutableGatewayStatusUpdatePayload.builder().status("online").isAfk(true).build();

    Assertions.assertThat(Data.fromJson(Data.toJsonString(payload), Map.class))
        .containsEntry("since", null)
        .containsEntry("game", null)
        .containsEntry("status", "online")
        .containsEntry("afk", true);
  }
}
