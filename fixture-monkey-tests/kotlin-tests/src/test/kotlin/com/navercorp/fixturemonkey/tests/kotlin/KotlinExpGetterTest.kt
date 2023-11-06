package com.navercorp.fixturemonkey.tests.kotlin

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.intoGetter
import com.navercorp.fixturemonkey.kotlin.maxSizeExpGetter
import com.navercorp.fixturemonkey.kotlin.minSizeExpGetter
import com.navercorp.fixturemonkey.kotlin.setExpGetter
import com.navercorp.fixturemonkey.kotlin.setLazyExpGetter
import com.navercorp.fixturemonkey.kotlin.setNotNullExpGetter
import com.navercorp.fixturemonkey.kotlin.setNullExpGetter
import com.navercorp.fixturemonkey.kotlin.sizeExpGetter
import com.navercorp.fixturemonkey.tests.kotlin.JavaExpGetterTestSpecs.InnerJavaTypeObject
import com.navercorp.fixturemonkey.tests.kotlin.JavaExpGetterTestSpecs.JavaTypeObject
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class KotlinExpGetterTest {
    @Test
    fun setExpGetter() {
        // given
        val expected = "expected"

        // when
        val actual = SUT.giveMeBuilder<JavaTypeObject>()
            .setExpGetter(JavaTypeObject::getString, expected)
            .sample()
            .string

        // then
        then(actual).isEqualTo(expected)
    }

    @Test
    fun setNestedExpGetter() {
        // given
        val expected = "expected"

        // when
        val actual = SUT.giveMeBuilder<JavaTypeObject>()
            .setNotNullExpGetter(JavaTypeObject::getObj)
            .setExpGetter(JavaTypeObject::getObj intoGetter InnerJavaTypeObject::getString, expected)
            .sample()
            .obj
            .string

        // then
        then(actual).isEqualTo(expected)
    }

    @Test
    fun setNullExpGetter() {
        // when
        val actual = SUT.giveMeBuilder<JavaTypeObject>()
            .setNullExpGetter(JavaTypeObject::getString)
            .sample()
            .string

        // then
        then(actual).isNull()
    }

    @Test
    fun setNestedNullExpGetter() {
        // when
        val actual = SUT.giveMeBuilder<JavaTypeObject>()
            .setNullExpGetter(JavaTypeObject::getObj intoGetter InnerJavaTypeObject::getString)
            .sample()
            .obj
            .string

        // then
        then(actual).isNull()
    }

    @Test
    fun setNotNullExpGetter() {
        // when
        val actual = SUT.giveMeBuilder<JavaTypeObject>()
            .setNotNullExpGetter(JavaTypeObject::getString)
            .sample()
            .string

        // then
        then(actual).isNotNull()
    }

    @Test
    fun setNestedNotNullExpGetter() {
        // when
        val actual = SUT.giveMeBuilder<JavaTypeObject>()
            .setNotNullExpGetter(JavaTypeObject::getObj)
            .setNotNullExpGetter(JavaTypeObject::getObj intoGetter InnerJavaTypeObject::getString)
            .sample()
            .obj
            .string

        // then
        then(actual).isNotNull
    }

    @Test
    fun sizeExpGetter() {
        // when
        val actual = SUT.giveMeBuilder<JavaTypeObject>()
            .sizeExpGetter(JavaTypeObject::getList, 1)
            .sample()
            .list

        // then
        then(actual).hasSize(1)
    }

    @Test
    fun sizeNestedExpGetter() {
        // when
        val actual = SUT.giveMeBuilder<JavaTypeObject>()
            .setNotNullExpGetter(JavaTypeObject::getObj)
            .sizeExpGetter(JavaTypeObject::getObj intoGetter InnerJavaTypeObject::getList, 1)
            .sample()
            .obj
            .list

        // then
        then(actual).hasSize(1)
    }

    @Test
    fun minSizeExpGetter() {
        // when
        val actual = SUT.giveMeBuilder<JavaTypeObject>()
            .minSizeExpGetter(JavaTypeObject::getList, 1)
            .sample()
            .list

        // then
        then(actual).hasSizeGreaterThanOrEqualTo(1)
    }

    @Test
    fun minSizeNestedExpGetter() {
        // when
        val actual = SUT.giveMeBuilder<JavaTypeObject>()
            .setNotNullExpGetter(JavaTypeObject::getObj)
            .minSizeExpGetter(JavaTypeObject::getObj intoGetter InnerJavaTypeObject::getList, 1)
            .sample()
            .obj
            .list

        // then
        then(actual).hasSizeGreaterThanOrEqualTo(1)
    }

    @Test
    fun maxSizeExpGetter() {
        // when
        val actual = SUT.giveMeBuilder<JavaTypeObject>()
            .maxSizeExpGetter(JavaTypeObject::getList, 1)
            .sample()
            .list

        // then
        then(actual).hasSizeLessThanOrEqualTo(1)
    }

    @Test
    fun maxSizeNestedExpGetter() {
        // when
        val actual = SUT.giveMeBuilder<JavaTypeObject>()
            .setNotNullExpGetter(JavaTypeObject::getObj)
            .maxSizeExpGetter(JavaTypeObject::getObj intoGetter InnerJavaTypeObject::getList, 1)
            .sample()
            .obj
            .list

        // then
        then(actual).hasSizeLessThanOrEqualTo(1)
    }

    @Test
    fun setLazyExpGetter() {
        // given
        val expected = "expected"

        // when
        val actual = SUT.giveMeBuilder<JavaTypeObject>()
            .setLazyExpGetter(JavaTypeObject::getString) { expected }
            .sample()
            .string

        // then
        then(actual).isEqualTo(expected)
    }

    @Test
    fun setLazyNestedExpGetter() {
        // given
        val expected = "expected"

        // when
        val actual = SUT.giveMeBuilder<JavaTypeObject>()
            .setNotNullExpGetter(JavaTypeObject::getObj)
            .setLazyExpGetter(JavaTypeObject::getObj intoGetter InnerJavaTypeObject::getString) { expected }
            .sample()
            .obj
            .string

        // then
        then(actual).isEqualTo(expected)
    }

    companion object {
        val SUT: FixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .build()
    }
}
