package com.github.princesslana.eriscasper.data.immutable;

import com.fasterxml.jackson.annotation.JsonValue;
import org.immutables.value.Value;

public interface Wrapper<T> {
  @Value.Parameter
  @JsonValue
  T unwrap();
}
