package eapli.ecourse.exammanagement.service;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class WriteToFile {

  public static void writeToFile(String content) throws FileNotFoundException, UnsupportedEncodingException {
    PrintWriter writer = new PrintWriter("formativeExam.txt", "UTF-8");
    writer.write(content);
    writer.close();
  }

}
