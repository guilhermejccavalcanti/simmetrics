/*
 * #%L
 * Simmetrics Core
 * %%
 * Copyright (C) 2014 - 2015 Simmetrics Authors
 * %%
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
 * #L%
 */

package org.simmetrics.metrics;

import static com.google.common.collect.Multisets.union;
import static java.lang.Math.sqrt;

import org.simmetrics.MultisetDistance;
import org.simmetrics.MultisetMetric;

import com.google.common.collect.Multiset;

/**
 * Euclidean Distance algorithm providing a similarity measure between two lists
 * using the vector space of combined terms as the dimensions.
 *
 * <p>
 * This class is immutable and thread-safe.
 * @param <T>
 *            type of the token
 * 
 */
public class EuclideanDistance<T> implements MultisetMetric<T>, MultisetDistance<T> {

	@Override
	public float compare(Multiset<T> a, Multiset<T> b) {

		if (a.isEmpty() && b.isEmpty()) {
			return 1.0f;
		}

		float maxDistance = (float) sqrt((a.size() * a.size()) + (b.size() * b.size()));
		return (maxDistance - distance(a, b)) / maxDistance;
	}

	@Override
	public float distance(final Multiset<T> a, final Multiset<T> b) {
		
		float distance = 0.0f;
		
		for (T token : union(a, b).elementSet()) {
			float frequencyInA = a.count(token);
			float frequencyInB = b.count(token);

			distance += ((frequencyInA - frequencyInB) * (frequencyInA - frequencyInB));
		}

		return (float) sqrt(distance);
	}

	@Override
	public String toString() {
		return "EuclideanDistance";
	}

}
