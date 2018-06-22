package com.github.princesslana.eriscasper.data.gateway;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
public abstract class ShardPayload {
  public abstract Long getShardId();

  public abstract Long getNumShards();

  @JsonValue
  public ImmutableList<Long> toArray() {
    return ImmutableList.of(getShardId(), getNumShards());
  }

  @JsonCreator
  private static ShardPayload fromIterable(List<Long> list) {
    Preconditions.checkArgument(list.size() == 2, "Shard creation requires two elements");

    return of(list.get(0), list.get(1));
  }

  public static ShardPayload of(Integer shardId, Integer numShards) {
    return of(shardId.longValue(), numShards.longValue());
  }

  public static ShardPayload of(Long shardId, Long numShards) {
    return ImmutableShardPayload.builder().shardId(shardId).numShards(numShards).build();
  }
}
