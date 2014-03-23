/*
 * Copyright (C) 2013 salesforce.com, inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.auraframework.impl.validation;

import org.auraframework.impl.AuraImplTestCase;
import org.auraframework.util.validation.ValidationError;

abstract class AuraValidationTestCase extends AuraImplTestCase {

    public AuraValidationTestCase(String name) {
        super(name, false);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        ValidationUtil.startValidationContext();
    }

    @Override
    public void tearDown() throws Exception {
        try {
            ValidationUtil.endValidationContext();
        } finally {
            super.tearDown();
        }
    }

    protected final void assertError(String expectedMessage, ValidationError error) {
        ValidationTestUtil.assertError(expectedMessage, error);
    }
}
