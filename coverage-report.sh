#!/bin/bash
mvn clean test jacoco:report
xdg-open target/site/jacoco/index.html
