#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export ECOURSE_CP=ecourse.app.board.frontend/target/app.board.frontend-1.0.0.jar:ecourse.app.board.frontend/target/dependency/*;

#REM call the java VM, e.g,
java -cp $ECOURSE_CP eapli.ecourse.app.board.frontend.App

