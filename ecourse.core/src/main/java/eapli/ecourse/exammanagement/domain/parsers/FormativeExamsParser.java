package eapli.ecourse.exammanagement.domain.parsers;

import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import eapli.ecourse.exammanagement.application.exceptions.ParseException;
import eapli.ecourse.exammanagement.domain.FormativeExamBuilder;

public class FormativeExamsParser {
  public static FormativeExamBuilder parseWithVisitor(String filePath) throws IOException, ParseException {
    ExamLexer lexer = new ExamLexer(CharStreams.fromFileName(filePath));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    FormativeExamParser parser = new FormativeExamParser(tokens);
    ParseTree tree = parser.start();

    if (parser.getNumberOfSyntaxErrors() > 0)
      throw new ParseException();

    FormativeExamsVisitor eval = new FormativeExamsVisitor();
    return (FormativeExamBuilder) eval.visit(tree);
  }
}
