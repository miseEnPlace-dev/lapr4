#!/usr/bin/env bash

export ECOURSE_BOOTSTRAP=bootstrap/app.bootstrap-1.0.0.jar:bootstrap/dependency/*;

java -cp $ECOURSE_BOOTSTRAP eapli.ecourse.app.bootstrap.ECourseBootstrap -bootstrap:demo # -smoke:basic -smoke:e2e

export ECOURSE_CP=daemon.board-1.0.0.jar:dependency/*;

java -Djavax.net.ssl.keyStore=serverkeystore.jks \
    -Djavax.net.ssl.keyStorePassword=password \
    -Djavax.net.ssl.trustStore=clienttruststore.jks \
    -Djavax.net.ssl.trustStorePassword=password \
     -cp $ECOURSE_CP eapli.ecourse.daemon.board.App
