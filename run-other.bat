REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=base.app.other.console\target\base.app.other.console-1.4.0-SNAPSHOT.jar;base.app.other.console\target\dependency\*;

REM call the java VM, e.g, 
java -cp %BASE_CP% eapli.base.app.other.console.OtherApp
