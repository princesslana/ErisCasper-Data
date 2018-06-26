package com.github.princesslana.eriscasper.data.immutable.encoding;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.immutables.value.Value;
import org.testng.annotations.Test;

public class TestOptionalImmutableListEncoding {

  @Test
  public void field_whenDefault_shouldBeAbsent() {
    Subject subject = ImmutableSubject.builder().build();

    Assertions.assertThat(subject.getField()).isEmpty();
  }

  @Test
  public void field_whenSetToEmptyList_shouldBePresent() {
    Subject subject = ImmutableSubject.builder().field(ImmutableList.of()).build();

    Assertions.assertThat(subject.getField()).isPresent();
    Assertions.assertThat(subject.getField()).hasValue(ImmutableList.of());
  }

  @Test
  void field_whenSetWithIterable_shouldBePresent() {
    Subject subject = ImmutableSubject.builder().field(Arrays.asList("a", "b", "c")).build();

    Assertions.assertThat(subject.getField()).isPresent();
    Assertions.assertThat(subject.getField()).hasValue(ImmutableList.of("a", "b", "c"));
  }

  @Test
  public void field_whenSingleElementAdded_shouldContainThatElement() {
    Subject subject = ImmutableSubject.builder().addField("test").build();

    Assertions.assertThat(subject.getField()).isPresent();
    Assertions.assertThat(subject.getField()).hasValue(ImmutableList.of("test"));
  }

  @Test
  void field_whenMultipleElementsAdded_shouldContainThoseElements() {
    Subject subject = ImmutableSubject.builder().addField("a", "b", "c").build();

    Assertions.assertThat(subject.getField()).isPresent();
    Assertions.assertThat(subject.getField()).hasValue(ImmutableList.of("a", "b", "c"));
  }

  @Value.Immutable
  interface Subject {
    Optional<ImmutableList<String>> getField();
  }
}
