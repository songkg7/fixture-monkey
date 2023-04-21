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

import java.util.function.Predicate;

import javax.validation.ConstraintViolationException;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

import net.jqwik.api.TooManyFilterMissesException;

@API(since = "0.5.0", status = Status.EXPERIMENTAL)
public final class FilteredCombinableArbitrary implements CombinableArbitrary {
	private final int maxMisses;
	private final CombinableArbitrary combinableArbitrary;
	private final Predicate<Object> predicate;

	public FilteredCombinableArbitrary(
		int maxMisses,
		CombinableArbitrary combinableArbitrary,
		Predicate<Object> predicate
	) {
		this.maxMisses = maxMisses;
		this.combinableArbitrary = combinableArbitrary;
		this.predicate = predicate;
	}

	@Override
	public Object combined() {
		Object returned;
		for (int i = 0; i < maxMisses; i++) {
			try {
				returned = combinableArbitrary.combined();
				if (predicate.test(returned)) {
					return returned;
				}
			} catch (TooManyFilterMissesException | ConstraintViolationException
				| jakarta.validation.ConstraintViolationException e) {
				// omitted
			} finally {
				combinableArbitrary.clear();
			}
		}

		throw new TooManyFilterMissesException("");
	}

	@Override
	public Object rawValue() {
		Object returned;
		for (int i = 0; i < maxMisses; i++) {
			try {
				returned = combinableArbitrary.rawValue();
				if (predicate.test(returned)) {
					return returned;
				}
			} catch (TooManyFilterMissesException | ConstraintViolationException
				| jakarta.validation.ConstraintViolationException e) {
				// omitted
			} finally {
				combinableArbitrary.clear();
			}
		}

		throw new TooManyFilterMissesException("");
	}

	@Override
	public void clear() {
		combinableArbitrary.clear();
	}

	@Override
	public boolean fixed() {
		return combinableArbitrary.fixed();
	}
}
