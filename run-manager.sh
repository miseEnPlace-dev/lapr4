#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export ECOURSE_CP=ecourse.app.manager.console/target/app.manager.console-1.0.0.jar:ecourse.app.manager.console/target/dependency/*;

#REM call the java VM, e.g,
java -cp $ECOURSE_CP eapli.ecourse.app.manager.console.ECourseManagerApp
