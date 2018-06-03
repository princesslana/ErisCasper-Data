package com.github.princesslana.eriscasper.data.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.princesslana.eriscasper.data.Data;
import com.github.princesslana.eriscasper.data.DataException;
import com.github.princesslana.eriscasper.data.Snowflake;
import com.google.common.collect.ImmutableList;
import java.util.Optional;

@SuppressWarnings("unused")
public class AuditLogChange<T> {

  private Class<T> valueClass;
  private T newValue;
  private T oldValue;
  private String key;

  private AuditLogChange(Class<T> valueClass, T newValue, T oldValue, String key) {
    this.valueClass = valueClass;
    this.newValue = newValue;
    this.oldValue = oldValue;
    this.key = key;
  }

  public Class<T> getValueClass() {
    return valueClass;
  }

  public boolean isArray() {
    return valueClass == null;
  }

  public Optional<T> getNewValue() {
    return Optional.ofNullable(newValue);
  }

  public Optional<T> getOldValue() {
    return Optional.ofNullable(oldValue);
  }

  public String getKey() {
    return key;
  }

  public static AuditLogChange<?> parseJson(JsonNode node) {
    String key = node.get("key").textValue();
    switch (key.toLowerCase()) {
      case "$add":
      case "$remove":
        return newArrayInstance(node, Role.class, key);
      case "permission_overwrites":
        return newArrayInstance(node, Overwrite.class, key);
      case "owner_id":
      case "afk_channel_id":
      case "widget_channel_id":
      case "system_channel_id":
      case "channel_id":
      case "inviter_id":
      case "id":
      case "application_id":
        return newInstance(node, Snowflake.class, key);
      case "widget_enabled":
      case "nsfw":
      case "temporary":
      case "deaf":
      case "mute":
      case "hoist":
      case "mentionable":
        return newInstance(node, Boolean.class, key);
      case "permissions":
      case "color":
      case "allow":
      case "deny":
      case "max_uses":
      case "uses":
      case "max_age":
      case "afk_timeout":
      case "mfa_level":
      case "verification_level":
      case "explicit_content_filter":
      case "default_message_notifications":
      case "position":
      case "bitrate":
      case "prune_delete_days":
        return newInstance(node, Integer.class, key);
      case "type":
      case "nick":
      case "avatar_hash":
      case "name":
      case "icon_hash":
      case "splash_hash":
      case "code":
      case "region":
      case "vanity_url_code":
      case "topic":
        return newInstance(node, String.class, key);
      default:
        System.out.println("Reached here with " + key);
        System.out.println("Body?=>" + node.asText());
        // just for stuff not represented by discord (like system_channel_id wasn't)
        // we will catch them as they're reported, can't predict stuff with no documentation for it
        return null;
    }
  }

  private static <T> AuditLogChange<T> newInstance(JsonNode node, Class<T> clazz, String key) {
    T newValue = null;
    T oldValue = null;
    if (node.has("new_value")) {
      newValue = parse(node.get("new_value"), clazz);
    }
    if (node.has("old_value")) {
      oldValue = parse(node.get("old_value"), clazz);
    }
    return new AuditLogChange<>(clazz, newValue, oldValue, key);
  }

  private static <T> AuditLogChange<ImmutableList<T>> newArrayInstance(
      JsonNode node, Class<T> clazz, String key) {
    ImmutableList<T> newValue = ImmutableList.of();
    ImmutableList<T> oldValue = ImmutableList.of();
    if (node.has("new_value")) {
      newValue = parseArray(node.get("new_value"), clazz);
    }
    if (node.has("old_value")) {
      oldValue = parseArray(node.get("old_value"), clazz);
    }
    return new AuditLogChange<>(null, newValue, oldValue, key);
  }

  private static <T> ImmutableList<T> parseArray(JsonNode node, Class<T> clazz) {
    ArrayNode array = (ArrayNode) node;
    ImmutableList.Builder<T> builder = ImmutableList.builder();
    array
        .elements()
        .forEachRemaining(
            part -> {
              T obj = parse(node, clazz);
              if (obj != null) {
                builder.add(obj);
              }
            });
    return builder.build();
  }

  private static <T> T parse(JsonNode node, Class<T> clazz) {
    if (clazz.isInstance(Integer.class)) {
      return clazz.cast(node.asInt());
    } else if (clazz.isInstance(String.class)) {
      return clazz.cast(node.textValue());
    } else if (clazz.isInstance(Boolean.class)) {
      return clazz.cast(node.asBoolean());
    } else {
      try {
        return Data.fromJson(node, clazz);
      } catch (DataException ex) {
        System.out.println("Returning null node...");
        return null;
      }
    }
  }
}
