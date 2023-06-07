function toggleColorPicker() {
  if (document.getElementById("colorpicker").style.opacity === "0")
    document.getElementById("colorpicker").style.opacity = "100%";
  else document.getElementById("colorpicker").style.opacity = 0;
}

function removeBackgroundClass(className) {
  const classArray = className.split(" ");
  const classWithoutBackground = classArray.filter(
    (c) => c.indexOf("bg-") === -1
  );
  return classWithoutBackground.join(" ");
}

function getContrastColor(color) {
  const colorArray = color.split("-");
  const colorTone = colorArray[colorArray.length - 1];
  const colorToneNumber = parseInt(colorTone);
  return `${colorArray[0]}-${colorArray[1]}-${colorToneNumber - 100}`;
}

function changeColor(color, elementId) {
  const oldClassName = document.getElementById(elementId).className;
  const classWithoutBackground = removeBackgroundClass(oldClassName);
  document.getElementById(
    elementId
  ).className = `${classWithoutBackground} ${color}`;

  document.getElementById("colorpicker").style.opacity = 0;

  const oldColorPickerClassName =
    document.getElementById("colorpicker-button").className;
  const classWithoutBackgroundColorPicker = removeBackgroundClass(
    oldColorPickerClassName
  );

  const contrastColor = getContrastColor(color);
  document.getElementById(
    "colorpicker-button"
  ).className = `${classWithoutBackgroundColorPicker} ${contrastColor}`;
}
