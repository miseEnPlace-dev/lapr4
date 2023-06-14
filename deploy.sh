#!/usr/bin/env bash

export ECOURSE_BOOTSTRAP=ecourse.app.bootstrap/target/app.bootstrap-1.0.0.jar:ecourse.app.bootstrap/target/dependency/*;

java -cp $ECOURSE_BOOTSTRAP eapli.ecourse.app.bootstrap.ECourseBootstrap -bootstrap:demo # -smoke:basic -smoke:e2e

export ECOURSE_CP=ecourse.daemon.board/target/daemon.board-1.0.0.jar:ecourse.daemon.board/target/dependency/*;

setsid -Djavax.net.ssl.keyStore=serverkeystore.jks \
    -Djavax.net.ssl.keyStorePassword=password \
    -Djavax.net.ssl.trustStore=clienttruststore.jks \
    -Djavax.net.ssl.trustStorePassword=password \
     -cp $ECOURSE_CP eapli.ecourse.daemon.board.App 2>/dev/null 1>&2 &

exit 0
