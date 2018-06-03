package com.github.princesslana.eriscasper.data.resource;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.princesslana.eriscasper.data.util.AuditLogChangeDeserializer;
import java.util.Optional;
import org.immutables.value.Value;

@SuppressWarnings("unused")
@Value.Immutable
@JsonDeserialize(using = AuditLogChangeDeserializer.class)
public interface AuditLogChange {

  boolean isArray();

  Optional<Object> getNewValue();

  Optional<Object> getOldValue();

  String getKey();
}
