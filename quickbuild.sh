#!/usr/bin/env bash
mvn -B dependency:copy-dependencies verify -D maven.javadoc.skip=true
