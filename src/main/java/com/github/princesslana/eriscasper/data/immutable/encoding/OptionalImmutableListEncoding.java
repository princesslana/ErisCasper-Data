package com.github.princesslana.eriscasper.data.immutable.encoding;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Optional;
import org.immutables.encode.Encoding;
import org.immutables.encode.Encoding.StandardNaming;

@Encoding
public class OptionalImmutableListEncoding<T> {
  @Encoding.Impl private Optional<ImmutableList<T>> value = Optional.empty();

  @Encoding.Builder
  static class Builder<T> {
    private Optional<ImmutableList.Builder<T>> value = Optional.empty();

    @Encoding.Init
    @Encoding.Copy
    public void set(Optional<ImmutableList<T>> value) {
      this.value = value.map(vs -> ImmutableList.<T>builder().addAll(vs));
    }

    @Encoding.Init
    @Encoding.Naming(standard = StandardNaming.INIT)
    public void ofIterable(Iterable<? extends T> vs) {
      this.value = Optional.of(ImmutableList.<T>builder().addAll(vs));
    }

    @Encoding.Init
    @Encoding.Naming(standard = StandardNaming.ADD)
    public final void add(T v) {
      ImmutableList.Builder<T> b = value.orElse(ImmutableList.builder());
      value = Optional.of(b.add(v));
    }

    @Encoding.Init
    @Encoding.Naming(standard = StandardNaming.ADD)
    @SafeVarargs
    public final void addVarArgs(T... vs) {
      Arrays.stream(vs).forEach(this::add);
    }

    @Encoding.Build
    public Optional<ImmutableList<T>> build() {
      return value.map(ImmutableList.Builder::build);
    }
  }
}
