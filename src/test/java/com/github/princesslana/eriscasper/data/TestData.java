package com.github.princesslana.eriscasper.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.princesslana.eriscasper.data.util.Nullable;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class TestData {

  @Test
  public void toJsonString_whenPresent_shouldInclude() throws DataException {
    Assertions.assertThat(Data.toJsonString(Obj.of(Optional.of("value"))))
        .isEqualTo("{\"field\":\"value\"}");
  }

  @Test
  public void toJsonString_whenNull_shouldInclude() throws DataException {
    Assertions.assertThat(Data.toJsonString(Obj.of(null))).isEqualTo("{\"field\":null}");
  }

  @Test
  public void toJsonString_whenNullableOfNull_shouldInclude() throws DataException {
    Assertions.assertThat(Data.toJsonString(Obj.of(Optional.of(Nullable.ofNull()))))
        .isEqualTo("{\"field\":null}");
  }

  @Test
  public void toJsonString_whenNullableOfValue_shouldInclude() throws DataException {
    Assertions.assertThat(Data.toJsonString(Obj.of(Optional.of(Nullable.of("value")))))
        .isEqualTo("{\"field\":\"value\"}");
  }

  @Test
  public void toJsonString_whenEmpty_shouldOmit() throws DataException {
    Assertions.assertThat(Data.toJsonString(Obj.of(Optional.empty()))).isEqualTo("{}");
  }

  @Test
  public void toJsonString_whenEmptyList_shouldInclude() throws DataException {
    Assertions.assertThat(Data.toJsonString(Obj.of(Optional.of(Collections.emptyList()))))
        .isEqualTo("{\"field\":[]}");
  }

  @Test
  public void toJsonString_whenPresentInMap_shouldInclude() throws DataException {
    Assertions.assertThat(Data.toJsonString(mapOf("key", Optional.of("value"))))
        .isEqualTo("{\"key\":\"value\"}");
  }

  @Test
  public void toJsonString_whenNullInMap_shouldInclude() throws DataException {
    Assertions.assertThat(Data.toJsonString(mapOf("key", null))).isEqualTo("{\"key\":null}");
  }

  @Test
  public void toJsonString_whenEmptyInMap_shouldOmit() throws DataException {
    Assertions.assertThat(Data.toJsonString(mapOf("key", Optional.empty()))).isEqualTo("{}");
  }

  @Test
  public void toJsonString_whenEmptyListInMap_shouldInclude() throws DataException {
    Assertions.assertThat(Data.toJsonString(mapOf("key", Optional.of(Collections.emptyList()))))
        .isEqualTo("{\"key\":[]}");
  }

  @Test
  public void toQueryString_whenPopulated_shouldFormat() {
    Assertions.assertThat(Data.toQueryString(Obj.of(Optional.of("test")))).isEqualTo("field=test");
  }

  @Test
  public void toQueryString_whenPopulatedWithMultipleItems_shouldFormat() {
    Assertions.assertThat(
            Data.toQueryString(
                ImmutableMap.builder().put("test1", "item1").put("test2", "item2").build()))
        .contains("test1=item1")
        .contains("test2=item2")
        .matches(Pattern.compile(".+?(?=&).*"));
  }

  private static class Obj<T> {
    @JsonProperty private final Optional<T> field;

    private Obj(Optional<T> value) {
      this.field = value;
    }

    public static <T> Obj<T> of(Optional<T> value) {
      return new Obj<>(value);
    }
  }

  private static <T> Map<String, Optional<T>> mapOf(String key, Optional<T> value) {
    return Collections.singletonMap(key, value);
  }
}
