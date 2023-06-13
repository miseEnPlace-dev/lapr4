#!/bin/bash
echo
if [[ $1 == "--help" || $1 == "-h" ]]
then
  echo "Usage: ./properties.sh [OPTION]"
  echo "  -c, --clean     Clean all application.properties files"
  echo "  -h, --help      Display this help and exit"
  exit 0
fi

ls | grep ecourse. | while read line; do
  path="$line/src/main/resources"
  if [ -f "$path/application.example.properties" ]; then
    if [ -f "$path/application.properties" ]; then
      if [[ $1 == "--clean" || $1 == "-c" ]]
      then
        echo "> Removing $path/application.properties"
        rm "$path/application.properties"
        echo "> Creating $path/application.properties"
        cp "$path/application.example.properties" "$path/application.properties"
      else
        echo "> $path already has application.properties. Skipping..."
      fi
    else
      echo "> Creating $path/application.properties"
      cp "$path/application.example.properties" "$path/application.properties"
    fi
  fi
done

