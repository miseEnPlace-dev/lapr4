package eapli.ecourse.exammanagement.domain.parsers;

import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import eapli.ecourse.answermanagement.domain.ExamAnswerBuilder;
import eapli.ecourse.exammanagement.application.ExamPrinter;
import eapli.ecourse.exammanagement.application.exceptions.ParseException;

public class TakeEvaluationExamParser {
  public static ExamAnswerBuilder parseWithVisitor(String filePath, ExamPrinter printer)
      throws IOException, ParseException {
    ExamLexer lexer = new ExamLexer(CharStreams.fromFileName(filePath));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    ExamParser parser = new ExamParser(tokens);
    ParseTree tree = parser.start();

    if (parser.getNumberOfSyntaxErrors() > 0)
      throw new ParseException();

    TakeEvaluationExamVisitor eval = new TakeEvaluationExamVisitor(printer);
    return (ExamAnswerBuilder) eval.visit(tree);
  }
}
