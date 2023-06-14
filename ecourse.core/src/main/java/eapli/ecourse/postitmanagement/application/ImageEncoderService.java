package eapli.ecourse.postitmanagement.application;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

import eapli.framework.strings.util.StringPredicates;

public class ImageEncoderService {

  public String encodeImage(String imagePath) throws IOException {
    validatePath(imagePath);

    File img = new File(imagePath);
    if (!img.exists())
      throw new IllegalArgumentException("Image Path does not exist");

    byte[] fileContent = FileUtils.readFileToByteArray(img);
    String encodedString = Base64.getEncoder().encodeToString(fileContent);

    return encodedString;
  }

  private void validatePath(String imagePath) {
    if (StringPredicates.isNullOrEmpty(imagePath))
      throw new IllegalArgumentException("Image Path should neither be null nor empty");

    if (!imagePath.endsWith(".png") && !imagePath.endsWith(".jpg") && !imagePath.endsWith(".jpeg"))
      throw new IllegalArgumentException("Image Path should end with .png or .jpg or .jpeg");
  }

}
