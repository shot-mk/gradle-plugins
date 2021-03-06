/*
 * Copyright 2015 the original author or authors.
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
package com.github.iboyko.gradle.plugins.jpamodelgen.tasks;

import org.gradle.api.tasks.compile.JavaCompile;

/**
 * Compiles the Metamodel using jpaModelgen annotation processors supplied by the jpaModelgen extension configuration.
 * 
 * @author Illya Boyko
 * @since 1.0.0
 */
class JpaModelgenCompile extends JavaCompile {

    JpaModelgenCompile() {

	setSource(project.sourceSets.main.java)
	setClasspath(project.configurations.compile)

	project.afterEvaluate {
	    File file = project.file(project.jpaModelgen.jpaModelgenSourcesDir)
	    logger.info("Generate to dir: " + file)
	    setDestinationDir(file)

	    options.compilerArgs += [
		"-proc:only",
		"-processor",
		project.extensions.jpaModelgen.processor
	    ]
	}
    }
}
