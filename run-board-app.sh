#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export ECOURSE_CP=ecourse.app.board.console/target/app.board.console-1.0.0.jar:ecourse.app.board.console/target/dependency/*;

#REM call the java VM, e.g,
java -Djavax.net.ssl.keyStore=serverkeystore.jks \
    -Djavax.net.ssl.keyStorePassword=password \
    -Djavax.net.ssl.trustStore=clienttruststore.jks \
    -Djavax.net.ssl.trustStorePassword=password \
     -cp $ECOURSE_CP eapli.ecourse.app.board.console.App

