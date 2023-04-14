#!/bin/bash
export BASE_CP=eCourse.app.user.console/target/eCourse.app.user.console-1.0.jar:eCourse.app.user.console/target/dependency/*;

#REM call the java VM, e.g,
java -cp $BASE_CP isep.MainUserApp
