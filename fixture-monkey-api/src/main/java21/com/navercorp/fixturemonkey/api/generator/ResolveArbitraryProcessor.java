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

package com.navercorp.fixturemonkey.api.generator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiFunction;

import com.navercorp.fixturemonkey.api.arbitrary.CombinableArbitrary;
import com.navercorp.fixturemonkey.api.generator.ArbitraryGeneratorContext;
import com.navercorp.fixturemonkey.api.generator.ArbitraryProperty;

public class ResolveArbitraryProcessor {
	public Map<ArbitraryProperty, CombinableArbitrary<?>> resolve(
		ArbitraryGeneratorContext context,
		List<ArbitraryProperty> children,
		BiFunction<ArbitraryGeneratorContext, ArbitraryProperty, CombinableArbitrary<?>> resolveArbitrary
	) {
		if (context.getArbitraryProperty().isContainer()) {
			Map<ArbitraryProperty, CombinableArbitrary<?>> childrenValues = new LinkedHashMap<>();
			for (ArbitraryProperty child : children) {
				CombinableArbitrary<?> arbitrary = resolveArbitrary.apply(context, child);
				childrenValues.put(child, arbitrary);
			}
			return childrenValues;
		}

		Map<ArbitraryProperty, CombinableArbitrary<?>> childrenValues = new ConcurrentHashMap<>();
		CountDownLatch countDownLatch = new CountDownLatch(children.size());
		for (ArbitraryProperty child : children) {
			Thread.startVirtualThread(
				() -> {
					try {
						CombinableArbitrary<?> arbitrary = resolveArbitrary.apply(context, child);
						childrenValues.put(child, arbitrary);
					} finally {
						countDownLatch.countDown();
					}
				}
			);
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return childrenValues;
	}
}
