#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export ECOURSE_CP=ecourse.app.board.console/target/app.board.console-1.0.0.jar:ecourse.app.board.console/target/dependency/*;

#REM call the java VM, e.g,
java -cp $ECOURSE_CP -Djavax.net.ssl.keyStore=clientkeystore.jks -Djavax.net.ssl.keyStorePassword=password eapli.ecourse.app.board.console.App

