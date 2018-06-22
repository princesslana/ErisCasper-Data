package com.github.princesslana.eriscasper.data.immutable.encoding;

import com.github.princesslana.eriscasper.data.util.Nullable;
import org.immutables.encode.Encoding;
import org.immutables.encode.Encoding.StandardNaming;

@Encoding
public class NullableEncoding<T> {
  @Encoding.Impl private Nullable<T> value = Nullable.ofNull();

  @Encoding.Builder
  static class Builder<T> {
    private Nullable<T> value = Nullable.ofNull();

    @Encoding.Init
    @Encoding.Copy
    public void set(Nullable<T> value) {
      this.value = value;
    }

    @Encoding.Init
    @Encoding.Naming("null*")
    public void ofNull() {
      this.value = Nullable.ofNull();
    }

    @Encoding.Init
    @Encoding.Naming(standard = StandardNaming.INIT)
    public void ofNullable(T value) {
      this.value = Nullable.of(value);
    }

    @Encoding.Build
    public Nullable<T> build() {
      return value;
    }
  }
}
