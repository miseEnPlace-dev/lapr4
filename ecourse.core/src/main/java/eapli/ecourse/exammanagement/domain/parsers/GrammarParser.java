package eapli.ecourse.exammanagement.domain.parsers;

import java.io.IOException;

import eapli.ecourse.exammanagement.application.exceptions.ParseException;

public interface GrammarParser<B> {
  public B parseFromFile(String path) throws IOException, ParseException;

  public B parseFromString(String str) throws ParseException;
}
