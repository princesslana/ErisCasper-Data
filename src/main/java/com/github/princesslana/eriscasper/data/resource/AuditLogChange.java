package com.github.princesslana.eriscasper.data.resource;

import java.util.Optional;
import org.immutables.value.Value;

@SuppressWarnings("unused")
@Value.Immutable
public interface AuditLogChange {

  boolean isArray();

  Optional<Object> newValue();

  Optional<Object> oldValue();

  String getKey();
}
