/*
 * #%L
 * Simmetrics Core
 * %%
 * Copyright (C) 2014 - 2015 Simmetrics Authors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * #L%
 */

package org.simmetrics.metrics;

import static com.google.common.collect.Sets.intersection;
import static java.lang.Math.sqrt;

import java.util.Set;

import org.simmetrics.SetMetric;

/**
 * Tantimoto coefficient providing a similarity measure between two sets.
 * Identical to cosine similarity when the latter used with multiset containing
 * singular entries
 * <p>
 * <code>
 * similarity(a,b) = a·b / (||a|| * ||b||)
 * </code>
 * <p>
 * This class is immutable and thread-safe.
 * 
 * @see CosineSimilarity
 * @see <a href="http://en.wikipedia.org/wiki/Cosine_similarity">Wikipedia
 *      Cosine similarity</a>
 * 
 * @param <T>
 *            type of the token
 */
public class TanimotoCoefficient<T> implements SetMetric<T> {

	@Override
	public float compare(Set<T> a, Set<T> b) {

		if (a.isEmpty() && b.isEmpty()) {
			return 1.0f;
		}

		if (a.isEmpty() || b.isEmpty()) {
			return 0.0f;
		}
		
		// a·b / (||a|| * ||b||)
		// Dot product of two binary vectors is the intersection of two sets
		// Magnitude of a binary vectors is square root of its size.
		return (float) (intersection(a, b).size() / (sqrt(a.size()) * sqrt(b.size())));
	}

	@Override
	public String toString() {
		return "TanimotoCoefficient";
	}

}
