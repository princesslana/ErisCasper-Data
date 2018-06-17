package com.github.princesslana.eriscasper.types;

import com.github.princesslana.eriscasper.types.immutable.Wrapped;
import com.github.princesslana.eriscasper.types.immutable.Wrapper;
import org.immutables.value.Value;

@Wrapped
@Value.Immutable
public interface SnowflakeWrapper extends Wrapper<String> {}
