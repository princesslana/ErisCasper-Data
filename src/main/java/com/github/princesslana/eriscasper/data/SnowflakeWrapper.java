package com.github.princesslana.eriscasper.data;

import com.github.princesslana.eriscasper.data.immutable.Wrapped;
import com.github.princesslana.eriscasper.data.immutable.Wrapper;
import org.immutables.value.Value;

@Wrapped
@Value.Immutable
public interface SnowflakeWrapper extends Wrapper<String> { }
