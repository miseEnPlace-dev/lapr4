REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET ECOURSE_CP=ecourse.app.bootstrap\target\app.bootstrap-1.0.0.jar;ecourse.app.bootstrap\target\dependency\*;

REM call the java VM, e.g,
java -cp %ECOURSE_CP% eapli.ecourse.app.bootstrap.ECourseBootstrap -bootstrap:demo
REM -smoke:basic -smoke:e2e
