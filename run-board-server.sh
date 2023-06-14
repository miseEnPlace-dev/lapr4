#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export ECOURSE_CP=ecourse.daemon.board/target/daemon.board-1.0.0.jar:ecourse.daemon.board/target/dependency/*;

#REM call the java VM, e.g,
java -Djavax.net.ssl.keyStore=serverkeystore.jks \
    -Djavax.net.ssl.keyStorePassword=password \
    -Djavax.net.ssl.trustStore=clienttruststore.jks \
    -Djavax.net.ssl.trustStorePassword=password \
     -cp $ECOURSE_CP eapli.ecourse.daemon.board.App

