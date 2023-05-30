@echo off


for /d %%a in (ecourse.*) do (
  if exist "%%a\src\main\resources\application.example.properties" (
    if not exist "%%a\src\main\resources\application.properties" (
      copy "%%a\src\main\resources\application.example.properties" "%%a\src\main\resources\application.properties"
      echo Creating "%%a\src\main\resources\application.properties"
    ) else (
      echo "%%a\src\main\resources\application.properties" already exists
    )
  )
)

pause
