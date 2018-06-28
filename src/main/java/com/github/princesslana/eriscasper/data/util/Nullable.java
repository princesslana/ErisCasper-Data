package com.github.princesslana.eriscasper.data.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Nullable<T> {

  private static final Nullable<?> NULL = new Nullable<>(Optional.empty());

  private final Optional<T> value;

  private Nullable(Optional<T> value) {
    this.value = value;
  }

  public <U> Nullable<U> flatMap(Function<? super T, Nullable<U>> f) {
    return isNull() ? ofNull() : f.apply(value.get());
  }

  public <U> Nullable<U> map(Function<? super T, ? extends U> f) {
    return of(value.map(f));
  }

  public boolean isNull() {
    return !value.isPresent();
  }

  public boolean isPresent() {
    return value.isPresent();
  }

  public void ifPresent(Consumer<? super T> c) {
    value.ifPresent(c);
  }

  @JsonValue
  @javax.annotation.Nullable
  public T orNull() {
    return value.orElse(null);
  }

  public T orElse(T other) {
    return value.orElse(other);
  }

  public T orElseGet(Supplier<? extends T> other) {
    return value.orElseGet(other);
  }

  public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
    return value.orElseThrow(exceptionSupplier);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("value", orNull()).toString();
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

  @SuppressWarnings("unchecked")
  public static <T> Nullable<T> ofNull() {
    return (Nullable<T>) NULL;
  }

  public static <T> Nullable<T> of(T value) {
    Preconditions.checkNotNull(value);
    return of(Optional.of(value));
  }

  @JsonCreator
  public static <T> Nullable<T> ofNullable(@javax.annotation.Nullable T value) {
    return value == null ? ofNull() : of(value);
  }
}
