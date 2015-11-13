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

import static com.google.common.collect.Multisets.union;
import static java.lang.Math.sqrt;

import org.simmetrics.MultisetMetric;

import com.google.common.collect.Multiset;

/**
 * Cosine Similarity algorithm providing a similarity measure between two
 * multisets.
 * <p>
 * <code>
 * similarity(a,b) = a·b / (||a|| * ||b||)
 * </code>
 * 
 * <p>
 * This class is immutable and thread-safe.
 * 
 * @see TanimotoCoefficient
 * @see <a href="http://en.wikipedia.org/wiki/Cosine_similarity">Wikipedia
 *      Cosine similarity</a>
 * 
 * @param <T>
 *            type of the token
 */
public class CosineSimilarity<T> implements MultisetMetric<T> {

	@Override
	public float compare(Multiset<T> a, Multiset<T> b) {

		if (a.isEmpty() && b.isEmpty()) {
			return 1.0f;
		}

		if (a.isEmpty() || b.isEmpty()) {
			return 0.0f;
		}

		float dotProduct = 0;
		float magnitudeA = 0;
		float magnitudeB = 0;

		for (T entry : union(a, b).elementSet()) {
			float aCount = a.count(entry);
			float bCount = b.count(entry);

			dotProduct += aCount * bCount;
			magnitudeA += aCount * aCount;
			magnitudeB += bCount * bCount;
		}

		//  a·b / (||a|| * ||b||)
		return (float) (dotProduct / (sqrt(magnitudeA) * sqrt(magnitudeB)));
	}

	@Override
	public String toString() {
		return "CosineSimilarity";
	}

}
