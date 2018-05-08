package com.github.princesslana.eriscasper.data;

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assertions;

public class DataAssert {
  private DataAssert() {}

  public static <T> AbstractObjectAssert<?, T> thatFromJson(String payload, Class<T> cls) {
    try {
      T data = Data.fromJson(payload, cls);

      return Assertions.assertThat(data);
    } catch (DataException e) {
      throw new AssertionError("Could not parse payload", e);
    }
  }
}
