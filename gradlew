#!/bin/sh

# Standard Gradle wrapper script for Unix
# (Corrected for JVM options and APP_HOME)

APP_NAME="Gradle"
APP_BASE_NAME=$(basename "$0")

# Resolve APP_HOME
APP_HOME=$(dirname "$0")
if [ "$APP_HOME" = "." ]; then
  APP_HOME=$(pwd)
fi

# Add default JVM options here. 
DEFAULT_JVM_OPTS="-Xmx64m -Xms64m"

warn () {
    echo "$*"
}

die () {
    echo
    echo "$*"
    echo
    exit 1
}

# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    JAVACMD="$JAVA_HOME/bin/java"
else
    JAVACMD="java"
fi

CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

# Execute Gradle
exec "$JAVACMD" $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS "-Dorg.gradle.appname=$APP_BASE_NAME" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
