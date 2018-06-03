package com.github.princesslana.eriscasper.data.resource;

import java.util.Optional;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.princesslana.eriscasper.data.util.AuditLogChangeDeserializer;
import org.immutables.value.Value;

@SuppressWarnings("unused")
@Value.Immutable
public interface AuditLogChange {

  boolean isArray();

  Optional<Object> getNewValue();

  Optional<Object> getOldValue();

  String getKey();
}
