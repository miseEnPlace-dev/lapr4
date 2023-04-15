REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET ECOURSE_CP=ecourse.app.backoffice.console\target\app.backoffice.console-1.0.0.jar;ecourse.app.backoffice.console\target\dependency\*;

REM call the java VM, e.g,
java -cp %ECOURSE_CP% eapli.ecourse.app.backoffice.console.ECourseBackoffice
