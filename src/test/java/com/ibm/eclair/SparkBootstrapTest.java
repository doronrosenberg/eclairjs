/*
 * Copyright 2015 IBM Corp.
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

package com.ibm.eclair;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.spark.SparkContext;
import org.junit.Test;

import java.io.InputStreamReader;

public class SparkBootstrapTest {

    @Test
    public void getEngine() throws Exception {

        ScriptEngine engine = TestUtils.getEngine();
        engine.eval(new InputStreamReader(getClass().getResourceAsStream("/rddtest.js")));
        Object ret = ((Invocable)engine).invokeFunction("test");

        System.out.println(ret);
        assertNotNull(ret);
    }
}
