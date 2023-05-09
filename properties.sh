#!/bin/bash
echo
ls | grep ecourse. | while read line; do
  path="$line/src/main/resources"
  if [ -f "$path/application.example.properties" ]; then
    if [ -f "$path/application.properties" ]; then
      echo "> $path already has application.properties. Skipping..."
    else
      echo "> Creating $path/application.properties"
      cp "$path/application.example.properties" "$path/application.properties"
    fi
  fi
done

