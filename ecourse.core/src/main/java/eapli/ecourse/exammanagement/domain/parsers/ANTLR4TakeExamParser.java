package eapli.ecourse.exammanagement.domain.parsers;

import java.io.IOException;

// import org.antlr.v4.runtime.CharStreams;
// import org.antlr.v4.runtime.CommonTokenStream;
// import org.antlr.v4.runtime.tree.ParseTree;

import eapli.ecourse.answermanagement.domain.Answer;
// import eapli.ecourse.exammanagement.application.ExamPrinter;
import eapli.ecourse.exammanagement.application.exceptions.ParseException;
// import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExamBuilder;

public class ANTLR4TakeExamParser implements GrammarParser<Answer> {
  public Answer parseFromFile(String path) throws IOException, ParseException {
    return null;
  }

  public Answer parseFromString(String str) throws ParseException {
    return null;
  }
}
