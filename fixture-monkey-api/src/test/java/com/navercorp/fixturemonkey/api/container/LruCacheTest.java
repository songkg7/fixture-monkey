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

package com.navercorp.fixturemonkey.api.container;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

import lombok.Value;

public class LruCacheTest {
	@Test
	void put() {
		LruCache<String, Integer> lruCache = new LruCache<>(1);
		lruCache.put("a", 1);

		// when
		lruCache.put("b", 2);

		// then
		then(lruCache).hasSize(1);
	}

	@Test
	void computeIfAbsent() {
		LruCache<KeyObject, Integer> lruCache = new LruCache<>(1);
		lruCache.put(new KeyObject("a"), 1);

		// when
		lruCache.computeIfAbsent(new KeyObject("b"), key -> 2);

		// then
		then(lruCache).hasSize(1);
	}

	@Value
	private static class KeyObject {
		String key;
	}
}
