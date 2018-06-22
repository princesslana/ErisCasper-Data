package com.github.princesslana.eriscasper.data.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
public abstract class ActivityPartySize {
  public abstract Long getCurrentSize();

  public abstract Long getMaxSize();

  @JsonValue
  public ImmutableList<Long> toArray() {
    return ImmutableList.of(getCurrentSize(), getMaxSize());
  }

  @JsonCreator
  private static ActivityPartySize fromIterable(List<Long> list) {
    Preconditions.checkArgument(
        list.size() == 2, "ActivityPartySize creation requires two elements");

    return of(list.get(0), list.get(1));
  }

  public static ActivityPartySize of(Integer currentSize, Integer maxSize) {
    return of(currentSize.longValue(), maxSize.longValue());
  }

  public static ActivityPartySize of(Long currentSize, Long maxSize) {
    return ImmutableActivityPartySize.builder().currentSize(currentSize).maxSize(maxSize).build();
  }
}
