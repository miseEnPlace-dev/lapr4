#!/bin/bash

ls | grep ecourse. | while read line; do
  path="$line/src/main/resources"
  if [ -f "$path/application.example.properties" ]; then
    cp "$path/application.example.properties" "$path/application.properties"
  fi
done

