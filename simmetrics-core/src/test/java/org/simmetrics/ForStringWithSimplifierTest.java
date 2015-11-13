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
package org.simmetrics;

import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.simmetrics.StringMetrics.ForStringWithSimplifier;
import org.simmetrics.metrics.Identity;
import org.simmetrics.simplifiers.Simplifier;
import org.simmetrics.simplifiers.Simplifiers;

@SuppressWarnings("javadoc")
public class ForStringWithSimplifierTest extends StringMetricTest{

	private final Metric<String> metric = new Identity<>();
	private final Simplifier simplifier = Simplifiers.toLowerCase();
	
	@Override
	protected ForStringWithSimplifier getMetric() {
		return new ForStringWithSimplifier(metric, simplifier);
	}
	
	@Override
	protected T[] getStringTests() {
		return new T[]{
				new T(0.0f, "To repeat repeat is to repeat", ""),
				new T(1.0f, "To repeat repeat is to repeat", "to repeat repeat is to repeat"),
		};
	}
	
	@Override
	protected boolean satisfiesCoincidence() {
		return false;
	}

	
	@Test
	public void shouldReturnMetric(){
		assertSame(metric, getMetric().getMetric());
	}
	
	@Test
	public void shouldReturnSimplifier(){
		assertSame(simplifier, getMetric().getSimplifier());
	}
}
