package com.github.princesslana.eriscasper.data;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class TestSanity {
  @Test
  public void shouldBeSane() {
    Assertions.assertThat(true).isTrue();
  }
}
