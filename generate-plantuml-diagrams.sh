#! /bin/sh
echo "LOG: Generate Plantuml Diagrams"
exportFormat="svg"
#monochrome="true"
extra="-SdefaultFontSize=20"
#extra="-SdefaultFontName=Times New Roman -SdefaultFontSize=10"

for aFile in `find docs -name "*.puml" -type f`;
do
  #-Smonochrome=$monochrome
  echo "Processing file: $aFile"
	java -jar libs/plantuml-1.2023.1.jar $extra -t$exportFormat $aFile 
done

echo "Finished"