REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET ECOURSE_CP=ecourse.app.user.console\target\app.user.console-1.0.0.jar;ecourse.app.user.console\target\dependency\*;

REM call the java VM, e.g,
java -cp %ECOURSE_CP% eapli.ecourse.app.user.console.ECourseUserApp
