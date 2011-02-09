/*
Copyright 2008-2011 Opera Software ASA

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.opera.core.systems.scope.internal;

/**
 * Enumerator for native mouse key events
 *
 * @author Deniz Turkoglu
 *
 */
public enum OperaMouseKeys {
		LEFT_DOWN(1),
		LEFT_UP(2),
		LEFT(LEFT_DOWN.value | LEFT_UP.value),
		RIGHT_DOWN(4),
		RIGHT_UP(8),
		RIGHT(RIGHT_DOWN.value | RIGHT_UP.value),
		MIDDLE_DOWN(16),
		MIDDLE_UP(32),
		MIDDLE(MIDDLE_DOWN.value | MIDDLE_UP.value);

		private OperaMouseKeys(int value) {
			this.value = value;
		}

		private Integer value;

		public Integer getValue() {
			return value;
		}

}
