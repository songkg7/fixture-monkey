package com.navercorp.fixturemonkey.tests.kotlin

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.into
import com.navercorp.fixturemonkey.kotlin.maxSize
import com.navercorp.fixturemonkey.kotlin.maxSizeExp
import com.navercorp.fixturemonkey.kotlin.minSize
import com.navercorp.fixturemonkey.kotlin.minSizeExp
import com.navercorp.fixturemonkey.kotlin.set
import com.navercorp.fixturemonkey.kotlin.setExp
import com.navercorp.fixturemonkey.kotlin.setLazy
import com.navercorp.fixturemonkey.kotlin.setLazyExp
import com.navercorp.fixturemonkey.kotlin.setNotNull
import com.navercorp.fixturemonkey.kotlin.setNotNullExp
import com.navercorp.fixturemonkey.kotlin.setNull
import com.navercorp.fixturemonkey.kotlin.setNullExp
import com.navercorp.fixturemonkey.kotlin.setPostCondition
import com.navercorp.fixturemonkey.kotlin.setPostConditionExp
import com.navercorp.fixturemonkey.kotlin.size
import com.navercorp.fixturemonkey.kotlin.sizeExp
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class KotlinExpTest {
    @Test
    fun set() {
        // given
        class Foo(val foo: String)

        val expected = "expected"

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .set(Foo::foo, expected)
            .sample()
            .foo

        // then
        then(actual).isEqualTo(expected)
    }

    @Test
    fun setExp() {
        // given
        class Foo(val foo: String)

        val expected = "expected"

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setExp(Foo::foo, expected)
            .sample()
            .foo

        // then
        then(actual).isEqualTo(expected)
    }

    @Test
    fun setNested() {
        // given
        class Bar(val bar: Int)
        class Foo(val foo: Bar)

        val expected = 987654321

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .set(Foo::foo into Bar::bar, expected)
            .sample()
            .foo
            .bar

        // then
        then(actual).isEqualTo(expected)
    }

    @Test
    fun setNestedExp() {
        // given
        class Bar(val bar: Int)
        class Foo(val foo: Bar)

        val expected = 987654321

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setExp(Foo::foo into Bar::bar, expected)
            .sample()
            .foo
            .bar

        // then
        then(actual).isEqualTo(expected)
    }

    @Test
    fun setNull() {
        // given
        class Foo(val foo: String?)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setNull(Foo::foo)
            .sample()
            .foo

        // then
        then(actual).isNull()
    }

    @Test
    fun setNullExp() {
        // given
        class Foo(val foo: String?)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setNullExp(Foo::foo)
            .sample()
            .foo

        // then
        then(actual).isNull()
    }

    @Test
    fun setNestedNullExp() {
        // given
        class Bar(val bar: Int?)
        class Foo(val foo: Bar)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setNullExp(Foo::foo into Bar::bar)
            .sample()
            .foo
            .bar

        // then
        then(actual).isNull()
    }

    @Test
    fun setNotNull() {
        // given
        class Foo(val foo: String?)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setNotNull(Foo::foo)
            .sample()
            .foo

        // then
        then(actual).isNotNull
    }

    @Test
    fun setNotNullExp() {
        // given
        class Foo(val foo: String?)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setNotNullExp(Foo::foo)
            .sample()
            .foo

        // then
        then(actual).isNotNull
    }

    @Test
    fun setNestedNotNullExp() {
        // given
        class Bar(val bar: Int?)
        class Foo(val foo: Bar)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setNotNullExp(Foo::foo into Bar::bar)
            .sample()
            .foo
            .bar

        // then
        then(actual).isNotNull
    }

    @Test
    fun setPostConditionExpression() {
        // given
        class Foo(val foo: String?)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setPostCondition<Foo, String>("foo") { it != null }
            .sample()
            .foo

        // then
        then(actual).isNotNull
    }

    @Test
    fun setPostCondition() {
        // given
        class Foo(val foo: String?)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setPostCondition(Foo::foo) { it != null }
            .sample()
            .foo

        // then
        then(actual).isNotNull
    }

    @Test
    fun setPostConditionExp() {
        // given
        class Foo(val foo: String?)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setPostConditionExp(Foo::foo) { it != null }
            .sample()
            .foo

        // then
        then(actual).isNotNull
    }

    @Test
    fun setNestedPostConditionExp() {
        // given
        class Bar(val bar: Int?)
        class Foo(val foo: Bar)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setPostConditionExp<Foo, Int>(Foo::foo into Bar::bar) { it != null }
            .sample()
            .foo
            .bar

        // then
        then(actual).isNotNull
    }

    @Test
    fun size() {
        // given
        class Foo(val foo: List<String>)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .size(Foo::foo, 1)
            .sample()
            .foo

        // then
        then(actual).hasSize(1)
    }

    @Test
    fun sizeExp() {
        // given
        class Foo(val foo: List<String>)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .sizeExp(Foo::foo, 1)
            .sample()
            .foo

        // then
        then(actual).hasSize(1)
    }

    @Test
    fun sizeNestedExp() {
        // given
        class Bar(val bar: List<String>)
        class Foo(val foo: Bar)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .sizeExp(Foo::foo into Bar::bar, 1)
            .sample()
            .foo
            .bar

        // then
        then(actual).hasSize(1)
    }

    @Test
    fun minSize() {
        // given
        class Foo(val foo: List<String>)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .minSize(Foo::foo, 1)
            .sample()
            .foo

        // then
        then(actual).hasSizeGreaterThanOrEqualTo(1)
    }

    @Test
    fun minSizeExp() {
        // given
        class Foo(val foo: List<String>)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .minSizeExp(Foo::foo, 1)
            .sample()
            .foo

        // then
        then(actual).hasSizeGreaterThanOrEqualTo(1)
    }

    @Test
    fun minSizeNestedExp() {
        // given
        class Bar(val bar: List<String>)
        class Foo(val foo: Bar)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .minSizeExp(Foo::foo into Bar::bar, 1)
            .sample()
            .foo
            .bar

        // then
        then(actual).hasSizeGreaterThanOrEqualTo(1)
    }

    @Test
    fun maxSize() {
        // given
        class Foo(val foo: List<String>)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .maxSize(Foo::foo, 1)
            .sample()
            .foo

        // then
        then(actual).hasSizeLessThanOrEqualTo(1)
    }

    @Test
    fun maxSizeExp() {
        // given
        class Foo(val foo: List<String>)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .maxSizeExp(Foo::foo, 1)
            .sample()
            .foo

        // then
        then(actual).hasSizeLessThanOrEqualTo(1)
    }

    @Test
    fun maxSizeNestedExp() {
        // given
        class Bar(val bar: List<String>)
        class Foo(val foo: Bar)

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .maxSizeExp(Foo::foo into Bar::bar, 1)
            .sample()
            .foo
            .bar

        // then
        then(actual).hasSizeLessThanOrEqualTo(1)
    }

    @Test
    fun setLazy() {
        // given
        class Foo(val foo: String)

        val expected = "expected"

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setLazy(Foo::foo) { expected }
            .sample()
            .foo

        // then
        then(actual).isEqualTo(expected)
    }

    @Test
    fun setLazyExp() {
        // given
        class Foo(val foo: String)

        val expected = "expected"

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setLazyExp(Foo::foo) { expected }
            .sample()
            .foo

        // then
        then(actual).isEqualTo(expected)
    }

    @Test
    fun setLazyNested() {
        // given
        class Bar(val bar: Int)
        class Foo(val foo: Bar)

        val expected = 987654321

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setLazy(Foo::foo into Bar::bar) { expected }
            .sample()
            .foo
            .bar

        // then
        then(actual).isEqualTo(expected)
    }

    @Test
    fun setLazyNestedExp() {
        // given
        class Bar(val bar: Int)
        class Foo(val foo: Bar)

        val expected = 987654321

        // when
        val actual = SUT.giveMeBuilder<Foo>()
            .setLazyExp(Foo::foo into Bar::bar) { expected }
            .sample()
            .foo
            .bar

        // then
        then(actual).isEqualTo(expected)
    }

    companion object {
        val SUT: FixtureMonkey = FixtureMonkey.builder()
            .plugin(KotlinPlugin())
            .build()
    }
}
