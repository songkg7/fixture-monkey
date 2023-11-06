/*
 * Fixture Monkey
 *
 * Copyright (c) 2021-present NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("unused")

package com.navercorp.fixturemonkey.kotlin

import com.navercorp.fixturemonkey.ArbitraryBuilder
import com.navercorp.fixturemonkey.api.expression.ExpressionGenerator
import java.util.function.Predicate
import java.util.function.Supplier
import kotlin.reflect.KFunction1
import kotlin.reflect.KProperty1

fun <T> ArbitraryBuilder<T>.setExp(expressionGenerator: ExpressionGenerator, value: Any?): ArbitraryBuilder<T> =
    this.set(expressionGenerator, value)

fun <T> ArbitraryBuilder<T>.setExp(
    expressionGenerator: ExpressionGenerator,
    value: Any?,
    limit: Int,
): ArbitraryBuilder<T> =
    this.set(expressionGenerator, value, limit)

fun <T> ArbitraryBuilder<T>.setExpGetter(expressionGenerator: ExpressionGenerator, value: Any?): ArbitraryBuilder<T> =
    this.set(expressionGenerator, value)

fun <T> ArbitraryBuilder<T>.setExpGetter(
    expressionGenerator: ExpressionGenerator,
    value: Any?,
    limit: Int,
): ArbitraryBuilder<T> =
    this.set(expressionGenerator, value, limit)

fun <T> ArbitraryBuilder<T>.setNullExp(expressionGenerator: ExpressionGenerator): ArbitraryBuilder<T> =
    this.setNull(expressionGenerator)

fun <T> ArbitraryBuilder<T>.setNullExpGetter(expressionGenerator: ExpressionGenerator): ArbitraryBuilder<T> =
    this.setNull(expressionGenerator)

fun <T> ArbitraryBuilder<T>.setNotNullExp(expressionGenerator: ExpressionGenerator): ArbitraryBuilder<T> =
    this.setNotNull(expressionGenerator)

fun <T> ArbitraryBuilder<T>.setNotNullExpGetter(expressionGenerator: ExpressionGenerator): ArbitraryBuilder<T> =
    this.setNotNull(expressionGenerator)

inline fun <T, reified U> ArbitraryBuilder<T>.setPostConditionExp(
    property: KProperty1<T, U>,
    noinline filter: (U?) -> Boolean,
    limit: Int,
): ArbitraryBuilder<T> =
    this.setPostCondition(
        property(property),
        U::class.java,
        filter,
        limit,
    )

inline fun <T, reified U> ArbitraryBuilder<T>.setPostConditionExp(
    property: KProperty1<T, U>,
    noinline filter: (U?) -> Boolean,
): ArbitraryBuilder<T> =
    this.setPostCondition(
        property(property),
        U::class.java,
        filter,
    )

inline fun <T, reified U> ArbitraryBuilder<T>.setPostConditionExpGetter(
    function: KFunction1<T, U>,
    noinline filter: (U?) -> Boolean,
    limit: Int,
): ArbitraryBuilder<T> =
    this.setPostCondition(
        property(function),
        U::class.java,
        filter,
        limit,
    )

inline fun <T, reified U> ArbitraryBuilder<T>.setPostConditionExpGetter(
    function: KFunction1<T, U>,
    noinline filter: (U?) -> Boolean,
): ArbitraryBuilder<T> =
    this.setPostCondition(
        property(function),
        U::class.java,
        filter,
    )

inline fun <T, reified U> ArbitraryBuilder<T>.setPostConditionExp(
    expressionGenerator: ExpressionGenerator,
    noinline filter: (U?) -> Boolean,
    limit: Int,
): ArbitraryBuilder<T> =
    this.setPostCondition(
        expressionGenerator,
        U::class.java,
        filter,
        limit,
    )

inline fun <T, reified U> ArbitraryBuilder<T>.setPostConditionExp(
    expressionGenerator: ExpressionGenerator,
    noinline filter: (U?) -> Boolean,
): ArbitraryBuilder<T> =
    this.setPostCondition(
        expressionGenerator,
        U::class.java,
        filter,
    )

inline fun <T, reified U> ArbitraryBuilder<T>.setPostConditionExpGetter(
    expressionGenerator: ExpressionGenerator,
    noinline filter: (U?) -> Boolean,
    limit: Int,
): ArbitraryBuilder<T> =
    this.setPostCondition(
        expressionGenerator,
        U::class.java,
        filter,
        limit,
    )

inline fun <T, reified U> ArbitraryBuilder<T>.setPostConditionExpGetter(
    expressionGenerator: ExpressionGenerator,
    noinline filter: (U?) -> Boolean,
): ArbitraryBuilder<T> =
    this.setPostCondition(
        expressionGenerator,
        U::class.java,
        filter,
    )

fun <T> ArbitraryBuilder<T>.sizeExp(
    expressionGenerator: ExpressionGenerator,
    size: Int,
): ArbitraryBuilder<T> =
    this.size(expressionGenerator, size)

fun <T> ArbitraryBuilder<T>.sizeExpGetter(
    expressionGenerator: ExpressionGenerator,
    size: Int,
): ArbitraryBuilder<T> =
    this.size(expressionGenerator, size)

fun <T> ArbitraryBuilder<T>.sizeExp(
    expressionGenerator: ExpressionGenerator,
    min: Int,
    max: Int,
): ArbitraryBuilder<T> =
    this.size(expressionGenerator, min, max)

fun <T> ArbitraryBuilder<T>.sizeExpGetter(
    expressionGenerator: ExpressionGenerator,
    min: Int,
    max: Int,
): ArbitraryBuilder<T> =
    this.size(expressionGenerator, min, max)

fun <T> ArbitraryBuilder<T>.minSizeExp(
    expressionGenerator: ExpressionGenerator,
    min: Int,
): ArbitraryBuilder<T> =
    this.minSize(expressionGenerator, min)

fun <T> ArbitraryBuilder<T>.minSizeExpGetter(
    expressionGenerator: ExpressionGenerator,
    min: Int,
): ArbitraryBuilder<T> =
    this.minSize(expressionGenerator, min)

fun <T> ArbitraryBuilder<T>.maxSizeExp(
    expressionGenerator: ExpressionGenerator,
    max: Int,
): ArbitraryBuilder<T> =
    this.maxSize(expressionGenerator, max)

fun <T> ArbitraryBuilder<T>.maxSizeExpGetter(
    expressionGenerator: ExpressionGenerator,
    max: Int,
): ArbitraryBuilder<T> =
    this.maxSize(expressionGenerator, max)

fun <T> ArbitraryBuilder<T>.setLazyExp(
    expressionGenerator: ExpressionGenerator,
    supplier: Supplier<Any?>,
): ArbitraryBuilder<T> =
    this.setLazy(expressionGenerator, supplier)

fun <T> ArbitraryBuilder<T>.setLazyExp(
    expressionGenerator: ExpressionGenerator,
    supplier: Supplier<Any?>,
    limit: Long,
): ArbitraryBuilder<T> =
    this.setLazy(expressionGenerator, supplier, limit.toInt())

fun <T> ArbitraryBuilder<T>.setLazyExpGetter(
    expressionGenerator: ExpressionGenerator,
    supplier: Supplier<Any?>,
): ArbitraryBuilder<T> =
    this.setLazy(expressionGenerator, supplier)

fun <T> ArbitraryBuilder<T>.setLazyExpGetter(
    expressionGenerator: ExpressionGenerator,
    supplier: Supplier<Any?>,
    limit: Int,
): ArbitraryBuilder<T> =
    this.setLazy(expressionGenerator, supplier, limit)

fun <T> ArbitraryBuilder<T>.set(property: KProperty1<T, Any?>, value: Any?): ArbitraryBuilder<T> =
    this.set(property(property), value)

fun <T> ArbitraryBuilder<T>.set(property: KProperty1<T, Any?>, value: Any?, limit: Int): ArbitraryBuilder<T> =
    this.set(property(property), value, limit)

fun <T> ArbitraryBuilder<T>.setExp(property: KProperty1<T, Any?>, value: Any?): ArbitraryBuilder<T> =
    this.set(property(property), value)

fun <T> ArbitraryBuilder<T>.setExp(property: KProperty1<T, Any?>, value: Any?, limit: Int): ArbitraryBuilder<T> =
    this.set(property(property), value, limit)

fun <T> ArbitraryBuilder<T>.setNull(property: KProperty1<T, *>): ArbitraryBuilder<T> =
    this.setNull(property(property))

fun <T> ArbitraryBuilder<T>.setNullExp(property: KProperty1<T, *>): ArbitraryBuilder<T> =
    this.setNull(property(property))

fun <T> ArbitraryBuilder<T>.setNotNull(property: KProperty1<T, *>): ArbitraryBuilder<T> =
    this.setNotNull(property(property))

fun <T> ArbitraryBuilder<T>.setNotNullExp(property: KProperty1<T, *>): ArbitraryBuilder<T> =
    this.setNotNull(property(property))

inline fun <T, reified U> ArbitraryBuilder<T>.setPostCondition(
    expressionGenerator: ExpressionGenerator,
    noinline predicate: (U?) -> Boolean,
): ArbitraryBuilder<T> = this.setPostCondition(expressionGenerator, U::class.java, predicate)

inline fun <T, reified U> ArbitraryBuilder<T>.setPostCondition(
    expressionGenerator: ExpressionGenerator,
    noinline predicate: (U?) -> Boolean,
    limit: Int,
): ArbitraryBuilder<T> = this.setPostCondition(expressionGenerator, U::class.java, predicate, limit)

inline fun <T, reified U> ArbitraryBuilder<T>.setPostCondition(
    property: KProperty1<T, U>,
    noinline filter: (U?) -> Boolean,
    limit: Int,
): ArbitraryBuilder<T> =
    this.setPostCondition(
        property(property),
        U::class.java,
        filter,
        limit,
    )

inline fun <T, reified U> ArbitraryBuilder<T>.setPostCondition(
    property: KProperty1<T, U>,
    noinline filter: (U?) -> Boolean,
): ArbitraryBuilder<T> =
    this.setPostCondition(
        property(property),
        U::class.java,
        filter,
    )

fun <T> ArbitraryBuilder<T>.size(
    property: KProperty1<T, *>,
    size: Int,
): ArbitraryBuilder<T> = this.size(property(property), size)

fun <T> ArbitraryBuilder<T>.size(
    property: KProperty1<T, *>,
    min: Int,
    max: Int,
): ArbitraryBuilder<T> = this.size(property(property), min, max)

fun <T> ArbitraryBuilder<T>.sizeExp(
    property: KProperty1<T, *>,
    size: Int,
): ArbitraryBuilder<T> = this.size(property(property), size)

fun <T> ArbitraryBuilder<T>.sizeExp(
    property: KProperty1<T, *>,
    min: Int,
    max: Int,
): ArbitraryBuilder<T> = this.size(property(property), min, max)

fun <T> ArbitraryBuilder<T>.minSize(
    property: KProperty1<T, *>,
    min: Int,
): ArbitraryBuilder<T> = this.minSize(property(property), min)

fun <T> ArbitraryBuilder<T>.minSizeExp(
    property: KProperty1<T, *>,
    min: Int,
): ArbitraryBuilder<T> = this.minSize(property(property), min)

fun <T> ArbitraryBuilder<T>.maxSize(
    property: KProperty1<T, *>,
    max: Int,
): ArbitraryBuilder<T> = this.maxSize(property(property), max)

fun <T> ArbitraryBuilder<T>.maxSizeExp(
    property: KProperty1<T, *>,
    max: Int,
): ArbitraryBuilder<T> = this.maxSize(property(property), max)

fun <T> ArbitraryBuilder<T>.setLazy(
    property: KProperty1<T, Any?>,
    supplier: Supplier<Any?>,
): ArbitraryBuilder<T> = this.setLazy(property(property), supplier)

fun <T> ArbitraryBuilder<T>.setLazyExp(
    property: KProperty1<T, Any?>,
    supplier: Supplier<Any?>,
): ArbitraryBuilder<T> = this.setLazy(property(property), supplier)

fun <T> ArbitraryBuilder<T>.setLazyExp(
    property: KProperty1<T, Any?>,
    supplier: Supplier<Any?>,
    limit: Int,
): ArbitraryBuilder<T> = this.setLazy(property(property), supplier, limit)

fun <T> ArbitraryBuilder<T>.setExpGetter(property: KFunction1<T, Any?>, value: Any?): ArbitraryBuilder<T> =
    this.set(property(property), value)

fun <T> ArbitraryBuilder<T>.setExpGetter(property: KFunction1<T, Any?>, value: Any?, limit: Int): ArbitraryBuilder<T> =
    this.set(
        property(property),
        value,
        limit,
    )

fun <T> ArbitraryBuilder<T>.setNullExpGetter(property: KFunction1<T, *>): ArbitraryBuilder<T> =
    this.setNull(property(property))

fun <T> ArbitraryBuilder<T>.setNotNullExpGetter(property: KFunction1<T, *>): ArbitraryBuilder<T> =
    this.setNotNull(property(property))

fun <T, U> ArbitraryBuilder<T>.setPostConditionExpGetter(
    property: KFunction1<T, *>,
    clazz: Class<U>,
    filter: Predicate<U>,
    limit: Int,
): ArbitraryBuilder<T> =
    this.setPostCondition(
        property(property),
        clazz,
        filter,
        limit,
    )

fun <T, U> ArbitraryBuilder<T>.setPostConditionExpGetter(
    property: KFunction1<T, *>,
    clazz: Class<U>,
    filter: Predicate<U>,
): ArbitraryBuilder<T> =
    this.setPostCondition(
        property(property),
        clazz,
        filter,
    )

fun <T> ArbitraryBuilder<T>.sizeExpGetter(
    property: KFunction1<T, *>,
    size: Int,
): ArbitraryBuilder<T> =
    this.size(property(property), size)

fun <T> ArbitraryBuilder<T>.sizeExpGetter(
    property: KFunction1<T, *>,
    min: Int,
    max: Int,
): ArbitraryBuilder<T> =
    this.size(property(property), min, max)

fun <T> ArbitraryBuilder<T>.minSizeExpGetter(
    property: KFunction1<T, *>,
    min: Int,
): ArbitraryBuilder<T> =
    this.minSize(property(property), min)

fun <T> ArbitraryBuilder<T>.maxSizeExpGetter(
    property: KFunction1<T, *>,
    max: Int,
): ArbitraryBuilder<T> =
    this.maxSize(property(property), max)

fun <T> ArbitraryBuilder<T>.setLazyExpGetter(
    property: KFunction1<T, Any?>,
    supplier: Supplier<Any?>,
): ArbitraryBuilder<T> =
    this.setLazy(property(property), supplier)

fun <T> ArbitraryBuilder<T>.setLazyExpGetter(
    property: KFunction1<T, Any?>,
    supplier: Supplier<Any?>,
    limit: Int,
): ArbitraryBuilder<T> =
    this.setLazy(property(property), supplier, limit)
