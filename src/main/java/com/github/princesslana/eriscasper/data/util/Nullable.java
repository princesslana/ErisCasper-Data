package com.github.princesslana.eriscasper.data.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.Objects;
import java.util.Optional;

public class Nullable<T> {

  private final Optional<T> value;

  private Nullable(Optional<T> value) {
    this.value = value;
  }

  @JsonValue
  @javax.annotation.Nullable
  public T get() {
    return value.orElse(null);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("value", get()).toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Nullable)) {
      return false;
    }
    Nullable<?> rhs = (Nullable<?>) obj;

    return Objects.equals(value, rhs.value);
  }

  private static <T> Nullable<T> of(Optional<T> value) {
    return new Nullable<>(value);
  }

  public static <T> Nullable<T> ofNull() {
    return of(Optional.empty());
  }

  public static <T> Nullable<T> of(T value) {
    Preconditions.checkNotNull(value);
    return of(Optional.of(value));
  }

  @JsonCreator
  public static <T> Nullable<T> ofNullable(@javax.annotation.Nullable T value) {
    return of(Optional.ofNullable(value));
  }
}
