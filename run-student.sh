#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export ECOURSE_CP=ecourse.app.student.console/target/app.student.console-1.0.0.jar:ecourse.app.student.console/target/dependency/*;

#REM call the java VM, e.g,
java -cp $ECOURSE_CP eapli.ecourse.app.student.console.ECourseStudentApp
