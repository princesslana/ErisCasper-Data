package com.github.princesslana.eriscasper.data.resource;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.princesslana.eriscasper.data.util.AuditLogChangeDeserializer;
import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(using = AuditLogChangeDeserializer.class)
public interface AuditLogChange {

  Optional<Object> getNewValue();

  Optional<Object> getOldValue();

  String getKey();
}
