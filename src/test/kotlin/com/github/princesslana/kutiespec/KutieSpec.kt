package com.github.princesslana.kutiespec

import org.assertj.core.api.Assertions
import org.assertj.core.api.AbstractObjectAssert
import org.junit.platform.commons.annotation.Testable

@Testable
open class KutieSpec(block: DescribeBlock.() -> Unit)

class DescribeBlock {
  fun describe(name: String, block: DescribeBlock.() -> Unit): Unit {
  }
  
  fun context(name: String, block: DescribeBlock.() -> Unit): Unit {
    describe(name, block)
  }
  
  fun subject(s: () -> Any): Unit { }
  
  fun it(block: ItBlock.() -> Unit): Unit { }
}

typealias Expectation = (Any) -> Unit

class ItBlock {
  fun expect(a: Any): To {
    return To()     
  }
  
  val beTrue: Expectation = {a ->
    Assertions.assertThat(a).isInstanceOf(Boolean::class.java)
    Assertions.assertThat(a as Boolean).isTrue()
  } 
}

class To {
  fun to(e: Expectation): Unit {
    
  }
}
