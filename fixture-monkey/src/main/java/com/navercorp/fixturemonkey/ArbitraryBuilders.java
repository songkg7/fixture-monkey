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

package com.navercorp.fixturemonkey;

import java.util.function.BiFunction;

public final class ArbitraryBuilders {
	public static <T, U, R> ArbitraryBuilder<R> zip(
		ArbitraryBuilder<T> a1,
		ArbitraryBuilder<U> a2,
		BiFunction<T, U, R> combinator
	) {
		return a1.zipWith(a2, combinator);
	}
}