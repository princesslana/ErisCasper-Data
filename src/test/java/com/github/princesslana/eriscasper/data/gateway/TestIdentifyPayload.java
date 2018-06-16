package com.github.princesslana.eriscasper.data.gateway;

import com.github.princesslana.eriscasper.data.Data;
import com.github.princesslana.eriscasper.data.DataException;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class TestIdentifyPayload {

  @Test
  public void toJsonString_whenAbsentFields_shouldNotIncludeAbsentFields() throws DataException {
    ConnectionPropertiesPayload connectionProperties =
        ImmutableConnectionPropertiesPayload.builder()
            .os(System.getProperty("os.name"))
            .browser("ErisCasper-Data")
            .device("ErisCasper-Data")
            .build();

    IdentifyPayload payload =
        ImmutableIdentifyPayload.builder().token("abc").properties(connectionProperties).build();

    Assertions.assertThat(Data.fromJson(Data.toJsonString(payload), Map.class))
        .hasSize(2)
        .containsEntry("token", "abc")
        .containsKey("properties")
        .doesNotContainKeys("compress", "large_threshold", "shard", "presence");
  }
}
