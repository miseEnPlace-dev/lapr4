#!/bin/sh
dir=$(dirname "$0")
java -cp "$dir/libs/h2-2.1.214.jar:$H2DRIVERS:$CLASSPATH" org.h2.tools.Server -tcp -web -ifNotExists
