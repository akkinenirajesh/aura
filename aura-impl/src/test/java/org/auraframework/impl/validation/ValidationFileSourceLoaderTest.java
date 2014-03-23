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

import java.io.File;
import java.util.Set;

import org.auraframework.components.AuraComponentsFiles;
import org.auraframework.def.ComponentDef;
import org.auraframework.def.ControllerDef;
import org.auraframework.def.DefDescriptor;
import org.auraframework.def.StyleDef;
import org.auraframework.impl.system.DefDescriptorImpl;
import org.auraframework.impl.util.AuraImplFiles;

public final class ValidationFileSourceLoaderTest extends AuraValidationTestCase {

    public ValidationFileSourceLoaderTest(String name) {
        super(name);
    }

    public void testFindIn() {
        // find in component folder
        File root = new File("src/test/components/validationTest/basic");
        assertTrue(root.exists());
        ValidationFileSourceLoader loader = new ValidationFileSourceLoader(AuraImplFiles.TestComponents.asFile());
        Set<DefDescriptor<?>> found = loader.findIn(root);
        assertEquals(3, found.size());
        assertTrue(found.contains(DefDescriptorImpl.getInstance("markup://validationTest:basic", ComponentDef.class)));
        assertTrue(found.contains(DefDescriptorImpl.getInstance("js://validationTest.basic", ControllerDef.class)));
        assertTrue(found.contains(DefDescriptorImpl.getInstance("css://validationTest.basic", StyleDef.class)));

        // find in namespace folder
        root = new File("src/test/components/validationTest");
        found = loader.findIn(root);
        assertEquals(3, found.size());
        assertTrue(found.contains(DefDescriptorImpl.getInstance("markup://validationTest:basic", ComponentDef.class)));
        assertTrue(found.contains(DefDescriptorImpl.getInstance("js://validationTest.basic", ControllerDef.class)));
        assertTrue(found.contains(DefDescriptorImpl.getInstance("css://validationTest.basic", StyleDef.class)));

        // find in file
        root = new File("src/test/components/validationTest/basic/basicController.js");
        found = loader.findIn(root);
        assertEquals(1, found.size());
        assertTrue(found.contains(DefDescriptorImpl.getInstance("js://validationTest.basic", ControllerDef.class)));
        root = new File("src/test/components/validationTest/basic/basic.css");
        found = loader.findIn(root);
        assertEquals(1, found.size());
        assertTrue(found.contains(DefDescriptorImpl.getInstance("css://validationTest.basic", StyleDef.class)));

        // find nothing
        root = new File("src/test/components/validationTest/basic/missing.js");
        found = loader.findIn(root);
        assertEquals(0, found.size());

        // can find aura components
        loader = new ValidationFileSourceLoader(AuraComponentsFiles.Components.asFile());
        root = new File(AuraComponentsFiles.Components.getPath() + "/ui/button/button.cmp");
        found = loader.findIn(root);
        assertEquals(1, found.size());
        assertTrue(found.contains(DefDescriptorImpl.getInstance("markup://ui:button", ComponentDef.class)));
    }
}